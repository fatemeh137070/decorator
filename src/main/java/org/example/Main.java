package org.example;

public class Main {
    public static void main(String[] args) {
        String salaryData = "Salary records: John - $1000, Alice - $1200";

        DataSource source = new FileDataSource("data.txt");

        DataSource fullyDecoratedSource = new EncryptionDecorator(new CompressionDecorator(source));

        fullyDecoratedSource.writeData(salaryData);
        System.out.println("Fully Decorated Data: " + fullyDecoratedSource.readData());
    }
}