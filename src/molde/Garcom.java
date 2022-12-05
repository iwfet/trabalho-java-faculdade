package molde;

import enun.TipoSexo;
import utils.anotecion.Colum;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.String.format;

public class Garcom {

    @Colum(name="id_garcom")
    private Long idGarcom;

    @Colum(name = "nome")
    private String nome;

    @Colum(name="cpf")
    private String cpf;

    @Colum(name="data_nascimento")
    private Date dataNascimento;

    @Colum(name="email")
    private String email;

    @Colum(name="telefone")
    private String telefone;

    @Colum(name="sexo")
    private TipoSexo sexo;

    @Colum(name="salario")
    private Integer salario;

    public Garcom() {
        super();
    }
    public Garcom( String nome, String cpf, Date dataNascimento, String email, String telefone, TipoSexo sexo, Integer salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.salario = salario;

    }

    public Long getIdGarcom() {
        return this.idGarcom;
    }

    public void setIdGarcom(Long idGarcom) {
        this.idGarcom = idGarcom;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return this.sexo.getValue();
    }

    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }

    public Integer getSalario() {
        return this.salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garcom garcom = (Garcom) o;
        return Objects.equals(idGarcom, garcom.idGarcom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGarcom);
    }





    public String generateInsert(){

        System.out.println(this.salario);
        return format("INSERT INTO garcom" +
                "(nome, cpf, data_nascimento, email, telefone, sexo, salario)" +
                "VALUES('%s','%s','%s','%s','%s','%s',%d);"
                ,this.nome
                ,this.cpf
                ,this.convetDateAndString()
                ,this.email
                ,this.telefone
                ,this.sexo.getValue()
                ,this.salario);
    }

    public String convetDateAndString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.dataNascimento);
    }






}
