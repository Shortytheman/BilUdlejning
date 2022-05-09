package com.example.biludlejning.model;

public class String {

  private int vognnummer;
  private int stelnummer;
  private java.lang.String mærke;
  private java.lang.String model;
  private java.lang.String udstyrsNiveau;
  private int stålpris;
  private int regAfgift;
  private int co2Udledning;
  private boolean udlejet;
  private java.lang.String udlejningsdato;
  private boolean erDS;

  public String(){

  }

  public String(int vognnummer, int stelnummer, java.lang.String mærke, java.lang.String model, java.lang.String udstyrsNiveau, int stålpris,
                int regAfgift, int co2Udledning, boolean udlejet, java.lang.String udlejningsdato, boolean erDS) {
    this.vognnummer = vognnummer;
    this.stelnummer = stelnummer;
    this.mærke = mærke;
    this.model = model;
    this.udstyrsNiveau = udstyrsNiveau;
    this.stålpris = stålpris;
    this.regAfgift = regAfgift;
    this.co2Udledning = co2Udledning;
    this.udlejet = udlejet;
    this.udlejningsdato = udlejningsdato;
    this.erDS = erDS;
  }

  public int getVognnummer() {
    return vognnummer;
  }

  public void setVognnummer(int vognnummer) {
    this.vognnummer = vognnummer;
  }

  public int getStelnummer() {
    return stelnummer;
  }

  public void setStelnummer(int stelnummer) {
    this.stelnummer = stelnummer;
  }

  public java.lang.String getMærke() {
    return mærke;
  }

  public void setMærke(java.lang.String mærke) {
    this.mærke = mærke;
  }

  public java.lang.String getModel() {
    return model;
  }

  public void setModel(java.lang.String model) {
    this.model = model;
  }

  public java.lang.String getUdstyrsNiveau() {
    return udstyrsNiveau;
  }

  public void setUdstyrsNiveau(java.lang.String udstyrsNiveau) {
    this.udstyrsNiveau = udstyrsNiveau;
  }

  public int getStålpris() {
    return stålpris;
  }

  public void setStålpris(int stålpris) {
    this.stålpris = stålpris;
  }

  public int getRegAfgift() {
    return regAfgift;
  }

  public void setRegAfgift(int regAfgift) {
    this.regAfgift = regAfgift;
  }

  public int getCo2Udledning() {
    return co2Udledning;
  }

  public void setCo2Udledning(int co2Udledning) {
    this.co2Udledning = co2Udledning;
  }

  public boolean isUdlejet() {
    return udlejet;
  }

  public void setUdlejet(boolean udlejet) {
    this.udlejet = udlejet;
  }

  public java.lang.String getUdlejningsdato() {
    return udlejningsdato;
  }

  public void setUdlejningsdato(java.lang.String udlejningsdato) {
    this.udlejningsdato = udlejningsdato;
  }

  public boolean isErSD() {
    return erDS;
  }

  public void setErSD(boolean erSD) {
    this.erDS = erSD;
  }

//Klassen er abstrakt og kan derfor ikke instantieres.

}
