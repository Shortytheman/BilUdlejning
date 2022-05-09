package com.example.biludlejning.model;

public class Bil {

  private int vognnummer;
  private int stelnummer;
  private String mærke;
  private String model;
  private String udstyrsNiveau;
  private int stålpris;
  private int regAfgift;
  private int co2Udledning;
  private boolean udlejet;
  private String udlejningsdato;
  private boolean erSD;

  public Bil(){

  }

  public Bil(int vognnummer, int stelnummer, String mærke, String model, String udstyrsNiveau, int stålpris,
             int regAfgift, int co2Udledning, boolean udlejet, String udlejningsdato, boolean erSD) {
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
    this.erSD = erSD;
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

  public String getMærke() {
    return mærke;
  }

  public void setMærke(String mærke) {
    this.mærke = mærke;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getUdstyrsNiveau() {
    return udstyrsNiveau;
  }

  public void setUdstyrsNiveau(String udstyrsNiveau) {
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

  public String getUdlejningsdato() {
    return udlejningsdato;
  }

  public void setUdlejningsdato(String udlejningsdato) {
    this.udlejningsdato = udlejningsdato;
  }

  public boolean isErSD() {
    return erSD;
  }

  public void setErSD(boolean erSD) {
    this.erSD = erSD;
  }

//Klassen er abstrakt og kan derfor ikke instantieres.

}
