package com.example.biludlejning.repository;


import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Bildata;
import com.example.biludlejning.model.LejeAftale;
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

    public LinkedHashMap<Bil, Bildata> modelAntal() {
        LinkedHashMap<Bil, Bildata> modelAntal = new LinkedHashMap<>();
        ArrayList<Bil> tempBil = seBiler();
        int counter = 0;
        int udlejedeCounter = 0;
        int ikkeUdlejedeCounter = 0;
        for (int i = 0; i < tempBil.size(); i++) {
            counter = 1;
            if (tempBil.get(i).isUdlejet()) {
                udlejedeCounter = 1;
                ikkeUdlejedeCounter = 0;
            } else if (!tempBil.get(i).isUdlejet()) {
                ikkeUdlejedeCounter = 1;
                udlejedeCounter = 0;
            }
            for (int j = i + 1; j < tempBil.size(); j++) {
                if (tempBil.get(i).getModel().equalsIgnoreCase(tempBil.get(j).getModel())) {
                    counter++;
                    if (tempBil.get(j).isUdlejet()) {
                        udlejedeCounter++;
                    } else if (!tempBil.get(j).isUdlejet()) {
                        ikkeUdlejedeCounter++;
                    }
                    tempBil.remove(j);
                    j = j - 1;
                }
            }
            modelAntal.put(tempBil.get(i), new Bildata(tempBil.get(i).getMaerke(), tempBil.get(i).getModel(),
                    tempBil.get(i).getUdstyrsNiveau(), tempBil.get(i).isUdlejet(), counter, udlejedeCounter, ikkeUdlejedeCounter));
        }
        return modelAntal;
    }

    public ArrayList<Bil> enAfHverModel() {
        System.out.println(seBiler().size());
        ArrayList<Bil> enAfHverModel = seBiler();
        for (int i = 0; i < enAfHverModel.size(); i++) {
            for (int j = i + 1; j < enAfHverModel.size(); j++) {
                if (enAfHverModel.get(i).getModel().equalsIgnoreCase(enAfHverModel.get(j).getModel())) {
                    enAfHverModel.remove(j);
                    j = j - 1;
                }
            }
        }
        return enAfHverModel;
    }
    public double manglendeBetalingPerLejeaftale(double månedligBetaling, String slutLejeDato) {
        String dagsDatoString;
        DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDateTime nu = LocalDateTime.now();
        dagsDatoString = datoenIdag.format(nu);
        int dagsDatoDag = Integer.parseInt(dagsDatoString.substring(0, 2));
        int dagsDatoMåned = Integer.parseInt(dagsDatoString.substring(2, 4));
        int dagsDatoÅr = Integer.parseInt(dagsDatoString.substring(4, 6));
        int slutDatoDag = Integer.parseInt(slutLejeDato.substring(0,2));
        int slutDatoMåned = Integer.parseInt(slutLejeDato.substring(2, 4));
        int slutDatoÅr = Integer.parseInt(slutLejeDato.substring(4, 6));

        int månedCounter;
        int årCounter;
        double resterendeBetaling = 0;
        if (dagsDatoÅr == slutDatoÅr) {
            månedCounter = slutDatoMåned - dagsDatoMåned;
            resterendeBetaling = månedligBetaling * månedCounter;
        } else {
            årCounter = slutDatoÅr - dagsDatoÅr;
            månedCounter = 12 - dagsDatoMåned + slutDatoMåned;
            if (årCounter > 1) {
                månedCounter += 12 * årCounter - 1;
            }
            resterendeBetaling = månedligBetaling * månedCounter;
        }
        return resterendeBetaling;
    }
    public LinkedHashMap<String, Double> resterendeBetalingPerModel() {
        LinkedHashMap<String, Double> modelPris = new LinkedHashMap<>();

        ArrayList<Bil> biler = seBiler();
        ArrayList<LejeAftale> lejeAftaler = new ArrayList<>();
        String query = "SELECT * FROM lejeaftaler";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int kundeID = resultSet.getInt("kunde_id");
                int vognnummer = resultSet.getInt("vognnummer");
                String dato = resultSet.getString("dato");
                double forskudsBetaling = resultSet.getDouble("forskudsbetaling");
                double månedligBetaling = resultSet.getDouble("månedligbetaling");
                String førsteBetalingsDato = resultSet.getString("førstebetalingsdato");
                int antalBetalinger = resultSet.getInt("antalbetalinger");
                String slutLejeDato = resultSet.getString("slutlejedato");

                LejeAftale lejeAftale = new LejeAftale();
                lejeAftale.setKunde(kundeID);
                lejeAftale.setVognnummer(vognnummer);
                lejeAftale.setDato(dato);
                lejeAftale.setForskudsBetaling(forskudsBetaling);
                lejeAftale.setMånedligBetaling(månedligBetaling);
                lejeAftale.setFørsteBetalingsDato(førsteBetalingsDato);
                lejeAftale.setAntalBetalinger(antalBetalinger);
                lejeAftale.setSlutLejeDato(slutLejeDato);
                lejeAftaler.add(lejeAftale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i fremvisning af lejeaftaler");
        }
        ArrayList<Bil> fedGed = enAfHverModel();
        for (int i = 0; i < fedGed.size(); i++) {
            modelPris.put(fedGed.get(i).getModel(), 0.0);
            System.out.println(fedGed.size());
        }
        for (int i = 0; i < biler.size(); i++) {
            double prisCounter = 0.0;
            for (int j = 0; j < lejeAftaler.size(); j++) {
                if (biler.get(i).getVognnummer() == lejeAftaler.get(j).getVognnummer()) {
                    prisCounter = manglendeBetalingPerLejeaftale(lejeAftaler.get(j).getMånedligBetaling(), lejeAftaler.get(j).getSlutLejeDato());
                }
            }
            if (modelPris.containsKey(biler.get(i).getModel())) {
                prisCounter += modelPris.get(biler.get(i).getModel());
                modelPris.put(biler.get(i).getModel(), prisCounter);
            }
        }
        Set<String> Keys = modelPris.keySet();
        for (String key : Keys){
            System.out.println(key + modelPris.get(key));
        }
        return modelPris;
    }
}
