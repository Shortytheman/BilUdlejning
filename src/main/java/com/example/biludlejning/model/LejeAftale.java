package com.example.biludlejning.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LejeAftale {

  private int lejeaftaleID;
  private int kundeID;
  private int vognnummer;
  private String dato; // Formatet ddmmåå (f.eks. 070522)
  private double forskudsBetaling;
  private double månedligBetaling;
  private String førsteBetalingsDato; // JOS LAV METODE TIL AT SÆTTE DEN TIL NÆSTKOMMENDE 1. DATO
  private int antalBetalinger;
  private double totalAfbetaling;
  private double betalesIalt;


  public LejeAftale(int kundeID, int vognnummer, int forskudsBetaling, int månedligBetaling,
                    int antalBetalinger){
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
    this.antalBetalinger = antalBetalinger;
    this.totalAfbetaling = antalBetalinger * månedligBetaling;
    this.betalesIalt = (antalBetalinger * månedligBetaling) + forskudsBetaling;
  }
  //Sætter første betalingsdato til første dag i næstkommende måned fra dato;
  public String findFørsteBetalingsdato () {
    String måned = dato.substring(2,4);
    String år = dato.substring(4,6);
    String nyMåned;
    String nytÅr;
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

  public int getLejeaftaleID() {
    return lejeaftaleID;
  }

  public void setLejeaftaleID(int lejeaftaleID) {
    this.lejeaftaleID = lejeaftaleID;
  }

  public int getKunde() {
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

  public java.lang.String getDato() {
    return dato;
  }

  public void setDato(java.lang.String dato) {
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

  public int getAntalBetalinger() {
    return antalBetalinger;
  }

  public void setAntalBetalinger(int antalBetalinger) {
    this.antalBetalinger = antalBetalinger;
  }

  public double getTotalAfbetaling() {
    return totalAfbetaling;
  }

  public void setTotalAfbetaling(double totalAfbetaling) {
    this.totalAfbetaling = totalAfbetaling;
  }

  public double getBetalesIalt() {
    return betalesIalt;
  }

  public void setBetalesIalt(double betalesIalt) {
    this.betalesIalt = betalesIalt;
  }

public void lavKontrakt(){

}


}
