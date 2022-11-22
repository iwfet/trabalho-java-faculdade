package molde;

import enun.TipoSituacaoMesa;

import static enun.TipoSituacaoMesa.LIVRE;

public class Mesa {
    private Integer idMesa;
    private TipoSituacaoMesa situacao;
    private Integer maxCap;
    private String idGarcom;

    public Mesa(Integer idMes, Integer maxCap, String idGarcom) {
        this.idMesa = idMes;
        this.situacao = LIVRE;
        this.maxCap = maxCap;
        this.idGarcom = idGarcom;
    }



    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMes) {
        this.idMesa = idMes;
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

    public String getIdGarcom() {
        return idGarcom;
    }

    public void String (String idGarcom) {
        this.idGarcom = idGarcom;
    }

    public void setIdGarcom(String idGarcom) {
        this.idGarcom = idGarcom;
    }

    public void getTudoPrint(){
        System.out.println("---------MESA---------");
        System.out.println("ID garcom: "+this.idGarcom);
        System.out.println("Numero mesa: "+this.idMesa);
        System.out.println("Capadidade maxima: "+this.maxCap);
        System.out.println("Situaçao: "+this.situacao.getValue());
        System.out.println("\n");
    }

    public void printDadosMaisGarcom(String nome){
        System.out.println("---------MESA---------");
        System.out.println("ID garcom: "+this.idGarcom);
        System.out.println("Nome garcom: "+nome);
        System.out.println("Numero mesa: "+this.idMesa);
        System.out.println("Capadidade maxima: "+this.maxCap);
        System.out.println("Situaçao: "+this.situacao.getValue());
        System.out.println("\n");

    }

}
