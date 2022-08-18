package com.example.biludlejning.repository;

import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Skrevet af alle i gruppen

@Repository
public class LejeAftaleRepository {

    Connection connection;

    public LejeAftaleRepository() {
        connection = ConnectionManager.connectToSql();
    }

    public void opretLejeaftale(LejeAftale lejeAftale) {
        String query = "INSERT INTO lejeaftaler(kunde_id, vognnummer, dato, forskudsbetaling, månedligbetaling, førstebetalingsdato, slutlejedato) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lejeAftale.getKundeId());
            preparedStatement.setInt(2, lejeAftale.getVognnummer());
            preparedStatement.setString(3, lejeAftale.getDato());
            preparedStatement.setDouble(4, lejeAftale.getForskudsBetaling());
            preparedStatement.setDouble(5, lejeAftale.getMånedligBetaling());
            preparedStatement.setString(6, lejeAftale.getFørsteBetalingsDato());
            preparedStatement.setString(7, lejeAftale.getSlutLejeDato());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i oprettelse af lejeaftale: " + e);
        }
    }

    public ArrayList<LejeAftale> seLejeAftaler() {
        ArrayList<LejeAftale> lejeAftaler = new ArrayList<>();
        String query = "SELECT * FROM lejeaftaler";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int lejeaftaleId = resultSet.getInt("lejeaftale_id");
                int kundeID = resultSet.getInt("kunde_id");
                int vognnummer = resultSet.getInt("vognnummer");
                String dato = resultSet.getString("dato");
                double forskudsBetaling = resultSet.getDouble("forskudsbetaling");
                double månedligBetaling = resultSet.getDouble("månedligbetaling");
                String førsteBetalingsDato = resultSet.getString("førstebetalingsdato");
                String slutLejeDato = resultSet.getString("slutlejedato");

                LejeAftale lejeAftale = new LejeAftale();
                lejeAftale.setLejeaftaleID(lejeaftaleId);
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
        return lejeAftaler;
    }

    public void sletLejeAftaleEfterKundeId(int kundeId) {
        String query = "DELETE FROM lejeaftaler WHERE kunde_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, kundeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fejl i sletning af lejeaftaler: " + e);
        }
    }

    public LejeAftale findlejeAftaleEfterKundeId(int kundeId) {
        LejeAftale lejeAftale = null;
        String query = "SELECT vognnummer, dato, forskudsbetaling, månedligbetaling, førstebetalingsdato," +
                " slutlejedato FROM lejeaftaler WHERE kunde_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, kundeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int vognnummer = resultSet.getInt("vognnummer");
                String dato = resultSet.getString("dato");
                double forskudsBetaling = resultSet.getDouble("forskudsbetaling");
                double månedligBetaling = resultSet.getDouble("månedligbetaling");
                String førsteBetalingsDato = resultSet.getString("førstebetalingsdato");
                String slutlejedato = resultSet.getString("slutlejedato");

                lejeAftale = new LejeAftale();
                lejeAftale.setKunde(kundeId);
                lejeAftale.setVognnummer(vognnummer);
                lejeAftale.setDato(dato);
                lejeAftale.setForskudsBetaling(forskudsBetaling);
                lejeAftale.setMånedligBetaling(månedligBetaling);
                lejeAftale.setFørsteBetalingsDato(førsteBetalingsDato);
                //lejeAftale.setAntalBetalinger(antalBetalinger);
                lejeAftale.setSlutLejeDato(slutlejedato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Forkert kundeId. Prøv igen. " + e);
        }
        return lejeAftale;
    }


    //Metoden opretter en String og konkatenerer den så den ligner en reel lejekontrakt som kunden får når de har
    //godkendt en leasing aftale.
    public String opretLejekontrakt(LejeAftale lejeAftale) {
        String query = "SELECT navn FROM kunder WHERE kunde_id = " + lejeAftale.getKundeId();
        String kundenavn = "";

        //Vi har lavet to DB kald, et som finder en bil og giver os et resultset og en query som updater bilen til udlejet.
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                kundenavn = resultSet.getString("navn");
            }
        } catch (SQLException e) {
            System.out.println("Fejl ved lejekontraktoprettelse" + e);
        }

        String query1 = "SELECT stelnummer, mærke, model, udstyrsniveau, co2udledning, erDS FROM biler WHERE vognnummer= " + lejeAftale.getVognnummer();
        int stelnummer = 0;
        String mærke = "";
        String model = "";
        String udstyrsNiveau = "";
        int co2Udledning = 0;
        boolean erDS = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stelnummer = resultSet.getInt("stelnummer");
                mærke = resultSet.getString("mærke");
                model = resultSet.getString("model");
                udstyrsNiveau = resultSet.getString("udstyrsNiveau");
                co2Udledning = resultSet.getInt("co2Udledning");
                erDS = resultSet.getBoolean("erDS");
            }
        } catch (SQLException a) {
            System.out.println("Fejl ved lejekontraktoprettelse" + a);
        }

        try {
            String query2 = "UPDATE biler SET udlejet=1 WHERE vognnummer = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, lejeAftale.getVognnummer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl når vi sætter bil til udlejet via lejeaftale" + e);
        }

        String hentHosDs = "";
        if (erDS) {
            hentHosDs = "ja";
        } else {
            hentHosDs = "nej";
        }

        String dagsDatoString;
        DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDateTime nu = LocalDateTime.now();
        dagsDatoString = datoenIdag.format(nu);
        int dagsDatoMåned = Integer.parseInt(dagsDatoString.substring(2, 4));
        int dagsDatoÅr = Integer.parseInt(dagsDatoString.substring(4, 6));
        int slutDatoMåned = Integer.parseInt(lejeAftale.getSlutLejeDato().substring(2, 4));
        int slutDatoÅr = Integer.parseInt(lejeAftale.getSlutLejeDato().substring(4, 6));
        int månedCounter;
        int årCounter;
        double resterendeBetaling = 0;
        if (dagsDatoÅr == slutDatoÅr) {
            månedCounter = slutDatoMåned - dagsDatoMåned;
            resterendeBetaling = lejeAftale.getMånedligBetaling() * månedCounter;
        } else {
            årCounter = slutDatoÅr - dagsDatoÅr;
            månedCounter = årCounter * 12;
            månedCounter += slutDatoMåned - dagsDatoMåned;
            resterendeBetaling = lejeAftale.getMånedligBetaling() * månedCounter;
        }
        String lejeKontrakt;
        lejeAftale.findFørsteBetalingsdato();

        lejeKontrakt = "-----------Lejekontrakt-----------\n" + "Kontrakt dato: " + lejeAftale.getDato() +
                "\nUdlejer: Bilabonnement"
                + "\nLejer: " + kundenavn + "\n\nForskudsbetaling: " + lejeAftale.getForskudsBetaling() +
                "\nMånedlig betaling: " + lejeAftale.getMånedligBetaling() + "\nFørste betaling den: " +
                lejeAftale.getFørsteBetalingsDato() + "\nAfbetaling ialt: " +
                resterendeBetaling + "\nTil betaling ialt: " + (resterendeBetaling + lejeAftale.getForskudsBetaling()) +
                "\n\nBil" + "\nMærke: " + mærke + "\nModel: " + model + "\nUdstyrsniveau: " + udstyrsNiveau +
                "\nStelnummer: " + stelnummer + "\nCo2 udledning: " + co2Udledning + " g/km" + "\nAfhentes hos DS forhandler: " + hentHosDs;

        return lejeKontrakt;
    }
    /*
    Denne metoder tjekker alle de eksisterende lejeaftaler for om de er tæt på at udløbe. Som udgangspunkt giver den besked
    når kontrakten er 5 dage fra udløb med mindre det er i en fremtidig måned, så kan den give besked op til 11 dage før.
    - Johannes
    */
    public ArrayList<LejeAftale> slutAftaleAdvarsel() {
        ArrayList<LejeAftale> udløberSnart = new ArrayList<>();
        DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDateTime nu = LocalDateTime.now();
        String dagsDatoString = datoenIdag.format(nu);
        int dagsDatoDag = Integer.parseInt(dagsDatoString.substring(0, 2));
        int dagsDatoMåned = Integer.parseInt(dagsDatoString.substring(2, 4));
        int dagsDatoÅr = Integer.parseInt(dagsDatoString.substring(4, 6));

        for (int i = 0; i < seLejeAftaler().size(); i++) {
            boolean årMatcher = false;
            boolean månedMatcher = false;
            int slutDatoDag = Integer.parseInt(seLejeAftaler().get(i).getSlutLejeDato().substring(0, 2));
            int slutDatoMåned = Integer.parseInt(seLejeAftaler().get(i).getSlutLejeDato().substring(2, 4));
            int slutDatoÅr = Integer.parseInt(seLejeAftaler().get(i).getSlutLejeDato().substring(4, 6));

            if (dagsDatoÅr < slutDatoÅr || (dagsDatoÅr == slutDatoÅr && dagsDatoMåned < slutDatoMåned) ||
                (dagsDatoÅr == slutDatoÅr && slutDatoMåned == dagsDatoMåned && dagsDatoDag < slutDatoDag)) {
                if (slutDatoÅr == dagsDatoÅr) {
                    årMatcher = true;
                    if (slutDatoMåned == dagsDatoMåned) {
                        månedMatcher = true;
                    }
                }
                if (årMatcher && månedMatcher) {
                    if (slutDatoDag < 6) {
                        udløberSnart.add(seLejeAftaler().get(i));
                    } else if (slutDatoDag - 6 < dagsDatoDag) {
                        udløberSnart.add(seLejeAftaler().get(i));
                    }
                }
                if (slutDatoÅr - 1 == dagsDatoÅr && slutDatoDag < 6 && dagsDatoMåned == 12 && dagsDatoDag > 24) {
                    udløberSnart.add(seLejeAftaler().get(i));
                }
                if (årMatcher && !månedMatcher && dagsDatoMåned == slutDatoMåned - 1 && slutDatoDag < 6 && dagsDatoDag > 24) {
                    udløberSnart.add(seLejeAftaler().get(i));
                }
            }

            if (slutDatoÅr == dagsDatoÅr && slutDatoMåned == dagsDatoMåned && dagsDatoDag == slutDatoDag){
                udløberSnart.remove(seLejeAftaler().get(i));
                try {
                    String query = "UPDATE biler SET udlejet=0 WHERE vognnummer = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, seLejeAftaler().get(i).getVognnummer());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Fejl når vi sætter biler til ikke længere udlejede" + e);
                }
            }
        }
        return udløberSnart;
    }
}
