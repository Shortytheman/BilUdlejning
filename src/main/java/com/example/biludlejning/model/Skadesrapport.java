package com.example.biludlejning.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class
Skadesrapport {

  //https://www.sampleforms.com/damage-report-forms.html for inspiration

  private int skadesrapportId;
  private String medarbejderNavn;
  private String medarbejderEmail;
  private String datoForUdfyldelse;
  private int vognnummer;
  private int kundeId;
  private LinkedHashMap<String,Double> skader;

  public Skadesrapport(String medarbejderNavn, String medarbejderEmail,
                       int vognnummer, int kundeId) {
    this.medarbejderNavn = medarbejderNavn;
    this.medarbejderEmail = medarbejderEmail;
    // dataformatter fundet her: https://www.javatpoint.com/java-get-current-date
    DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
    LocalDateTime nu = LocalDateTime.now();
    this.datoForUdfyldelse = datoenIdag.format(nu);
    this.vognnummer = vognnummer;
    this.kundeId = kundeId;
  }

  public Skadesrapport(){

  }

  public void setSkader(LinkedHashMap<String, Double> skader) {
    this.skader = skader;
  }

  public LinkedHashMap<String, Double> getSkader() {
    return skader;
  }

  public void setSkadesrapportId(int skadesrapportId) {
    this.skadesrapportId = skadesrapportId;
  }

  public int getSkadesrapportId() {
    return skadesrapportId;
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

  public int getVognnummer() {
    return vognnummer;
  }

  public void setVognnummer(int vognnummer) {
    this.vognnummer = vognnummer;
  }

  public int getKundeId() {
    return kundeId;
  }

  public void setKundeId(int kundeId) {
    this.kundeId = kundeId;
  }
}
