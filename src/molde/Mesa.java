package molde;

public class Mesa {
    private Integer idMes;
    private String situacao;
    private Integer maxCap;


    public Mesa(Integer idMes, Integer maxCap) {
        this.idMes = idMes;
        this.situacao = "livre";
        this.maxCap = maxCap;

    }


    public Integer getIdMes() {
        return idMes;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(Integer maxCap) {
        this.maxCap = maxCap;
    }


}
