package com.example.biludlejning.repository;


import com.example.biludlejning.model.Bil;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Repository
public class BilRepository {

    Connection connection;

    public BilRepository() {
        connection = ConnectionManager.connectToSql();
    }

    public void tilføjBil(Bil bil) {
        String query = "INSERT INTO biler (vognnummer, stelnummer, mærke, model, udstyrsNiveau, stålpris, regAfgift, co2Udledning, udlejet, udlejningsdato, erDS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bil.getVognnummer());
            preparedStatement.setInt(2, bil.getStelnummer());
            preparedStatement.setString(3, bil.getMaerke());
            preparedStatement.setString(4, bil.getModel());
            preparedStatement.setInt(5, bil.getStålpris());
            preparedStatement.setInt(6, bil.getCo2Udledning());
            preparedStatement.setBoolean(7, bil.isUdlejet());
            preparedStatement.setString(8, bil.getUdlejningsdato());
            preparedStatement.setBoolean(9, bil.isErDS());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i oprettelse af bil: "+ e);
        }
    }

    public ArrayList<Bil> seBiler() {
        ArrayList<Bil> biler = new ArrayList<>();
        String query = "SELECT * FROM biler";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i visning af biler: " + e);
        }
        return biler;
    }

    public void opdaterBil(Bil bil) {
        String query = "UPDATE biler SET stelnummer=?, mærke=?, model=?, udstyrsniveau=?, stålpris=?, regafgift=?, co2udledning=?, udlejet=?, udlejningsdato=?, erDS=? WHERE vognnummer=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bil.getStelnummer());
            preparedStatement.setString(2, bil.getMaerke());
            preparedStatement.setString(3, bil.getModel());
            preparedStatement.setString(4, bil.getUdstyrsNiveau());
            preparedStatement.setInt(5, bil.getStålpris());
            preparedStatement.setInt(6, bil.getRegAfgift());
            preparedStatement.setInt(7, bil.getCo2Udledning());
            preparedStatement.setBoolean(8, bil.isUdlejet());
            preparedStatement.setString(9, bil.getUdlejningsdato());
            preparedStatement.setBoolean(10, bil.isErDS());
            preparedStatement.setInt(11, bil.getVognnummer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i opdatering af bil: " + e);
        }
    }

    public void sletBil(int vognnummer) {
        String query = "DELETE FROM biler WHERE vognnummer=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, vognnummer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i sletning af bil: " + e);
        }
    }

    public Bil findBilmedStelnummer(int vognnumer) {
        Bil bil = null;
        String query = "SELECT stelnummer, mærke, model, udstyrsniveau, stålpris, regafgift, co2udledning, udlejet, udlejningsdato, erDS FROM biler WHERE vognnummer= " + vognnumer;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Bil kunne ikke findes. Prøv igen: " + e);
        }
        return bil;
    }
    public ArrayList<Bil> retunerBilEfterModel(String model) {
        ArrayList<Bil> biler = seBiler();
        ArrayList<Bil> bilerEfterModel = new ArrayList<>();
        for (int i=0; i < biler.size(); i++) {
            if (biler.get(i).getModel().equalsIgnoreCase(model)) {
                bilerEfterModel.add(biler.get(i));
            }
        }
        return bilerEfterModel;
    }
    public LinkedHashMap<Bil, Integer> modelAntal() {
        LinkedHashMap<Bil, Integer> modelAntal = new LinkedHashMap<>();
        ArrayList<Bil> tempBil = seBiler();
        int counter = 0;
        for (int i = 0; i < tempBil.size(); i++) {
            counter = 1;
            for (int j = i + 1; j < tempBil.size(); j++) {
                if (tempBil.get(i).getModel().equalsIgnoreCase(tempBil.get(j).getModel())) {
                    counter++;
                    tempBil.remove(j);
                    j = j - 1;
                }
            }
            modelAntal.put(tempBil.get(i), counter);
        }
        return modelAntal;
    }
    public ArrayList<Bil> enAfHverModel() {
        ArrayList<Bil> enAfHverModel = seBiler();
        for (int i = 0; i < enAfHverModel.size(); i++) {
            for (int j = i + 1; j < enAfHverModel.size(); j++) {
                if (seBiler().get(i).getModel().equalsIgnoreCase(seBiler().get(j).getModel())) {
                    enAfHverModel.remove(j);
                    j--;
                }
            }
        }
        return enAfHverModel;
    }
}
