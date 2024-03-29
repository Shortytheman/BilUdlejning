package com.example.biludlejning.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Hovedsageligt skrevet af Niklas

public class LejeAftale {

  private int lejeaftaleID;
  private int kundeID;
  private int vognnummer;
  private String dato; // Formatet ddmmåå (f.eks. 070522)
  private double forskudsBetaling;
  private double månedligBetaling;
  private String førsteBetalingsDato;
  private double totalAfbetaling;
  private double betalesIalt;
  private String slutLejeDato;

  public LejeAftale(int kundeID, int vognnummer, double forskudsBetaling, double månedligBetaling,
                    String slutLejeDato){
    this.kundeID = kundeID;
    this.vognnummer = vognnummer;
    //Sørger for at "dato" altid er dagen kontrakten er lavet.
    // dataformatter fundet her: https://www.javatpoint.com/java-get-current-date
    DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
    LocalDateTime nu = LocalDateTime.now();
    this.dato = datoenIdag.format(nu);
    this.forskudsBetaling = forskudsBetaling;
    this.månedligBetaling = månedligBetaling;
    this.førsteBetalingsDato = findFørsteBetalingsdato();
    this.slutLejeDato = slutLejeDato;
  }

  public LejeAftale() {
  }

  //Sætter første betalingsdato til første dag i næstkommende måned fra dato;
  //Skrevet af Johannes
  public String findFørsteBetalingsdato () {
    String måned = dato.substring(2,4);
    String år = dato.substring(4,6);
    String nyMåned;
    if (Integer.parseInt(måned) == 12) {
      nyMåned = "01";
      int nytIntÅr = (Integer.parseInt(år) + 1);
      år = String.valueOf(nytIntÅr);
    } else {
      int nyIntMåned = (Integer.parseInt(måned) + 1);
      if (nyIntMåned < 10) {
        nyMåned = "0" + String.valueOf(nyIntMåned);
      } else {
        nyMåned = String.valueOf(nyIntMåned);
      }
    }
    return "01" + nyMåned + år;
  }

  public String getSlutLejeDato() {
    return slutLejeDato;
  }

  public void setSlutLejeDato(String slutLejeDato) {
    this.slutLejeDato = slutLejeDato;
  }

  public int getLejeaftaleID() {
    return lejeaftaleID;
  }

  public void setLejeaftaleID(int lejeaftaleID) {
    this.lejeaftaleID = lejeaftaleID;
  }

  public int getKundeId() {
    return kundeID;
  }

  public void setKunde(int kundeID) {
    this.kundeID = kundeID;
  }

  public int getVognnummer() {
    return vognnummer;
  }

  public void setVognnummer(int vognnummer) {
    this.vognnummer = vognnummer;
  }

  public String getDato() {
    return dato;
  }

  public void setDato(String dato) {
    this.dato = dato;
  }

  public double getForskudsBetaling() {
    return forskudsBetaling;
  }

  public void setForskudsBetaling(double forskudsBetaling) {
    this.forskudsBetaling = forskudsBetaling;
  }

  public double getMånedligBetaling() {
    return månedligBetaling;
  }

  public void setMånedligBetaling(double månedligBetaling) {
    this.månedligBetaling = månedligBetaling;
  }

  public String getFørsteBetalingsDato() {
    return førsteBetalingsDato;
  }

  public void setFørsteBetalingsDato(String førsteBetalingsDato) {
    this.førsteBetalingsDato = førsteBetalingsDato;
  }

}
