package com.example.biludlejning.utilities;

import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Drivermanager hentet fra sidste projekt
public class ConnectionManager {

  private static Connection connection;
  private static Environment environment;
  public ConnectionManager(Environment environment){
    this.environment = environment;
  }



  public static Connection connectToSql() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(
            environment.getProperty("spring.datasource.url"),
            environment.getProperty("spring.datasource.username"),
            environment.getProperty("spring.datasource.password"));
            //DriverManager.getConnection(System.getenv("url"), System.getenv("user"), System.getenv("password"));
        System.out.println("Der er forbindelse til serveren");
      } catch (SQLException e) {
        System.out.println("Forbindelsen til serveren virker ikke: " + e);
      }
    }
    return connection;
  }
}