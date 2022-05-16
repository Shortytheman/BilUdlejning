package com.example.biludlejning.repository;

import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Repository
public class LejeAftaleRepository {

    Connection connection;

    public LejeAftaleRepository() {
        connection = ConnectionManager.connectToSql();
    }

    public void tilføjLejeAftale(LejeAftale lejeAftale) {
        String query = "INSERT INTO lejeaftaler(kunde_id, vognnummer, dato, forskudsbetaling, månedligbetaling, førstebetalingsdato, antalbetalinger, slutlejedato) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lejeAftale.getKundeId());
            preparedStatement.setInt(2, lejeAftale.getVognnummer());
            preparedStatement.setString(3, lejeAftale.getDato());
            preparedStatement.setDouble(4, lejeAftale.getForskudsBetaling());
            preparedStatement.setDouble(5, lejeAftale.getMånedligBetaling());
            preparedStatement.setString(6, lejeAftale.getFørsteBetalingsDato());
            preparedStatement.setInt(7, lejeAftale.getAntalBetalinger());
            preparedStatement.setString(8, lejeAftale.getSlutLejeDato());
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
                int kundeID = resultSet.getInt("kundeid");
                int vognnummer = resultSet.getInt("vognnummer");
                String dato = resultSet.getString("dato");
                double forskudsBetaling = resultSet.getDouble("forskudsbetaling");
                double månedligBetaling = resultSet.getDouble("månedligbetaling");
                String førsteBetalingsDato = resultSet.getString("førstebetalingsdato");
                int antalBetalinger = resultSet.getInt("antalbetalinger");
                String slutLejeDato = resultSet.getString("slutlejedaato");

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
        return lejeAftaler;
    }

    public void opdaterLejeAftale(LejeAftale lejeAftale) {
        String query = "UPDATE lejeaftaler SET kundeid=?, vognnummer=?, dato=?, forskudsbetaling=?, månedligbetaling=?, førstegangsbetaling=?, antalbetalinger=?, slutlejedato=? WHERE lejeaftaleid=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lejeAftale.getKundeId());
            preparedStatement.setInt(2, lejeAftale.getVognnummer());
            preparedStatement.setString(3, lejeAftale.getDato());
            preparedStatement.setDouble(4, lejeAftale.getForskudsBetaling());
            preparedStatement.setDouble(5, lejeAftale.getMånedligBetaling());
            preparedStatement.setString(6, lejeAftale.getFørsteBetalingsDato());
            preparedStatement.setInt(7, lejeAftale.getAntalBetalinger());
            preparedStatement.setString(8, lejeAftale.getSlutLejeDato());
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
        String query = "SELECT kundeid, vognnummer, dato, forskudsbetaling, månedligbetaling, førstebetalingsdato," +
                " antalbetalinger, slutlejedato FROM lejeaftaler WHERE lejeaftaleid=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lejeaftaleID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int kundeID = resultSet.getInt("kundeid");
                int vognnummer = resultSet.getInt("vognnummer");
                String dato = resultSet.getString("dato");
                double forskudsBetaling = resultSet.getDouble("forskudsbetaling");
                double månedligBetaling = resultSet.getDouble("månedligbetaling");
                String førsteBetalingsDato = resultSet.getString("førstebetalingsdato");
                int antalBetalinger = resultSet.getInt("antalbetalinger");
                String slutlejedato = resultSet.getString("slutlejedato");

                lejeAftale = new LejeAftale();
                lejeAftale.setKunde(kundeID);
                lejeAftale.setVognnummer(vognnummer);
                lejeAftale.setDato(dato);
                lejeAftale.setForskudsBetaling(forskudsBetaling);
                lejeAftale.setMånedligBetaling(månedligBetaling);
                lejeAftale.setFørsteBetalingsDato(førsteBetalingsDato);
                lejeAftale.setAntalBetalinger(antalBetalinger);
                lejeAftale.setSlutLejeDato(slutlejedato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Forkert lejeaftaleid. Prøv igen. " + e);
        }
        return lejeAftale;
    }

    public LejeAftale findlejeAftaleEfterKundeId(int kundeId) {
        LejeAftale lejeAftale = null;
        String query = "SELECT vognnummer, dato, forskudsbetaling, månedligbetaling, førstebetalingsdato," +
                " antalbetalinger, slutlejedato FROM lejeaftaler WHERE kunde_id=?";

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
                int antalBetalinger = resultSet.getInt("antalbetalinger");
                String slutlejedato = resultSet.getString("slutlejedato");

                lejeAftale = new LejeAftale();
                lejeAftale.setKunde(kundeId);
                lejeAftale.setVognnummer(vognnummer);
                lejeAftale.setDato(dato);
                lejeAftale.setForskudsBetaling(forskudsBetaling);
                lejeAftale.setMånedligBetaling(månedligBetaling);
                lejeAftale.setFørsteBetalingsDato(førsteBetalingsDato);
                lejeAftale.setAntalBetalinger(antalBetalinger);
                lejeAftale.setSlutLejeDato(slutlejedato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Forkert kundeId. Prøv igen. " + e);
        }
        return lejeAftale;
    }

    public String lavLejeKontrakt(LejeAftale lejeAftale) {
        String query = "SELECT navn FROM kunder WHERE kunde_id = " + lejeAftale.getKundeId();
        String kundenavn = "";
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

        String hentHosDs = "";
        if (erDS) {
            hentHosDs = "ja";
        } else {
            hentHosDs = "nej";
        }

        String lejeKontrakt;
        lejeAftale.findFørsteBetalingsdato();

        lejeKontrakt = "-----------Lejekontrakt-----------\n" + "Kontrakt dato: " + lejeAftale.getDato() +
                "\nUdlejer: Bilabonnement"
                + "\nLejer: " + kundenavn + "\n\nForskudsbetaling: " + lejeAftale.getForskudsBetaling() +
                "\nMånedlig betaling: " + lejeAftale.getMånedligBetaling() + "\nførste betaling den: " +
                lejeAftale.getFørsteBetalingsDato() + ", derefter den 1. i hver måned." + "\nAfbetaling ialt: " +
                lejeAftale.getTotalAfbetaling() + "\nTil betaling ialt: " + lejeAftale.getBetalesIalt() +
                "\n\nBil" + "\nMærke: " + mærke + "\nModel: " + model + "\nUdstyrsniveau: " + udstyrsNiveau +
                "\nStelnummer: " + stelnummer + "\nco2 udledning: " + co2Udledning + " g/km" + "\nAfhentes hos DS forhandler: " + hentHosDs;

        return lejeKontrakt;
    }
    public ArrayList<LejeAftale> slutAftaleAdvarsel() {
        ArrayList<LejeAftale> udløberSnart = null;
        String dagsDatoString;
        DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDateTime nu = LocalDateTime.now();
        dagsDatoString = datoenIdag.format(nu);
        int dagsDatoDag = Integer.parseInt(dagsDatoString.substring(0, 2));
        int dagsDatoMåned = Integer.parseInt(dagsDatoString.substring(2, 4));
        int dagsDatoÅr = Integer.parseInt(dagsDatoString.substring(4, 6));
        for (int i = 0; i < seLejeAftaler().size(); i++) {
            boolean årMatcher = false;
            boolean månedMatcher = false;
            int slutDatoDag = Integer.parseInt(seLejeAftaler().get(i).getSlutLejeDato().substring(0, 2));
            int slutDatoMåned = Integer.parseInt(seLejeAftaler().get(i).getSlutLejeDato().substring(2, 4));
            int slutDatoÅr = Integer.parseInt(seLejeAftaler().get(i).getSlutLejeDato().substring(4, 6));
            if (slutDatoÅr == dagsDatoÅr) {
                årMatcher = true;
                if (slutDatoMåned == dagsDatoMåned) {
                    månedMatcher = true;
                }
            }
            if (årMatcher && månedMatcher) {
                if (dagsDatoDag < 5) {
                    udløberSnart.add(seLejeAftaler().get(i));
                } else if (slutDatoDag - 5 < dagsDatoDag) {
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
        return udløberSnart;
    }
    public boolean slutAftaleAdvarselTest(String dato) {
        ArrayList<LejeAftale> udløberSnart = null;
        String dagsDatoString;
        DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDateTime nu = LocalDateTime.now();
        dagsDatoString = datoenIdag.format(nu);
        int dagsDatoDag = Integer.parseInt(dagsDatoString.substring(0, 2));
        int dagsDatoMåned = Integer.parseInt(dagsDatoString.substring(2, 4));
        int dagsDatoÅr = Integer.parseInt(dagsDatoString.substring(4, 6));
            boolean advarsel = false;
            boolean årMatcher = false;
            boolean månedMatcher = false;
            int slutDatoDag = Integer.parseInt(dato.substring(0,2));
            int slutDatoMåned = Integer.parseInt(dato.substring(2,4));
            int slutDatoÅr = Integer.parseInt(dato.substring(4,6));
            if (slutDatoÅr == dagsDatoÅr) {
                årMatcher = true;
                if (slutDatoMåned == dagsDatoMåned) {
                    månedMatcher = true;
                }
            }
            if (årMatcher && månedMatcher) {
                if (dagsDatoDag < 5) {
                    advarsel = true;
                } else if (slutDatoDag - 5 < dagsDatoDag) {
                    advarsel = true;
                }
            }
            if (slutDatoÅr - 1 == dagsDatoÅr && slutDatoDag < 6 && dagsDatoMåned == 12 && dagsDatoDag > 24) {
                advarsel = true;
            }
            if (årMatcher && !månedMatcher && dagsDatoMåned == slutDatoMåned - 1 && slutDatoDag < 6 && dagsDatoDag > 24) {
                advarsel = true;
            }
        return advarsel;
    }
}
