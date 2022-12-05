package molde;

import enun.TipoSituacaoMesa;
import utils.anotecion.Colum;

import static enun.TipoSituacaoMesa.LIVRE;
import static java.lang.String.format;

public class Mesa {
    @Colum(name = "id_mesa")
    private Integer idMesa;

    @Colum(name = "situacao")
    private TipoSituacaoMesa situacao;

    @Colum(name = "max_cap")
    private Integer maxCap;

    @Colum(name = "id_garcom")
    private Long idGarcom;


    public Mesa() {super();}

    public Mesa(Integer idMes, Integer maxCap, Long idGarcom) {
        this.idMesa = idMes;
        this.situacao = LIVRE;
        this.maxCap = maxCap;
        this.idGarcom = idGarcom;
    }



    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public String getSituacao() {
        return situacao.getValue();
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



    public String generateInsert(){

        return format("INSERT INTO mesa" +
                            "(id_mesa,situacao,max_cap,id_garcom)" +
                            "VALUES(%d,'%s',%d,%d);"
                    ,this.idMesa
                    ,this.situacao.getValue()
                    ,this.maxCap
                    ,this.idGarcom);



    }

}
