package domain;

public class Cititor extends Utilizator{
    private String email;
    private String adresa;
    private String telefon;

    public Cititor() {
    }

    public Cititor(String username, Integer hashing, String nume, String prenume, String email, String adresa, String telefon) {
        super(username, hashing, nume, prenume);
        this.email = email;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
