package org.example;

public class Main {
    public static void main(String[] args) {
        String salaryData = "Salary records: John - $1000, Alice - $1200";


        DataSource source = new FileDataSource("data.txt");
        source.writeData(salaryData);
        System.out.println("Plain Data: " + source.readData());


        DataSource compressedSource = new CompressionDecorator(source);
        compressedSource.writeData(salaryData);
        System.out.println("Compressed Data: " + compressedSource.readData());


        DataSource encryptedSource = new EncryptionDecorator(source);
        encryptedSource.writeData(salaryData);
        System.out.println("Encrypted Data: " + encryptedSource.readData());


        DataSource fullyDecoratedSource = new EncryptionDecorator(new CompressionDecorator(source));
        fullyDecoratedSource.writeData(salaryData);
        System.out.println("Encrypted & Compressed Data: " + fullyDecoratedSource.readData());
    }
}
