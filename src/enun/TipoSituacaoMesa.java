package enun;

public enum TipoSituacaoMesa {
    LIVRE("LIVRE","livre"),

    OCUPADO("OCUPADO","ocupado"),

    RESERVADO("RESERVADO","reservado")

    ;

    public String getDescricao() {
        return descricao;
    }

    public String getValue() {
        return value;
    }

    private final String descricao;
    private final String value;

    TipoSituacaoMesa(String descricao, String value) {
        this.descricao = descricao;
        this.value = value;
    }


}
