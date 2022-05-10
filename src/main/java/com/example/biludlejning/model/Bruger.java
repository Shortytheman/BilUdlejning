package com.example.biludlejning.model;

public class Bruger {

    private String brugernavn;
    private String rolle;
    private String kodeord;

    public Bruger(String brugernavn, String rolle, String kodeord) {
        this.rolle = rolle;
        this.brugernavn = brugernavn;
        this.kodeord = kodeord;
    }

    public Bruger() {

    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getKodeord() {
        return kodeord;
    }

    public void setKodeord(String kodeord) {
        this.kodeord = kodeord;
    }
}
