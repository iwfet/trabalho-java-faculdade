package dto;

import enun.TipoSituacaoMesa;
import utils.anotecion.Colum;

public class MesasLivreGarcomResponsavel {
    private Integer idMesa;

    private TipoSituacaoMesa situacao;

    private Integer maxCap;

    private Long idGarcom;

    private String nome;


    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public TipoSituacaoMesa getSituacao() {
        return situacao;
    }

    public void setSituacao(TipoSituacaoMesa situacao) {
        this.situacao = situacao;
    }

    public Integer getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(Integer maxCap) {
        this.maxCap = maxCap;
    }

    public Long getIdGarcom() {
        return idGarcom;
    }

    public void setIdGarcom(Long idGarcom) {
        this.idGarcom = idGarcom;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
