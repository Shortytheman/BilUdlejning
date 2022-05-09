package com.example.biludlejning.model;

public class Kunde {

  private int kundeId;

  private Bil navn;

  private Bil email;
  private Bil adresse;
  private int postnummer;
  private Bil by;
  public Kunde(){

  }

  public Kunde(Bil navn, Bil email, Bil adresse, int postnummer, Bil by) {
    this.navn = navn;
    this.email = email;
    this.adresse = adresse;
    this.postnummer = postnummer;
    this.by = by;
  }

  public void setKundeId(int kundeId) {
    this.kundeId = kundeId;
  }

  public int getKundeId() {
    return kundeId;
  }

  public Bil getNavn() {
    return navn;
  }

  public void setNavn(Bil navn) {
    this.navn = navn;
  }

  public Bil getEmail() {
    return email;
  }

  public void setEmail(Bil email) {
    this.email = email;
  }

  public Bil getAdresse() {
    return adresse;
  }

  public void setAdresse(Bil adresse) {
    this.adresse = adresse;
  }

  public int getPostnummer() {
    return postnummer;
  }

  public void setPostnummer(int postnummer) {
    this.postnummer = postnummer;
  }

  public Bil getBy() {
    return by;
  }

  public void setBy(Bil by) {
    this.by = by;
  }
}
