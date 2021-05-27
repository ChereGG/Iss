package domain;

public class Penalizare extends Entity<Long> {

    private Long idUtilizator;
    private String dataPenalizarii;
    private String dataFinalizarii;
    private boolean ended;

    public Long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Long idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getDataPenalizarii() {
        return dataPenalizarii;
    }

    public String getDataFinalizarii() {
        return dataFinalizarii;
    }

    public void setDataFinalizarii(String dataFinalizarii) {
        this.dataFinalizarii = dataFinalizarii;
    }

    public void setDataPenalizarii(String dataPenalizarii) {
        this.dataPenalizarii = dataPenalizarii;
    }

    public Penalizare() {
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public Penalizare(Long idUtilizator, String dataPenalizarii, String dataFinalizarii, boolean ended) {
        this.idUtilizator = idUtilizator;
        this.dataPenalizarii = dataPenalizarii;
        this.dataFinalizarii = dataFinalizarii;
        this.ended = ended;
    }
}
