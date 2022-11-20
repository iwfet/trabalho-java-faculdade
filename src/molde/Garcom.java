package molde;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Garcom {
    private String idGarcom;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String telefone;
    private String sexo;
    private Double salario;
    private List<Mesa> mesasRsponsavel;


    public Garcom(List<Garcom> garcom, String nome, String cpf, Date dataNascimento, String email, String telefone, String sexo, Double salario) {
        this.idGarcom = generateIdGarcom(garcom);
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.salario = salario;
    }

    public String getIdGarcom() {
        return idGarcom;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public List<Mesa> getMesasRsponsavel() {
        return mesasRsponsavel;
    }

    public void setMesasRsponsavel(List<Mesa> mesasRsponsavel) {
        this.mesasRsponsavel = mesasRsponsavel;
    }

    private String generateIdGarcom(List<Garcom> garcom){
        while(true) {
            String uuid =  UUID.randomUUID().toString().substring(0,6);
            long count = garcom.stream().filter((value) -> Objects.equals(value.getIdGarcom(), uuid)).count();
            if(count == 0){
                System.out.println("ID garcom: "+uuid);
                return  uuid;
            }
        }
    }

    public void removeMesa(final Integer idMesa){
        for(int i = 0; i < this.mesasRsponsavel.size(); i++){
            Mesa p = this.mesasRsponsavel.get(i);
            if(p.getIdMesa().equals(idMesa)){this.mesasRsponsavel.remove(p);break;}
        }
    }

    public void printMesas(){
        for (Mesa value : this.mesasRsponsavel) {
            value.getTudoPrint();
        }
    }
}
