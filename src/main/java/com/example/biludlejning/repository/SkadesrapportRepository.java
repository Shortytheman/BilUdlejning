package com.example.biludlejning.repository;

import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.model.Skade;
import com.example.biludlejning.model.Skadesrapport;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

//Hovedsageligt skrevet af Niklas

@Repository
public class SkadesrapportRepository {

  Connection connection;

  public SkadesrapportRepository() {
    connection = ConnectionManager.connectToSql();
  }

  public void opretSkadesrapport(Skadesrapport skadesrapport) {

    DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
    LocalDateTime nu = LocalDateTime.now();
    String dato = datoenIdag.format(nu);

    String query = "INSERT INTO skadesrapporter(medarbejdernavn, medarbejderemail, datoforudfyldelse, vognnummer, kunde_id) VALUES (?, ?, ?, ?, ?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1,skadesrapport.getMedarbejderNavn());
      preparedStatement.setString(2, skadesrapport.getMedarbejderEmail());
      preparedStatement.setString(3, dato);
      preparedStatement.setInt(4,skadesrapport.getVognnummer());
      preparedStatement.setInt(5,skadesrapport.getKundeId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Fejl i oprettelse af skadesrapport: " + e);
    }

    if (skadesrapport.getSkader() != null) {
      Set<String> Keys = skadesrapport.getSkader().keySet();
      for (String key : Keys) {
        if (skadesrapport.getSkader().get(key) != null) {
          tilføjSkade(new Skade(key, skadesrapport.getSkader().get(key), findSkadesrapportMedVognnummer(skadesrapport.getVognnummer()).getSkadesrapportId()));
        }
      }
    }

    try {
      String query2 = "UPDATE biler SET udlejet=0 WHERE vognnummer = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query2);
      preparedStatement.setInt(1, skadesrapport.getVognnummer());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Fejl når vi sætter bil til ikke udlejet via skadesrapport" + e);
    }
  }

  public ArrayList<Skadesrapport> seSkadesrapporter() {
    ArrayList<Skadesrapport> skadesrapporter = new ArrayList<>();
    String query = "SELECT * FROM skadesrapporter";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int skadesrapportId = resultSet.getInt("skadesrapport_id");
        String medarbejdernavn = resultSet.getString("medarbejdernavn");
        String medarbejderemail = resultSet.getString("medarbejderemail");
        String dato = resultSet.getString("datoforudfyldelse");
        int vognnummer = resultSet.getInt("vognnummer");
        int kundeId = resultSet.getInt("kunde_id");

        Skadesrapport skadesrapport = new Skadesrapport();
        skadesrapport.setSkadesrapportId(skadesrapportId);
        skadesrapport.setMedarbejderNavn(medarbejdernavn);
        skadesrapport.setMedarbejderEmail(medarbejderemail);
        skadesrapport.setDatoForUdfyldelse(dato);
        skadesrapport.setVognnummer(vognnummer);
        skadesrapport.setKundeId(kundeId);
        LinkedHashMap<String, Double> skader = new LinkedHashMap<>();
        for (int i = 0; i < findSkader(skadesrapportId).size(); i++){
          skader.put(findSkader(skadesrapportId).get(i).getSkadeBeskrivelse(),findSkader(skadesrapportId).get(i).getPris());
        }
        skadesrapport.setSkader(skader);
        skadesrapporter.add(skadesrapport);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Fejl i fremvisning af skadesrapporter");
    }
    return skadesrapporter;
  }

  public Skadesrapport findSkadesrapport(int skadesrapportId) {
    Skadesrapport skadesrapport = new Skadesrapport();
    String query = "SELECT medarbejdernavn, medarbejderemail, datoforudfyldelse, vognnummer, kunde_id FROM skadesrapporter WHERE skadesrapport_id=?";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1,skadesrapportId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String medarbejderNavn = resultSet.getString("medarbejdernavn");
        String medarbejderEmail = resultSet.getString("medarbejderemail");
        String dato = resultSet.getString("datoforudfyldelse");
        int vognnummer = resultSet.getInt("vognnummer");
        int kundeId = resultSet.getInt("kunde_id");
        skadesrapport.setSkadesrapportId(skadesrapportId);
        skadesrapport.setMedarbejderNavn(medarbejderNavn);
        skadesrapport.setMedarbejderEmail(medarbejderEmail);
        skadesrapport.setVognnummer(vognnummer);
        skadesrapport.setKundeId(kundeId);
        skadesrapport.setDatoForUdfyldelse(dato);
        LinkedHashMap<String, Double> skader = new LinkedHashMap<>();
        for (int i = 0; i < findSkader(skadesrapportId).size(); i++){
          skader.put(findSkader(skadesrapportId).get(i).getSkadeBeskrivelse(),findSkader(skadesrapportId).get(i).getPris());
        }
        skadesrapport.setSkader(skader);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Forkert skadesrapportId. Prøv igen. " + e);
    }
    return skadesrapport;
  }

  public ArrayList<Skade> findSkader(int skadesrapportId){
    ArrayList<Skade> skader = new ArrayList<>();

    String query = "SELECT * FROM skader WHERE skadesrapport_id = " + skadesrapportId;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String skadeBeskrivelse = resultSet.getString("skadebeskrivelse");
        double pris = resultSet.getDouble("pris");
        Skade skade = new Skade(skadeBeskrivelse,pris,skadesrapportId);
        skader.add(skade);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Fejl i fremvisning af skadesrapporter");
    }
    return skader;
  }

  public void tilføjSkade(Skade skade){
    String query = "INSERT INTO skader(skadebeskrivelse, pris, skadesrapport_id) VALUES (?, ?, ?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1,skade.getSkadeBeskrivelse());
      preparedStatement.setDouble(2, skade.getPris());
      preparedStatement.setInt(3, skade.getSkadesrapport_id());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Fejl i oprettelse af skade: " + e);
    }
  }

  public Skadesrapport findSkadesrapportMedVognnummer(int vognnummer) {
    Skadesrapport skadesrapport = new Skadesrapport();
    String query = "SELECT skadesrapport_id, medarbejdernavn, medarbejderemail, datoforudfyldelse, vognnummer, kunde_id FROM skadesrapporter WHERE vognnummer=?";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1,vognnummer);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int skadesrapportId = resultSet.getInt("skadesrapport_id");
        String medarbejderNavn = resultSet.getString("medarbejdernavn");
        String medarbejderEmail = resultSet.getString("medarbejderemail");
        String dato = resultSet.getString("datoforudfyldelse");
        int kundeId = resultSet.getInt("kunde_id");
        skadesrapport.setSkadesrapportId(skadesrapportId);
        skadesrapport.setMedarbejderNavn(medarbejderNavn);
        skadesrapport.setMedarbejderEmail(medarbejderEmail);
        skadesrapport.setVognnummer(vognnummer);
        skadesrapport.setKundeId(kundeId);
        skadesrapport.setDatoForUdfyldelse(dato);
        LinkedHashMap<String, Double> skader = new LinkedHashMap<>();
        for (int i = 0; i < findSkader(skadesrapportId).size(); i++){
          skader.put(findSkader(skadesrapportId).get(i).getSkadeBeskrivelse(),findSkader(skadesrapportId).get(i).getPris());
        }
        skadesrapport.setSkader(skader);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Forkert vognnummer ved skadesrapport. Prøv igen. " + e);
    }
    return skadesrapport;
  }

  public double totalSkadePris(int skadesrapportId){
    double totalpris = 0.0;
    for (int i = 0; i < findSkader(skadesrapportId).size();i++) {
      totalpris += findSkader(skadesrapportId).get(i).getPris();
    }
    return totalpris;
  }

}