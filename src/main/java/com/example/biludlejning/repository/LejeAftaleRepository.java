package com.example.biludlejning.repository;

import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class LejeAftaleRepository {

    Connection connection;

    public LejeAftaleRepository() {
        connection = ConnectionManager.connectToSql();
    }

    public void tilføjLejeAftale(LejeAftale lejeAftale) {
        String query = "INSERT INTO lejeaftaler(kundeid, vognnummer, dato, forskudsbetaling, månedligbetaling, førstebetalingsdato, antalbetalinger) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lejeAftale.getKunde());
            preparedStatement.setInt(2, lejeAftale.getVognnummer());
            preparedStatement.setString(3, lejeAftale.getDato());
            preparedStatement.setDouble(4, lejeAftale.getForskudsBetaling());
            preparedStatement.setDouble(5, lejeAftale.getMånedligBetaling());
            preparedStatement.setString(6, lejeAftale.getFørsteBetalingsDato());
            preparedStatement.setInt(7, lejeAftale.getAntalBetalinger());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Oprettelse af lejeaftale fejlede: " + e);
        }
    }

    public ArrayList<LejeAftale> seLejeAftaler() {
        ArrayList<LejeAftale> lejeAftaler = new ArrayList<>();
        String query = "SELECT * FROM lejeaftaler";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int kundeID = resultSet.getInt("kundeid");
                int vognnummer = resultSet.getInt("vognnummer");
                String dato = resultSet.getString("dato");
                double forskudsBetaling = resultSet.getDouble("forskudsbetaling");
                double månedligBetaling = resultSet.getDouble("månedligbetaling");
                String førsteBetalingsDato = resultSet.getString("førstebetalingsdato");
                int antalBetalinger = resultSet.getInt("antalbetalinger");

                LejeAftale lejeAftale = new LejeAftale();
                lejeAftale.getKunde();
                lejeAftale.getVognnummer();
                lejeAftale.getDato();
                lejeAftale.getForskudsBetaling();
                lejeAftale.getMånedligBetaling();
                lejeAftale.getFørsteBetalingsDato();
                lejeAftale.getAntalBetalinger();
                lejeAftaler.add(lejeAftale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i fremvisning af lejeaftaler");
        }
        return lejeAftaler;
    }

    public void opdaterLejeAftale(LejeAftale lejeAftale) {
        String query = "UPDATE lejeaftaler SET kundeid=?, vognnummer=?, dato=?, forskudsbetaling=?, månedligbetaling=?, førstegangsbetaling=?, antalbetalinger=? WHERE lejeaftaleid=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lejeAftale.getKunde());
            preparedStatement.setInt(2, lejeAftale.getVognnummer());
            preparedStatement.setString(3, lejeAftale.getDato());
            preparedStatement.setDouble(4, lejeAftale.getForskudsBetaling());
            preparedStatement.setDouble(5, lejeAftale.getMånedligBetaling());
            preparedStatement.setString(6, lejeAftale.getFørsteBetalingsDato());
            preparedStatement.setInt(7, lejeAftale.getAntalBetalinger());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i opdatering af lejeaftale: " + e);
        }

    }

    public void sletLejeAftale(int lejeaftaleID) {
        String query = "DELETE FROM lejeaftaler WHERE lejeaftaleID=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lejeaftaleID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i sletning af lejeaftaler: " + e);
        }
    }

    public LejeAftale findLejeaftale(int lejeaftaleID) {
        LejeAftale lejeAftale = new LejeAftale();
        String query = "SELECT kundeid, vognnummer, dato, forskudsbetaling, månedligbetaling, førstebetalingsdato, antalbetalinger FROM lejeaftaler WHERE lejeaftaleid=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int kundeID = resultSet.getInt("kundeid");
                int vognnummer = resultSet.getInt("vognnummer");
                String dato = resultSet.getString("dato");
                double forskudsBetaling = resultSet.getDouble("forskudsbetaling");
                double månedligBetaling = resultSet.getDouble("månedligbetaling");
                String førsteBetalingsDato = resultSet.getString("førstebetalingsdato");
                int antalBetalinger = resultSet.getInt("antalbetalinger");

                lejeAftale = new LejeAftale();
                lejeAftale.setKunde(kundeID);
                lejeAftale.setVognnummer(vognnummer);
                lejeAftale.setDato(dato);
                lejeAftale.setForskudsBetaling(forskudsBetaling);
                lejeAftale.setMånedligBetaling(månedligBetaling);
                lejeAftale.setFørsteBetalingsDato(førsteBetalingsDato);
                lejeAftale.setAntalBetalinger(antalBetalinger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Forkert lejeaftaleid. Prøv igen. " + e);
        }
        return lejeAftale;
    }

}
