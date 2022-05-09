package com.example.biludlejning.repository;

import com.example.biludlejning.model.String;
import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class BrugerRepository {

  Connection connection;

  public BrugerRepository(){
    connection = ConnectionManager.connectToSql();
  }

  public void opretBruger(Bruger bruger){
    try {
      String query = "INSERT INTO brugere(brugernavn, rolle, kodeord) values (?,?,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, bruger.getBrugernavn());
      preparedStatement.setString(2, bruger.getRolle());
      preparedStatement.setString(3, bruger.getKodeord());
      preparedStatement.executeUpdate();
    } catch (SQLException e){
      System.out.println("Fejl ved oprettelse af bruger" + e);
    }
  }

  public Bruger findBruger(String brugerNavn){
    Bruger bruger = null;
    try {
      String query = "SELECT * FROM brugere WHERE brugernavn = '" + brugerNavn + "'";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()){
        String brugernavn = resultSet.getString("brugernavn");
        String rolle = resultSet.getString("rolle");
        String kodeord = resultSet.getString("kodeord");
        bruger = new Bruger(brugerNavn,rolle,kodeord);
      }
    } catch (SQLException e){
      System.out.println("Kan ikke finde bruger." + e);
    }
    return bruger;
  }

  public ArrayList<Bruger> visAlleBrugere(){
    ArrayList<Bruger> brugere = new ArrayList<>();
    // Her skal metoden til at vise alle brugere være.
    return brugere;
  }

  public void sletBruger(String brugernavn){
    // DB kald som sletter bruger fra DB.
  }

  public void opdaterBruger(Bruger bruger){
    // DB kald som opdaterer bruger i DB. - evetuelt opdaterer rolle eller kode.
  }


}
