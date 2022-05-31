package com.example.biludlejning.repository;


import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Bildata;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

//Skrevet af alle gruppens medlemmer

@Repository
public class BilRepository {

    Connection connection;

    public BilRepository() {
        connection = ConnectionManager.connectToSql();
    }

    //Skrevet af Mikkel
    public void tilføjBil(Bil bil) {
        String query = "INSERT INTO biler (vognnummer, stelnummer, mærke, model, udstyrsNiveau, stålpris, regAfgift, co2Udledning, udlejet, udlejningsdato, erDS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bil.getVognnummer());
            preparedStatement.setInt(2, bil.getStelnummer());
            preparedStatement.setString(3, bil.getMaerke());
            preparedStatement.setString(4, bil.getModel());
            preparedStatement.setString(5, bil.getUdstyrsNiveau());
            preparedStatement.setInt(6, bil.getStålpris());
            preparedStatement.setInt(7, bil.getRegAfgift());
            preparedStatement.setInt(8, bil.getCo2Udledning());
            preparedStatement.setBoolean(9, bil.isUdlejet());
            preparedStatement.setString(10, bil.getUdlejningsdato());
            preparedStatement.setBoolean(11, bil.isErDS());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i oprettelse af bil: " + e);
        }
    }

    //Skrevet af Mikkel
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
    //Skrevet af Johannes
    public ArrayList<Bil> seIkkeUdlejedeBiler() {
        ArrayList<Bil> ikkeUdlejedeBiler = new ArrayList<>();
        ArrayList<Bil> alleBiler = seBiler();
        for (int i = 0; i < alleBiler.size(); i++) {
            if (!alleBiler.get(i).isUdlejet()) {
                ikkeUdlejedeBiler.add(alleBiler.get(i));
            }
        }
        return ikkeUdlejedeBiler;
    }

    //Skrevet af Mikkel
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

    //Skrevet af Mikkel
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
    //Skrevet af Mikkel
    public Bil findBilMedVognnummer(int vognnummer) {
        Bil bil = null;
        String query = "SELECT stelnummer, mærke, model, udstyrsniveau, stålpris, regafgift, co2udledning, udlejet, udlejningsdato, erDS FROM biler WHERE vognnummer= " + vognnummer;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Bil kunne ikke findes. Prøv igen: " + e);
        }
        return bil;
    }

    /*
    Metoden tjekker bilerne i databasen igennem, hvis den string der modtages som parameter matcher en bil, bliver denne bil tilføjet en seperat arrayliste som til sidst returneres.
    Man ender således med en arrayliste kun indeholdende den efterspurgte bilmodel.
    - Skrevet af Johannes
     */
    public ArrayList<Bil> returnerBilEfterModel(String model) {
        ArrayList<Bil> biler = seBiler();
        ArrayList<Bil> bilerEfterModel = new ArrayList<>();
        for (int i = 0; i < biler.size(); i++) {
            if (biler.get(i).getModel().equalsIgnoreCase(model)) {
                bilerEfterModel.add(biler.get(i));
            }
        }
        return bilerEfterModel;
    }

    /*
    Metoden er specifikt beskrevet i rapporten under afsnittet Uddrag af koden -> Forklaring af metoden modelAntal.
    Overordnet forklaret er det en metode der finder antallet af en specifik bilmodel, samt antallet af udlejede og
    ikke-udlejede eksemplarer af modellen og sætter disse værdier i et objekt i en LinkedHashMap.
    Denne information vises i en tabel til forretningsudvikleren.
    - Skrevet af Johannes og Niklas
    */
    public LinkedHashMap<Bil, Bildata> modelAntal() {
        LinkedHashMap<Bil, Bildata> modelAntal = new LinkedHashMap<>();
        ArrayList<Bil> tempBil = seBiler();
        int counter;
        int udlejedeCounter = 0;
        int ikkeUdlejedeCounter = 0;
        for (int i = 0; i < tempBil.size(); i++) {
            counter = 1;
            if (tempBil.get(i).isUdlejet()) {
                udlejedeCounter = 1;
                ikkeUdlejedeCounter = 0;
            } else {
                ikkeUdlejedeCounter = 1;
                udlejedeCounter = 0;
            }
            for (int j = i + 1; j < tempBil.size(); j++) {
                if (tempBil.get(i).getModel().equalsIgnoreCase(tempBil.get(j).getModel())) {
                    counter++;
                    if (tempBil.get(j).isUdlejet()) {
                        udlejedeCounter++;
                    } else {
                        ikkeUdlejedeCounter++;
                    }
                    tempBil.remove(j);
                    j--;
                }
            }
            modelAntal.put(tempBil.get(i), new Bildata(tempBil.get(i).getMaerke(), tempBil.get(i).getModel(),
                    tempBil.get(i).getUdstyrsNiveau(), tempBil.get(i).isUdlejet(), counter, udlejedeCounter, ikkeUdlejedeCounter));
        }
        return modelAntal;
    }

    /*
    Denne metoder finder først det resterende antal måneder tilbage af lejeaftalen.
    Dernæst udregner den den resterende betaling for en konkret lejeaftale ved at gange den månedlige betaling med antallet af resterende måneder.
    - Skrevet af Johannes
    */
    public double manglendeBetalingPerLejeaftale(double månedligBetaling, String slutLejeDato) {
        String dagsDatoString;
        DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDateTime nu = LocalDateTime.now();
        dagsDatoString = datoenIdag.format(nu);
        int dagsDatoMåned = Integer.parseInt(dagsDatoString.substring(2, 4));
        int dagsDatoÅr = Integer.parseInt(dagsDatoString.substring(4, 6));
        int slutDatoMåned = Integer.parseInt(slutLejeDato.substring(2, 4));
        int slutDatoÅr = Integer.parseInt(slutLejeDato.substring(4, 6));
        int månedCounter;
        int årCounter;
        double resterendeBetaling;
        if (dagsDatoÅr == slutDatoÅr) {
            månedCounter = slutDatoMåned - dagsDatoMåned;
            resterendeBetaling = månedligBetaling * månedCounter;
        } else {
            årCounter = slutDatoÅr - dagsDatoÅr;
            månedCounter = årCounter * 12;
            månedCounter += slutDatoMåned - dagsDatoMåned;
            resterendeBetaling = månedligBetaling * månedCounter;
        }
        return resterendeBetaling;
    }

    /*
    Denne metode anvender et dobbelt for-loop til at fjerne gengangere af samme model fra en arrayliste af biler der til sidst kun vil indeholde et eksemplar af hver model.
    Metoden finder anvendelse i den senere metode resterendeBetalingPerModel.
    - Skrevet af Johannes
    */
    public ArrayList<Bil> enAfHverModel() {
        ArrayList<Bil> enAfHverModel = seBiler();
        for (int i = 0; i < enAfHverModel.size(); i++) {
            for (int j = i + 1; j < enAfHverModel.size(); j++) {
                if (enAfHverModel.get(i).getModel().equalsIgnoreCase(enAfHverModel.get(j).getModel())) {
                    enAfHverModel.remove(j);
                    j--;
                }
            }
        }
        return enAfHverModel;
    }

    /*
    Udregner den resterende indkommende betaling for alle udlejede biler af en bestemt bilmodel.
    - Skrevet af Johannes
    */
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
                String slutLejeDato = resultSet.getString("slutlejedato");

                LejeAftale lejeAftale = new LejeAftale();
                lejeAftale.setKunde(kundeID);
                lejeAftale.setVognnummer(vognnummer);
                lejeAftale.setDato(dato);
                lejeAftale.setForskudsBetaling(forskudsBetaling);
                lejeAftale.setMånedligBetaling(månedligBetaling);
                lejeAftale.setFørsteBetalingsDato(førsteBetalingsDato);
                //lejeAftale.setAntalBetalinger(antalBetalinger);
                lejeAftale.setSlutLejeDato(slutLejeDato);
                lejeAftaler.add(lejeAftale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i fremvisning af lejeaftaler");
        }
        ArrayList<Bil> enAfHverModel = enAfHverModel();
        for (int i = 0; i < enAfHverModel.size(); i++) {
            modelPris.put(enAfHverModel.get(i).getModel(), 0.0);
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
        return modelPris;
    }
}
