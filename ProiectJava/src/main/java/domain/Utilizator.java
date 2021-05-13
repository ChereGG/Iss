package domain;

public class Utilizator extends Entity<Long>{

    private String username;
    private Integer hashing;
    private String nume;
    private String prenume;

    public Utilizator() {
    }

    public Utilizator(String username, Integer hashing, String nume, String prenume) {
        this.username = username;
        this.hashing = hashing;
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHashing() {
        return hashing;
    }

    public void setHashing(Integer hashing) {
        this.hashing = hashing;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}
