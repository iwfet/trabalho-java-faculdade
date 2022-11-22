package molde;

import enun.TipoSexo;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Garcom {
    private String idGarcom;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String telefone;
    private TipoSexo sexo;
    private Double salario;
    private List<Mesa> mesasResponsavel;


    public Garcom(List<Garcom> garcom, String nome, String cpf, Date dataNascimento, String email, String telefone, TipoSexo sexo, Double salario) {
        this.idGarcom = generateIdGarcom(garcom);
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.salario = salario;
        this.mesasResponsavel = new ArrayList<>();
    }

    public String getIdGarcom() {
        return this.idGarcom;
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

    public Double getSalario() {
        return this.salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public List<Mesa> getMesasRsponsavel() {
        return this.mesasResponsavel;
    }

    public void setMesasRsponsavel(List<Mesa> mesasRsponsavel) {
        this.mesasResponsavel = mesasRsponsavel;
    }

    public void addMesaResponsavel(Mesa mesa){
        this.mesasResponsavel.add(mesa);

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
        for(int i = 0; i < this.mesasResponsavel.size(); i++){
            Mesa p = this.mesasResponsavel.get(i);
            if(p.getIdMesa().equals(idMesa)){this.mesasResponsavel.remove(p);break;}
        }
    }

    public void printMesas(){
        for (Mesa value : this.mesasResponsavel) {
            value.getTudoPrint();
        }
    }

    public void printGarcomIdNomeMesas(){
        System.out.println("-----Garcom-----");
        System.out.println("Nome: "+this.nome);
        System.out.println("ID: "+this.idGarcom);
        System.out.println("-----Responsavel por mesas-----");
        this.printMesas();
    }


    public void printNome(){
        System.out.println("-----Garcom-----");
        System.out.println("Nome: "+this.nome);
        System.out.println("\n");
    }

    public void relaotorioGarcom(){
        System.out.println("Numeros de Mesas responsavel: "+this.mesasResponsavel.size());
        this.printMesas();
    }


}
