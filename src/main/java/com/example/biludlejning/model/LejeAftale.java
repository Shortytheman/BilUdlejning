package com.example.biludlejning.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LejeAftale {

  private Kunde kunde;
  private Bil bil;
  private String dato; // måske i formatet ddmmåå (f.eks. 070522)
  private int forskudsBetaling;
  private int månedligBetaling;
  private int førsteBetalingsDato;
  private int antalBetalinger;
  private int totalAfbetaling;
  private int betalesIalt;


  public LejeAftale(Kunde kunde, Bil bil, int forskudsBetaling, int månedligBetaling, int førsteBetalingsDato,
                    int antalBetalinger){
    this.kunde = kunde;
    this.bil = bil;
    //Sørger for at "dato" altid er dagen kontrakten er lavet.
    DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
    LocalDateTime nu = LocalDateTime.now();
    this.dato = datoenIdag.format(nu);
    this.forskudsBetaling = forskudsBetaling;
    this.månedligBetaling = månedligBetaling;
    this.førsteBetalingsDato = førsteBetalingsDato;
    this.antalBetalinger = antalBetalinger;
    this.totalAfbetaling = antalBetalinger * månedligBetaling;
    this.betalesIalt = (antalBetalinger * månedligBetaling) + forskudsBetaling;
  }

  public Kunde getKunde() {
    return kunde;
  }

  public void setKunde(Kunde kunde) {
    this.kunde = kunde;
  }

  public Bil getBil() {
    return bil;
  }

  public void setBil(Bil bil) {
    this.bil = bil;
  }

  public String getDato() {
    return dato;
  }

  public void setDato(String dato) {
    this.dato = dato;
  }

  public int getForskudsBetaling() {
    return forskudsBetaling;
  }

  public void setForskudsBetaling(int forskudsBetaling) {
    this.forskudsBetaling = forskudsBetaling;
  }

  public int getMånedligBetaling() {
    return månedligBetaling;
  }

  public void setMånedligBetaling(int månedligBetaling) {
    this.månedligBetaling = månedligBetaling;
  }

  public int getFørsteBetalingsDato() {
    return førsteBetalingsDato;
  }

  public void setFørsteBetalingsDato(int førsteBetalingsDato) {
    this.førsteBetalingsDato = førsteBetalingsDato;
  }

  public int getAntalBetalinger() {
    return antalBetalinger;
  }

  public void setAntalBetalinger(int antalBetalinger) {
    this.antalBetalinger = antalBetalinger;
  }

  public int getTotalAfbetaling() {
    return totalAfbetaling;
  }

  public void setTotalAfbetaling(int totalAfbetaling) {
    this.totalAfbetaling = totalAfbetaling;
  }

  public int getBetalesIalt() {
    return betalesIalt;
  }

  public void setBetalesIalt(int betalesIalt) {
    this.betalesIalt = betalesIalt;
  }

public void lavKontrakt(){

}


}
