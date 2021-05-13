package domain;

public class Carte extends Entity<Long>{

    private String titlu;
    private String autor;
    private String editura;
    private String data;

    public Carte() {
    }

    public Carte(String titlu, String autor, String editura, String data) {
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.data = data;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
