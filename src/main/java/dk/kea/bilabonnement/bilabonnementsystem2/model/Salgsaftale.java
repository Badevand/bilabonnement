

package dk.kea.bilabonnement.bilabonnementsystem2.model;

import java.time.LocalDateTime;

public class Salgsaftale {
    private int salgsaftaleId;
    private String stelnummer;
    private String maerke;
    private double koebspris;
    private String valuta;
    private String afhentningssted;
    private boolean erForhaandsaftale;
    private String fakturaEmail;
    private String regAttestAdresse;
    private LocalDateTime oprettetDato;

    public Salgsaftale() {}
    public Salgsaftale(String stelnummer, String maerke, double koebspris, String valuta,
                       String afhentningssted, boolean erForhaandsaftale,
                       String fakturaEmail, String regAttestAdresse) {
        this.stelnummer = stelnummer;
        this.maerke = maerke;
        this.koebspris = koebspris;
        this.valuta = valuta;
        this.afhentningssted = afhentningssted;
        this.erForhaandsaftale = erForhaandsaftale;
        this.fakturaEmail = fakturaEmail;
        this.regAttestAdresse = regAttestAdresse;
    }

    public int getSalgsaftaleId() {
        return salgsaftaleId;
    }

    public void setSalgsaftaleId(int salgsaftaleId) {
        this.salgsaftaleId = salgsaftaleId;
    }

    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public double getKoebspris() {
        return koebspris;
    }

    public void setKoebspris(double koebspris) {
        this.koebspris = koebspris;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getAfhentningssted() {
        return afhentningssted;
    }

    public void setAfhentningssted(String afhentningssted) {
        this.afhentningssted = afhentningssted;
    }

    public boolean isErForhaandsaftale() {
        return erForhaandsaftale;
    }

    public void setErForhaandsaftale(boolean erForhaandsaftale) {
        this.erForhaandsaftale = erForhaandsaftale;
    }

    public String getFakturaEmail() {
        return fakturaEmail;
    }

    public void setFakturaEmail(String fakturaEmail) {
        this.fakturaEmail = fakturaEmail;
    }

    public String getRegAttestAdresse() {
        return regAttestAdresse;
    }

    public void setRegAttestAdresse(String regAttestAdresse) {
        this.regAttestAdresse = regAttestAdresse;
    }

    public LocalDateTime getOprettetDato() {
        return oprettetDato;
    }

    public void setOprettetDato(LocalDateTime oprettetDato) {
        this.oprettetDato = oprettetDato;
    }
}