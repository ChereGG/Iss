package domain;

public class Inchiriere extends Entity<Long>{

    private Long idCarte;
    private Long idUtilizator;
    private boolean returned;

    public Inchiriere() {
    }

    public Inchiriere(Long idCarte, Long idUtilizator, boolean returned) {
        this.idCarte = idCarte;
        this.idUtilizator = idUtilizator;
        this.returned = returned;
    }

    public Long getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Long idCarte) {
        this.idCarte = idCarte;
    }

    public Long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Long idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }




}
