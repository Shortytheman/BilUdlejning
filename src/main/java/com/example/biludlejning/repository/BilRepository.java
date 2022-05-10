package com.example.biludlejning.repository;


import com.example.biludlejning.model.Bil;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class BilRepository {

    Connection connection;

    public BilRepository() {
        connection = ConnectionManager.connectToSql();
    }

    public void tilføjBil(Bil bil) {
        String query = "INSERT INTO bil (vognnummer, stelnummer, mærke, model, udstyrsNiveau, stålpris, regAfgift, co2Udledning, udlejet, udlejningsdato, erDS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setInt(1, bil.getVognnummer());
            preparedStatement.setInt(2, bil.getStelnummer());
            preparedStatement.setString(3, bil.getMærke());
            preparedStatement.setString(4, bil.getModel());
            preparedStatement.setInt(5, bil.getStålpris());
            preparedStatement.setInt(6, bil.getCo2Udledning());
            preparedStatement.setBoolean(7, bil.isUdlejet());
            preparedStatement.setString(8, bil.getUdlejningsdato());
            preparedStatement.setBoolean(9, bil.isErDS());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: "+ e);
        }
    }

    public ArrayList<Bil> seBiler() {
        ArrayList<Bil> biler = new ArrayList<>();
        String query = "SELECT * FROM bil";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int vognnummer = resultSet.getInt("vognnummer");
                int stelnummer = resultSet.getInt("stelnummer");
                String mærke = resultSet.getString("mærke");
                String model = resultSet.getString("model");
                String udstyrsNiveau = resultSet.getString("udstyrsNiveau");
                int stålpris = resultSet.getInt("stålpris");
                int regAfgift = resultSet.getInt("regAfgift");
                int co2Udledning = resultSet.getInt("co2Udledning");
                boolean udlejet = resultSet.getBoolean("udlejet");
                String udlejningsdato = resultSet.getString("udlejningsdato");
                boolean erDS = resultSet.getBoolean("erDS");

                Bil bil = new Bil();
                bil.setVognnummer(vognnummer);
                bil.setStelnummer(stelnummer);
                bil.setMærke(mærke);
                bil.setModel(model);
                bil.setUdstyrsNiveau(udstyrsNiveau);
                bil.setStålpris(stålpris);
                bil.setRegAfgift(regAfgift);
                bil.setCo2Udledning(co2Udledning);
                bil.setUdlejet(udlejet);
                bil.setUdlejningsdato(udlejningsdato);
                bil.setErDS(erDS);
                biler.add(bil);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
        return biler;
    }

    public void opdaterBil(Bil bil) {
        String query = "UPDATE bil SET vognnummer=?, stelnummer=?, mærke=?, model=?, udstyrsniveau=?, stålpris=?, regafgift=?, co2udledning=?, udlejet=?, udlejningsdato=?, erDS=?";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setInt(1, bil.getVognnummer());
            preparedStatement.setInt(2, bil.getStelnummer());
            preparedStatement.setString(3, bil.getMærke());
            preparedStatement.setString(4, bil.getModel());
            preparedStatement.setString(5, bil.getUdstyrsNiveau());
            preparedStatement.setInt(6, bil.getStålpris());
            preparedStatement.setInt(7, bil.getRegAfgift());
            preparedStatement.setInt(8, bil.getCo2Udledning());
            preparedStatement.setBoolean(9, bil.isUdlejet());
            preparedStatement.setString(10, bil.getUdlejningsdato());
            preparedStatement.setBoolean(11, bil.isErDS());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
    }

    public void sletBil(int vognnummer) {
        String query = "DELETE FROM bil WHERE vognnummer=?";

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            preparedStatement.setInt(1, vognnummer);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
    }

    public Bil findBilmedStelnummer(int vognnumer) {
        Bil bil = new Bil();
        String query = "SELECT stelnummer, mærke, model, udstyrsniveau, stålpris, regafgift, co2udledning, udlejet, udlejningsdato, erDS FROM biler WHERE vognnummer= " + vognnumer;

        try {
            PreparedStatement preparedStatement = ConnectionManager.connectToSql().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int vognnummer = resultSet.getInt("vognnummer");
                int stelnummer = resultSet.getInt("stelnummer");
                String mærke = resultSet.getString("mærke");
                String model = resultSet.getString("model");
                String udstyrsNiveau = resultSet.getString("udstyrsNiveau");
                int stålpris = resultSet.getInt("stålpris");
                int regAfgift = resultSet.getInt("regAfgift");
                int co2Udledning = resultSet.getInt("co2Udlejning");
                boolean udlejet = resultSet.getBoolean("udlejet");
                String udlejningsdato = resultSet.getString("udlejningsdato");
                boolean erDS = resultSet.getBoolean("erDS");

                bil = new Bil();
                bil.setVognnummer(vognnumer);
                bil.setStelnummer(stelnummer);
                bil.setMærke(mærke);
                bil.setModel(model);
                bil.setUdstyrsNiveau(udstyrsNiveau);
                bil.setStålpris(stålpris);
                bil.setRegAfgift(regAfgift);
                bil.setCo2Udledning(co2Udledning);
                bil.setUdlejet(udlejet);
                bil.setUdlejningsdato(udlejningsdato);
                bil.setErDS(erDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR 404: " + e);
        }
        return bil;
    }
}
