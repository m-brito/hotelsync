package br.edu.ifsp.hotelsync.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.execute(getDatabaseCreationScript());
            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String getDatabaseCreationScript() {
        return """
            CREATE TABLE Guest (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name VARCHAR(255),
                pronouns VARCHAR(255),
                birthdate DATE,
                phone VARCHAR(255),
                cpf VARCHAR(255),
                road VARCHAR(255),
                city VARCHAR(255),
                state VARCHAR(255),
                cep VARCHAR(255),
                district VARCHAR(255),
                complement TEXT
            );

            CREATE TABLE Room (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                number INT,
                numberOfBed INT,
                roomCategory VARCHAR(255),
                description TEXT,
                roomStatus VARCHAR(255),
                area FLOAT
            );

            CREATE TABLE Product (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                description TEXT,
                price FLOAT,
                isActive BOOLEAN
            );

            CREATE TABLE Reservation (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                roomId INT,
                ownerId INT,
                reservationStatus VARCHAR(255),
                checkoutDate DATE,
                endDate DATE,
                startDate DATE,
                paymentValue FLOAT,
                paymentDate DATE,
                paymentMethod VARCHAR(255),
                FOREIGN KEY (roomId) REFERENCES Room(id),
                FOREIGN KEY (ownerId) REFERENCES Guest(id)
            );

            CREATE TABLE GuestReservation (
                guestId INT,
                reservationId INT,
                PRIMARY KEY (guestId, reservationId),
                FOREIGN KEY (guestId) REFERENCES Guest(id),
                FOREIGN KEY (reservationId) REFERENCES Reservation(id)
            );

            CREATE TABLE ConsumedProduct (
                reservationId INT,
                productId INT,
                price FLOAT,
                PRIMARY KEY (reservationId, productId),
                FOREIGN KEY (reservationId) REFERENCES Reservation(id),
                FOREIGN KEY (productId) REFERENCES Product(id)
            );
        """;
    }
}