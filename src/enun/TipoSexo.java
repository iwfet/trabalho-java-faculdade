package enun;

public enum TipoSexo {
    MASCULINO("MASCULINO","masculino"),

    FEMININO("FEMININO","femino")
    ;

    private final String descricao;
    private final String value;

    TipoSexo(String descricao, String value) {
        this.descricao = descricao;
        this.value = value;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getValue() {
        return value;
    }


}
