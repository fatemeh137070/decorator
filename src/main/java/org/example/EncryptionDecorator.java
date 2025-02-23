package org.example;

import java.util.Base64;
import java.util.logging.*;

public class EncryptionDecorator extends DataSourceDecorator{
    private static final Logger logger = Logger.getLogger(EncryptionDecorator.class.getName());
    private static final String SECRET_KEY = "my-secret-key";

    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        try {
            String encryptedData = encrypt(data);
            wrappee.writeData(encryptedData);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error encrypting data", e);
            throw new RuntimeException("Failed to encrypt data", e);
        }
    }

    @Override
    public String readData() {
        try {
            String data = wrappee.readData();
            return decrypt(data);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error decrypting data", e);
            throw new RuntimeException("Failed to decrypt data", e);
        }
    }

    private String encrypt(String data) {
        return Base64.getEncoder().encodeToString((data + SECRET_KEY).getBytes());
    }

    private String decrypt(String encryptedData) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        String result = new String(decodedBytes);
        return result.replace(SECRET_KEY, "");
    }
}
