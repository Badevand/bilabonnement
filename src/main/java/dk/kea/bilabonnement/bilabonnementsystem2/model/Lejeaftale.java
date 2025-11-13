

package dk.kea.bilabonnement.bilabonnementsystem2.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Lejeaftale {
    private int lejeaftaleId;
    private String vognnummer;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double maanedligPris;
    private double totalPris;
    private String status;
    private LocalDateTime oprettetDato;

    public Lejeaftale() {}
    public Lejeaftale(String vognnummer, LocalDate startDato, LocalDate slutDato,
                      double maanedligPris, double totalPris, String status) {
        this.vognnummer = vognnummer;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maanedligPris = maanedligPris;
        this.totalPris = totalPris;
        this.status = status;
    }

    public int getLejeaftaleId() {
        return lejeaftaleId;
    }

    public void setLejeaftaleId(int lejeaftaleId) {
        this.lejeaftaleId = lejeaftaleId;
    }

    public String getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(String vognnummer) {
        this.vognnummer = vognnummer;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    public double getMaanedligPris() {
        return maanedligPris;
    }

    public void setMaanedligPris(double maanedligPris) {
        this.maanedligPris = maanedligPris;
    }

    public double getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(double totalPris) {
        this.totalPris = totalPris;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOprettetDato() {
        return oprettetDato;
    }

    public void setOprettetDato(LocalDateTime oprettetDato) {
        this.oprettetDato = oprettetDato;
    }
}