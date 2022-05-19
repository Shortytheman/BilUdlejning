package com.example.biludlejning.model;

public class Kunde {

  private int kundeId;
  private String navn;
  private String email;
  private String adresse;
  private int postnummer;
  private String by;

  public Kunde(){

  }

  public Kunde(String navn, String email, String adresse, int postnummer, String by) {
    this.navn = navn;
    this.email = email;
    this.adresse = adresse;
    this.postnummer = postnummer;
    this.by = by;
  }

  public int getKundeId() {
    return kundeId;
  }

  public String getNavn() {
    return navn;
  }

  public void setNavn(String navn) {
    this.navn = navn;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public int getPostnummer() {
    return postnummer;
  }

  public void setPostnummer(int postnummer) {
    this.postnummer = postnummer;
  }

  public String getBy() {
    return by;
  }

  public void setBy(String by) {
    this.by = by;
  }
  public void setKundeId(int kundeId) {
    this.kundeId = kundeId;
  }
}
