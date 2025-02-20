package org.example;

import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator {
    private static final String SECRET_KEY = "my-secret-key";

    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        String encryptedData = encrypt(data);
        wrappee.writeData(encryptedData);
    }

    @Override
    public String readData() {
        String data = wrappee.readData();
        return decrypt(data);
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
