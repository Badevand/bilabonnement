package dk.kea.bilabonnement.bilabonnementsystem2.model;

// Model klasse - placeres i din model mappe
public class Medarbejder {

    private int medarbejderId;
    private String brugernavn;
    private String password;
    private String navn;
    private String email;
    private String rolle;

    // Konstruktører
    public Medarbejder() {}

    public Medarbejder(String brugernavn, String password, String navn, String email, String rolle) {
        this.brugernavn = brugernavn;
        this.password = password;
        this.navn = navn;
        this.email = email;
        this.rolle = rolle;
    }

    // Getters og Setters
    public int getMedarbejderId() {
        return medarbejderId;
    }

    public void setMedarbejderId(int medarbejderId) {
        this.medarbejderId = medarbejderId;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}