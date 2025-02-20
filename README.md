Decorator Pattern in Java

📌 Introduction

This project demonstrates the Decorator Pattern in Java by implementing a system for storing data with optional compression and encryption features.

The Decorator Pattern allows adding new functionalities to objects dynamically without modifying their structure. Here, we use decorators to add encryption and compression layers to a data source.

🛠 Technologies Used

Java 8+

File Handling (Java IO)

Base64 Encoding

GZIP Compression

📂 Project Structure

├── src
│   ├── DataSource.java               # Interface defining data storage
│   ├── FileDataSource.java           # Concrete implementation of data source
│   ├── DataSourceDecorator.java      # Base decorator class
│   ├── CompressionDecorator.java     # Adds compression functionality
│   ├── EncryptionDecorator.java      # Adds encryption functionality
│   ├── Main.java                      # Demonstrates the usage
└── README.md

🚀 How to Run

Clone the repository

git clone https://github.com/your-repo/decorator-pattern-java.git
cd decorator-pattern-java

Compile the Java files

javac src/*.java

Run the program

java src.Main

🔍 How It Works

FileDataSource: Stores and retrieves data from a file.

CompressionDecorator: Compresses data before saving and decompresses it when reading.

EncryptionDecorator: Encrypts data before saving and decrypts it when reading.

Main: Demonstrates different ways of using decorators.

📌 Example Usage

DataSource source = new FileDataSource("data.txt");
DataSource compressedSource = new CompressionDecorator(source);
DataSource encryptedSource = new EncryptionDecorator(source);
DataSource fullyDecoratedSource = new EncryptionDecorator(new CompressionDecorator(source));

🏆 Benefits of Using the Decorator Pattern

Flexible feature addition without modifying existing classes.

Combining multiple behaviors dynamically (e.g., encryption + compression).

Follows the Open-Closed Principle (OCP) in SOLID.

📜 License

This project is licensed under the MIT License.

🎯 Author

Developed by Your Name