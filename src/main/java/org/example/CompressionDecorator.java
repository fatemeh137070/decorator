package org.example;

import java.io.*;
import java.util.Base64;
import java.util.zip.*;

public class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        String compressedData = compress(data);
        wrappee.writeData(compressedData);
    }

    @Override
    public String readData() {
        String data = wrappee.readData();
        return decompress(data);
    }

    private String compress(String data) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
            gzipOut.write(data.getBytes());
            gzipOut.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String decompress(String compressedData) {
        try {
            byte[] bytes = Base64.getDecoder().decode(compressedData);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            GZIPInputStream gzipIn = new GZIPInputStream(bais);
            BufferedReader reader = new BufferedReader(new InputStreamReader(gzipIn));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
