package com.example.biludlejning.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Drivermanager hentet fra sidste projekt - hvor den samme samme gruppe arbejdede sammen om at udabejde den.
public class ConnectionManager {

  private static Connection connection;

  public static Connection connectToSql() {
    if (connection == null) {
      try {
        connection =
            DriverManager.getConnection(
            System.getenv("spring.datasource.url"),
            System.getenv("spring.datasource.username"),
            System.getenv("spring.datasource.password"));
            //DriverManager.getConnection(System.getenv("url"), System.getenv("user"), System.getenv("password"));
        System.out.println("Der er forbindelse til serveren");
      } catch (SQLException e) {
        System.out.println("Forbindelsen til serveren virker ikke: " + e);
      }
    }
    return connection;
  }
}