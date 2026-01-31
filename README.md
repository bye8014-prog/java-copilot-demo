# Java Copilot Demo

A starter Java project with Maven build system.

## Project Structure

```
java-copilot-demo/
├── src/
│   ├── main/java/com/example/
│   │   └── Main.java
│   └── test/java/com/example/
├── pom.xml
└── README.md
```

## Building the Project

To compile and build the project:

```bash
mvn clean compile
```

## Running the Project

To run the main application:

```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

## Testing

To run tests:

```bash
mvn test
```

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher
