package com.example.biludlejning.model;


//Midlertidig modelklasse som bruge ifm. et LinkedHashMap i Bilrepository
public class Bildata {
  private String maerke;
  private String model;
  private String udstyrsNiveau;
  private boolean udlejet;
  private int antalBiler;
  private int antalUdlejede;
  private int antalIkkeUdlejede;
  private double resterendeBetaling;

  public Bildata(String marke, String model, String udstyrsNiveau, boolean udlejet, int antalBiler, int antalUdlejede, int antalIkkeUdlejede) {
    this.maerke = marke;
    this.model = model;
    this.udstyrsNiveau = udstyrsNiveau;
    this.udlejet = udlejet;
    this.antalBiler = antalBiler;
    this.antalUdlejede = antalUdlejede;
    this.antalIkkeUdlejede = antalIkkeUdlejede;
  }
  public double getResterendeBetaling() {
    return resterendeBetaling;
  }
  public void setResterendeBetaling(double resterendeBetaling) {
    this.resterendeBetaling = resterendeBetaling;
  }
  public int getAntalUdlejede() {
    return antalUdlejede;
  }

  public void setAntalUdlejede(int antalUdlejede) {
    this.antalUdlejede = antalUdlejede;
  }

  public int getAntalIkkeUdlejede() {
    return antalIkkeUdlejede;
  }

  public void setAntalIkkeUdlejede(int antalIkkeUdlejede) {
    this.antalIkkeUdlejede = antalIkkeUdlejede;
  }

  public String getMaerke() {
    return maerke;
  }

  public void setMaerke(String maerke) {
    this.maerke = maerke;
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

  public boolean isUdlejet() {
    return udlejet;
  }

  public void setUdlejet(boolean udlejet) {
    this.udlejet = udlejet;
  }

  public int getAntalBiler() {
    return antalBiler;
  }

  public void setAntalBiler(int antalBiler) {
    this.antalBiler = antalBiler;
  }
}
