

package dk.kea.bilabonnement.bilabonnementsystem2.model;

import java.time.LocalDate;

public class Tilstandsrapport {
    private int tilstandsrapportId;
    private int lejeaftaleId;
    private String vognnummer;
    private LocalDate rapportDato;
    private int overkoerteKm;
    private double kmPris;
    private int totalSkadepris;
    private double totalKmPris;
    private boolean slutopgoerelseSendt;

    public Tilstandsrapport() {}
    public Tilstandsrapport(int lejeaftaleId, String vognnummer, LocalDate rapportDato,
                            int overkoerteKm, double kmPris, int totalSkadepris,
                            double totalKmPris, boolean slutopgoerelseSendt) {
        this.lejeaftaleId = lejeaftaleId;
        this.vognnummer = vognnummer;
        this.rapportDato = rapportDato;
        this.overkoerteKm = overkoerteKm;
        this.kmPris = kmPris;
        this.totalSkadepris = totalSkadepris;
        this.totalKmPris = totalKmPris;
        this.slutopgoerelseSendt = slutopgoerelseSendt;
    }

    public int getTilstandsrapportId() {
        return tilstandsrapportId;
    }

    public void setTilstandsrapportId(int tilstandsrapportId) {
        this.tilstandsrapportId = tilstandsrapportId;
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

    public LocalDate getRapportDato() {
        return rapportDato;
    }

    public void setRapportDato(LocalDate rapportDato) {
        this.rapportDato = rapportDato;
    }

    public int getOverkoerteKm() {
        return overkoerteKm;
    }

    public void setOverkoerteKm(int overkoerteKm) {
        this.overkoerteKm = overkoerteKm;
    }

    public double getKmPris() {
        return kmPris;
    }

    public void setKmPris(double kmPris) {
        this.kmPris = kmPris;
    }

    public int getTotalSkadepris() {
        return totalSkadepris;
    }

    public void setTotalSkadepris(int totalSkadepris) {
        this.totalSkadepris = totalSkadepris;
    }

    public double getTotalKmPris() {
        return totalKmPris;
    }

    public void setTotalKmPris(double totalKmPris) {
        this.totalKmPris = totalKmPris;
    }

    public boolean isSlutopgoerelseSendt() {
        return slutopgoerelseSendt;
    }

    public void setSlutopgoerelseSendt(boolean slutopgoerelseSendt) {
        this.slutopgoerelseSendt = slutopgoerelseSendt;
    }
}