package org.example;

import java.io.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionDecorator  extends DataSourceDecorator{
    private static final Logger logger = Logger.getLogger(CompressionDecorator.class.getName());

    public CompressionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        try {
            String compressedData = compress(data);
            wrappee.writeData(compressedData);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error compressing data", e);
            throw new RuntimeException("Failed to compress data", e);
        }
    }

    @Override
    public String readData() {
        try {
            String data = wrappee.readData();
            return decompress(data);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error decompressing data", e);
            throw new RuntimeException("Failed to decompress data", e);
        }
    }

    private String compress(String data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(baos)) {
            gzipOut.write(data.getBytes());
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private String decompress(String compressedData) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(compressedData);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             GZIPInputStream gzipIn = new GZIPInputStream(bais);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipIn))) {

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            return output.toString();
        }
    }
}
