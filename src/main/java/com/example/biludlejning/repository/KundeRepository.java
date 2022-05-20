package com.example.biludlejning.repository;

import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.model.Kunde;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class KundeRepository {

    Connection connection;

    public KundeRepository() {
        connection = ConnectionManager.connectToSql();
    }

    //CRUD!

    public void opretKunde(Kunde kunde) {
        String query = "INSERT INTO kunder(navn, email, adresse, postnummer, byen) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, kunde.getNavn());
            preparedStatement.setString(2, kunde.getEmail());
            preparedStatement.setString(3, kunde.getAdresse());
            preparedStatement.setInt(4, kunde.getPostnummer());
            preparedStatement.setString(5, kunde.getBy());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i oprettelse af kunde: " + e);
        }

    }

    public ArrayList<Kunde> visAlleKunder() {
        ArrayList<Kunde> kunder = new ArrayList<>();
        try {
            String query = "SELECT * FROM kunder";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int kundeId = resultSet.getInt("kunde_id");
                String navn = resultSet.getString("navn");
                String email = resultSet.getString("email");
                String adresse = resultSet.getString("adresse");
                int postnummer = resultSet.getInt("postnummer");
                String by = resultSet.getString("byen");

                Kunde kunde = new Kunde();
                kunde.setKundeId(kundeId);
                kunde.setNavn(navn);
                kunde.setEmail(email);
                kunde.setAdresse(adresse);
                kunde.setPostnummer(postnummer);
                kunde.setBy(by);
                kunder.add(kunde);
            }
        } catch (SQLException e) {
            System.out.println("Fejl ved visning af alle kunder" + e);
        }
        return kunder;
    }

    public void opdaterKunde(Kunde kunde) {
        // DB kald som opdaterer kunde i DB. - evetuelt opdaterer rolle eller kode.
        try {
            String query = "UPDATE kunder SET navn=?, email=?, adresse=?, postnummer=?, byen=? WHERE kunde_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, kunde.getNavn());
            preparedStatement.setString(2, kunde.getEmail());
            preparedStatement.setString(3, kunde.getAdresse());
            preparedStatement.setInt(4, kunde.getPostnummer());
            preparedStatement.setString(5, kunde.getBy());
            preparedStatement.setInt(6, kunde.getKundeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl ved opdatering af kunde" + e);
        }
    }

    public void sletKundeMedId(int kundeId) {
        // DB kald som sletter kunde fra DB.
        try {
            String query = "DELETE FROM kunder WHERE kunde_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,kundeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl ved sletning af kunde" + e);
        }
    }

    public Kunde findKundeMedId(int id) {
        Kunde kunde = null;
        try {
            String query = "SELECT * FROM kunder WHERE kunde_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int kundeId = resultSet.getInt("kunde_id");
                String navn = resultSet.getString("navn");
                String email = resultSet.getString("email");
                String adresse = resultSet.getString("adresse");
                int postnummer = resultSet.getInt("postnummer");
                String by = resultSet.getString("byen");

                kunde = new Kunde();
                kunde.setKundeId(kundeId);
                kunde.setNavn(navn);
                kunde.setEmail(email);
                kunde.setAdresse(adresse);
                kunde.setPostnummer(postnummer);
                kunde.setBy(by);

            }
        } catch (SQLException e) {
            System.out.println("Kan ikke finde kunde." + e);
        }
        return kunde;
    }
}
