package com.example.biludlejning.model;

public class Bruger {

  String rolle;
  String brugernavn;
  String kodeord;

  public Bruger(String rolle, String brugernavn, String kodeord){

  if (!rolle.equalsIgnoreCase("dataregistrering") ||
      !rolle.equalsIgnoreCase("forretningsudvikler") ||
      !rolle.equalsIgnoreCase("skadeogudbedring")){
    rolle = "ugyldig";
  } else {
    this.rolle = rolle;
  }
  this.brugernavn = brugernavn;
  this.kodeord = kodeord;
  }

}
