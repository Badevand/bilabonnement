package dk.kea.bilabonnement.bilabonnementsystem2.model;

public class Skadepris {
    private int skadetypeId;
    private String skadetype;
    private int pris;

    // Tom konstruktør
    public Skadepris() {}

    // Konstruktør
    public Skadepris(int skadetypeId, String skadetype, int pris) {
        this.skadetypeId = skadetypeId;
        this.skadetype = skadetype;
        this.pris = pris;
    }

    // Getters og Setters
    public int getSkadetypeId() {
        return skadetypeId;
    }

    public void setSkadetypeId(int skadetypeId) {
        this.skadetypeId = skadetypeId;
    }

    public String getSkadetype() {
        return skadetype;
    }

    public void setSkadetype(String skadetype) {
        this.skadetype = skadetype;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }
}