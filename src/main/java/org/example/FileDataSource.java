package org.example;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDataSource implements DataSource {
    private static final Logger logger = Logger.getLogger(FileDataSource.class.getName());
    private String filename;

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeData(String data) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing data to file", e);
            throw new RuntimeException("Failed to write data to file", e);
        }
    }

    @Override
    public String readData() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading data from file", e);
            throw new RuntimeException("Failed to read data from file", e);
        }
        return data.toString();
    }
}