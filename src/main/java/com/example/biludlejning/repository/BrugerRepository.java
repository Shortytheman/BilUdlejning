package com.example.biludlejning.repository;

import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class BrugerRepository {

    Connection connection;

    public BrugerRepository() {
        connection = ConnectionManager.connectToSql();
    }

    public void opretBruger(Bruger bruger) {
        try {
            String query = "INSERT INTO brugere(brugernavn, rolle, kodeord) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bruger.getBrugernavn());
            preparedStatement.setString(2, bruger.getRolle());
            preparedStatement.setString(3, bruger.getKodeord());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl ved oprettelse af bruger" + e);
        }
    }

    public ArrayList<Bruger> seBrugere() {
        ArrayList<Bruger> brugere = new ArrayList<>();
        String query = "SELECT * FROM brugere";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String brugernavn = resultSet.getString("brugernavn");
                String rolle = resultSet.getString("rolle");
                String kodeord = resultSet.getString("kodeord");

                Bruger bruger = new Bruger();
                bruger.setBrugernavn(brugernavn);
                bruger.setRolle(rolle);
                bruger.setKodeord(kodeord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
        return brugere;
    }

    public void opdaterBruger(Bruger bruger) {
        String query = "UPDATE brugere SET brugernavn=?, rolle=?, kodeord=?";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setString(1, bruger.getBrugernavn());
            preparedStatement.setString(2, bruger.getRolle());
            preparedStatement.setString(3, bruger.getKodeord());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
    }

    public void sletBruger(String brugernavn) {
        String query = "DELETE FROM brugere WHERE brugernavn=?";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setString(1, brugernavn);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
    }

    public Bruger findBruger(String brugerNavn) {
        Bruger bruger = null;
        try {
            String query = "SELECT * FROM brugere WHERE brugernavn = '" + brugerNavn + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String brugernavn = resultSet.getString("brugernavn");
                String rolle = resultSet.getString("rolle");
                String kodeord = resultSet.getString("kodeord");
                bruger = new Bruger(brugerNavn, rolle, kodeord);
            }
        } catch (SQLException e) {
            System.out.println("Kan ikke finde bruger." + e);
        }
        return bruger;
    }
}
