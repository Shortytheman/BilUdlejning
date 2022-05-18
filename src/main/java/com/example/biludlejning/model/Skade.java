package com.example.biludlejning.model;

public class Skade {

  private int skade_id;
  private String skadeBeskrivelse;
  private double pris;
  private int skadesrapport_id;

  public Skade(String skadeBeskrivelse, double pris, int skadesrapport_id) {
    this.skadeBeskrivelse = skadeBeskrivelse;
    this.pris = pris;
    this.skadesrapport_id = skadesrapport_id;
  }

  public int getSkadesrapport_id() {
    return skadesrapport_id;
  }

  public void setSkadesrapport_id(int skadesrapport_id) {
    this.skadesrapport_id = skadesrapport_id;
  }

  public String getSkadeBeskrivelse() {
    return skadeBeskrivelse;
  }

  public void setSkadeBeskrivelse(String skadeBeskrivelse) {
    this.skadeBeskrivelse = skadeBeskrivelse;
  }

  public double getPris() {
    return pris;
  }

  public void setPris(int pris) {
    this.pris = pris;
  }
}