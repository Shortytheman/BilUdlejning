package com.example.biludlejning.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class SkadesRapport {

  //https://www.sampleforms.com/damage-report-forms.html for inspiration

  private String medarbejderNavn;
  private String medarbejderEmail;
  private String datoForUdfyldelse;
  private Bil bil;
  private LinkedHashMap<String, Double> skadeOgPris; //Key = skade, value = pris på skaden
  private Kunde kunde;

  public SkadesRapport(String medarbejderNavn, String medarbejderEmail,
                       Bil bil, LinkedHashMap<String, Double> skadeOgPris, Kunde kunde) {
    this.medarbejderNavn = medarbejderNavn;
    this.medarbejderEmail = medarbejderEmail;
    // dataformatter fundet her: https://www.javatpoint.com/java-get-current-date
    DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
    LocalDateTime nu = LocalDateTime.now();
    this.datoForUdfyldelse = datoenIdag.format(nu);
    this.bil = bil;
    this.skadeOgPris = skadeOgPris;
    this.kunde = kunde;
  }

  public LinkedHashMap<String, Double> getSkadeOgPris() {
    return skadeOgPris;
  }

  public void setSkadeOgPris(LinkedHashMap<String, Double> skadeOgPris) {
    this.skadeOgPris = skadeOgPris;
  }

  public String getMedarbejderNavn() {
    return medarbejderNavn;
  }

  public void setMedarbejderNavn(String medarbejderNavn) {
    this.medarbejderNavn = medarbejderNavn;
  }

  public String getMedarbejderEmail() {
    return medarbejderEmail;
  }

  public void setMedarbejderEmail(String medarbejderEmail) {
    this.medarbejderEmail = medarbejderEmail;
  }

  public String getDatoForUdfyldelse() {
    return datoForUdfyldelse;
  }

  public void setDatoForUdfyldelse(String datoForUdfyldelse) {
    this.datoForUdfyldelse = datoForUdfyldelse;
  }

  public Bil getBil() {
    return bil;
  }

  public void setBil(Bil bil) {
    this.bil = bil;
  }

  public Kunde getKunde() {
    return kunde;
  }

  public void setKunde(Kunde kunde) {
    this.kunde = kunde;
  }
}
