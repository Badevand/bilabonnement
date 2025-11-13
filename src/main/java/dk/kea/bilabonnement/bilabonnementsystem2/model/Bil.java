

package dk.kea.bilabonnement.bilabonnementsystem2.model;

public class Bil {
    private String vognnummer;
    private String stelnummer;
    private String maerke;
    private String model;
    private String udstyrsniveau;
    private double staalpris;
    private double regAfgift;
    private int co2Udledning;
    private String status;
    private int kmVedStart;
    private int kmVedRetur;



    public Bil() {}
    public Bil(String vognnummer, String stelnummer, String maerke, String model,
               String udstyrsniveau, double staalpris, double regAfgift,
               int co2Udledning, String status, int kmVedStart) {
        this.vognnummer = vognnummer;
        this.stelnummer = stelnummer;
        this.maerke = maerke;
        this.model = model;
        this.udstyrsniveau = udstyrsniveau;
        this.staalpris = staalpris;
        this.regAfgift = regAfgift;
        this.co2Udledning = co2Udledning;
        this.status = status;
        this.kmVedStart = kmVedStart;
    }

    public String getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(String vognnummer) {
        this.vognnummer = vognnummer;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUdstyrsniveau() {
        return udstyrsniveau;
    }

    public void setUdstyrsniveau(String udstyrsniveau) {
        this.udstyrsniveau = udstyrsniveau;
    }

    public double getStaalpris() {
        return staalpris;
    }

    public void setStaalpris(double staalpris) {
        this.staalpris = staalpris;
    }

    public double getRegAfgift() {
        return regAfgift;
    }

    public void setRegAfgift(double regAfgift) {
        this.regAfgift = regAfgift;
    }

    public int getCo2Udledning() {
        return co2Udledning;
    }

    public void setCo2Udledning(int co2Udledning) {
        this.co2Udledning = co2Udledning;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getKmVedStart() {
        return kmVedStart;
    }

    public void setKmVedStart(int kmVedStart) {
        this.kmVedStart = kmVedStart;
    }

    public int getKmVedRetur() {
        return kmVedRetur;
    }

    public void setKmVedRetur(int kmVedRetur) {
        this.kmVedRetur = kmVedRetur;
    }

}