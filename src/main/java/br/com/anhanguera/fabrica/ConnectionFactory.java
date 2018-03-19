package br.com.anhanguera.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://35.199.91.51:5432/rlcoder", "rlcoder", "rock8476");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
