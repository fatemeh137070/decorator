package org.example;


public interface DataSource {
    void writeData(String data);
    String readData();
}
