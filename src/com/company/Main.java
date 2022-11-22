/*
 * Participantes:
 * Wanderson da Silva Junior RA: 202217583
 * Geovana Oliveira RA:202218173
 *  Fábio Henrique Farias da Silva RA: 202218886
 * João Vitor Vieira Claro RA:20221697
 */

package com.company;

import enun.TipoSexo;
import enun.TipoSituacaoMesa;
import molde.Garcom;
import molde.Mesa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;


public class Main {

    private static List<Mesa> BD_Mesa = new ArrayList<>();
    private static List<Garcom> BD_Garcom = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        start();
        int operacao = 1;
        while(operacao != 0) {
            mostraMenu();
            System.out.print("\nEntre com a operação desejada:");
            operacao = sc.nextInt();
            switch (operacao) {
                case 1:{
                    cadastroMesa();
                    break;
                }
                case 2:{
                    removeMesa();
                    break;
                }
                case 3:{
                    buscaMesaNumero();
                    break;
                }
                case 4:{
                    buscaMesaCapacidade();
                    break;
                }
                case 5:{
                    relatorioMesaTodas();
                    break;
                }
                case 6:{
                    relatorioGarcomMesaOcupada();
                    break;
                }

                case 7:{
                    buscarGarcomEmail();
                    break;
                }
                case 8:{
                    buscaGarcomResponsavelMesa();
                    break;
                }

                case 9:{
                    relatorioMesasGarcom();
                    break;
                }
                case 10:{
                    removerGarcom();
                    break;
                }

                case 11:{
                    atualizaSituacaoMesa();
                    break;
                }

                case 12:{
                    relatorioMesaLivreEGarcom();
                    break;
                }
                case 13:{
                    cadastroGracom();
                    break;
                }
                case 14:{
                    listaGarcom();
                    break;
                }
                case 0:
                    System.out.println("PROGRAMA ENCERRADO");
                    return;
            }
        }
    }




    private static void mostraMenu() {
        System.out.println("O que deseja fazer:");
        System.out.println("1 - Cadatrar Mesa");
        System.out.println("2 - Remove Mesa");
        System.out.println("3 - Busca mesa pelo numero");
        System.out.println("4 - Relatorio mesa pela capacidade de clientes");
        System.out.println("5 - Relatorio mesas");
        System.out.println("6 - Relatorio mesas ocupadas X garcom");
        System.out.println("7 - Busca garcom email");
        System.out.println("8 - Busca garcom responsavel por mesa");
        System.out.println("9 - Relatorio mesas garcom ");
        System.out.println("10 - Remove garcom ID");
        System.out.println("11 - Atualiza situaçao mesa");
        System.out.println("12 - Relatorio mesas livre");
        System.out.println("13 - Cadatrar Garcom");
        System.out.println("14 - Lista Garcom");
        System.out.println("0 - Sair");
    }

    private static void cadastroMesa(){
        System.out.println("Numero mesa");
        var numeroMesa = sc.nextInt();
        if (validaSeIdMesaExiste(numeroMesa)){
            System.out.println("ID garcom");
            var idGarcom = sc.next();
            if(validaSeIdGarcomExiste(idGarcom)){
                System.out.println("Numero capacidade mesa");
                int capacidadeMesa = sc.nextInt();
                Mesa mesa = new Mesa(numeroMesa, capacidadeMesa, idGarcom);
                BD_Mesa.add(mesa);
                BD_Garcom.stream().filter(value->value.getIdGarcom().equals(idGarcom))
                                .forEach(garcom -> garcom.addMesaResponsavel(mesa));
                System.out.println("Cadastrado");
            }else{
                System.out.println("ID Garcom n existe");
            }
        }else{
            System.out.println("Numero mesa já sendo utilizada");
        }
    }

    private static void removeMesa(){
        System.out.println("Imforme numero mesa");
        int numeroMesaSuposta = sc.nextInt();
        if(validaSeIdMesaExiste(numeroMesaSuposta)){
            for(int i = 0; i < BD_Mesa.size(); i++){
                Mesa p = BD_Mesa.get(i);
                if(p.getIdMesa().equals(numeroMesaSuposta)){BD_Mesa.remove(p);break;}
            }
            BD_Garcom.forEach((value)->value.removeMesa(numeroMesaSuposta));
            System.out.println("Mesa removida com sucesso");
        }else{
            System.out.println("Mesa não existe");
        }
    }

    private static void buscaMesaNumero(){
        System.out.println("Infrome o numero Mesa");
        int mesa = sc.nextInt();
        if(validaSeIdMesaExiste(mesa)){
            BD_Mesa.stream()
                    .filter(valur->valur.getIdMesa().equals(mesa))
                    .forEach(Mesa::getTudoPrint);
        }else{
            System.out.println("Mesa não existe");
        }

    }
    private static void buscaMesaCapacidade(){
        System.out.println("Informe a capacidade de clienets em Mesa");
        int capaciade = sc.nextInt();
        BD_Mesa.stream()
                .filter(value->value.getMaxCap()>= capaciade)
                .forEach(Mesa::getTudoPrint);
    }


    private static void relatorioMesaTodas(){
        BD_Mesa.forEach(Mesa::getTudoPrint);
    }

    private static  void relatorioGarcomMesaOcupada(){
        System.out.println("ID garcom");
        var idGarcom = sc.next();
        if(validaSeIdGarcomExiste(idGarcom)){
             BD_Garcom.stream()
                    .filter((value) -> Objects.equals(value.getIdGarcom(), idGarcom))
                    .forEach((mesas) -> {
                        final List<Mesa> mesasResponsavel = mesas.getMesasRsponsavel();
                        if (mesasResponsavel.size()!=0){
                            final var listMesas = mesasResponsavel
                                    .stream()
                                    .filter((mesa) -> mesa.getSituacao().equals(TipoSituacaoMesa.OCUPADO.getValue()))
                                    .collect(toList());
                            if(listMesas.size()!= 0){
                                listMesas.forEach(Mesa::getTudoPrint);
                            }else {
                                System.out.println("Garcom n tem mesas ocupada");
                            }
                        }else {
                            System.out.println("Garcom n tem mesas cadastrada para ele");
                        }
                    });
        }else{
            System.out.println("ID Garcom n existe");
        }
    }



    private static void buscarGarcomEmail() {
        System.out.println("Digite o E-mail do garçom: ");
        String email = sc.next();
        if(validaSeEmailGarcomExiste(email)) {
            BD_Garcom.stream().filter((value) -> value.getEmail().equals(email))
                    .forEach((Garcom::printGarcomIdNomeMesas));
        }else{
            System.out.println("Garçom com esse E-mail não cadastrado");
        }

    }


    private static void buscaGarcomResponsavelMesa() {
        System.out.println("Infrome numero mesa: ");
        var mesa = sc.nextInt();
        if (validaSeIdMesaExiste(mesa)) {
            BD_Mesa.stream().filter(value -> value.getIdMesa().equals(mesa))
                    .map(Mesa::getIdGarcom)
                            .forEach((garcom)->{
                                BD_Garcom.stream()
                                        .filter(value->value.getIdGarcom().equals(garcom))
                                        .forEach(Garcom::printNome);
                            });
        } else {
            System.out.println("Mesa nao existe");
        }
    }





    private static void relatorioMesasGarcom() {
        System.out.println("ID garcom");
        var idGarcom = sc.next();
        if(validaSeIdGarcomExiste(idGarcom)){
        BD_Garcom.stream()
                .filter(value->value.getIdGarcom().equals(idGarcom))
                .forEach(Garcom::relaotorioGarcom);

        }else {
            System.out.println("Mesa nao existe");
        }

    }



    private static void removerGarcom() {
        System.out.println("ID garcom");
        var idGarcom = sc.next();
        if(validaSeIdGarcomExiste(idGarcom)){
            for(int i = 0; i < BD_Garcom.size(); i++){
                Garcom p = BD_Garcom.get(i);
                if(p.getIdGarcom().equals(idGarcom)){
                    BD_Garcom.remove(p);
                    break;
                }
            }
            for (Mesa mesa : BD_Mesa) {
                if (mesa.getIdGarcom().equals(idGarcom)) {
                    mesa.setIdGarcom(null);
                }
            }
            System.out.println("Garcom removido com sucesso");
        }else{
            System.out.println("Garçom não existe");
        }
    }


    private static void atualizaSituacaoMesa() {
        System.out.println("Infrome numero mesa: ");
        var mesa = sc.nextInt();
        if (validaSeIdMesaExiste(mesa)) {
            mostraMineMenu();
            System.out.print("\nEntre com a operação desejada:");
            var operacao = sc.nextInt();
            switch (operacao) {
                case 1:{
                    BD_Mesa.stream()
                            .filter(value->value.getIdMesa().equals(mesa))
                            .forEach(mesas -> {
                                mesas.setSituacao(TipoSituacaoMesa.LIVRE);
                                BD_Garcom.stream()
                                        .filter(value->value.getIdGarcom().equals(mesas.getIdGarcom()))
                                        .forEach(garcom -> garcom.getMesasRsponsavel()
                                                .stream().filter(value->value.getIdMesa().equals(mesas.getIdMesa()))
                                                .forEach(value->value.setSituacao(TipoSituacaoMesa.LIVRE))
                                        );
                            });
                    break;
                }
                case 2:{
                    BD_Mesa.stream()
                            .filter(value->value.getIdMesa().equals(mesa))
                            .forEach(mesas -> {
                                mesas.setSituacao(TipoSituacaoMesa.OCUPADO);
                                BD_Garcom.stream()
                                        .filter(value->value.getIdGarcom().equals(mesas.getIdGarcom()))
                                        .forEach(garcom -> garcom.getMesasRsponsavel()
                                                .stream().filter(value->value.getIdMesa().equals(mesas.getIdMesa()))
                                                .forEach(value->value.setSituacao(TipoSituacaoMesa.OCUPADO))
                                        );
                            });
                    break;
                }
                case 3:{
                    BD_Mesa.stream()
                            .filter(value->value.getIdMesa().equals(mesa))
                            .forEach(mesas -> {
                                mesas.setSituacao(TipoSituacaoMesa.RESERVADO);
                                BD_Garcom.stream()
                                        .filter(value->value.getIdGarcom().equals(mesas.getIdGarcom()))
                                        .forEach(garcom -> garcom.getMesasRsponsavel()
                                                .stream().filter(value->value.getIdMesa().equals(mesas.getIdMesa()))
                                                .forEach(value->value.setSituacao(TipoSituacaoMesa.RESERVADO))
                                        );
                            });
                    break;
                }

            }
            }else {
            System.out.println("Mesa nao existe");
        }

    }

    private static void mostraMineMenu() {
        System.out.println("Deseja atualizar q tipo de siatucao Mesa:");
        System.out.println("1 - Atualizar situacao mesa livre");
        System.out.println("2 - Atualizar situacao mesa ocupada");
        System.out.println("3 - Atualizar situacao mesa reservado");
    }


    private static  void relatorioMesaLivreEGarcom() {
        BD_Mesa.stream().filter(value->value.getSituacao().equals(TipoSituacaoMesa.LIVRE.getValue()))
                .forEach(mesa -> {
                    BD_Garcom.stream().filter(value->value.getIdGarcom().equals(mesa.getIdGarcom()))
                            .forEach(garcom -> {
                                mesa.printDadosMaisGarcom(garcom.getNome());
                            });
                });
    }

    private static void cadastroGracom(){
        System.out.println("Nome garcom");
        final String nomeGarcom = sc.next();

        System.out.println("CPF garcom");
        final String cpf = sc.next();

        var count = BD_Garcom.stream().filter((value) -> Objects.equals(value.getCpf(), cpf)).count();
        if(count == 0) {
            System.out.println("Dara de nascimento garcom, formato dd-MM-yyyy");
            String dataEntrada = sc.next();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            try {
                date = formatter.parse(dataEntrada);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Email garcom");
            final String email = sc.next();

            System.out.println("Telefone garcom");
            final String telefone = sc.next();

            System.out.println("Sexo garcom");
            System.out.println("1 - Masculino");
            System.out.println("2 - Feminino");
            final var value = sc.nextInt();
            TipoSexo sexo = null;
            if(value==1){
                sexo=TipoSexo.MASCULINO;
            }else if(value ==2){
                sexo=TipoSexo.FEMININO;
            }
            if(sexo != null) {
                System.out.println("Salario garcom XX,XX");
                double salario = sc.nextDouble();

                BD_Garcom.add(new Garcom(BD_Garcom, nomeGarcom, cpf, date, email, telefone, sexo, salario));
                System.out.println("Cadastrado garcom \n");
            }else{
                System.out.println("Erro ao cadastar sexo n valido");
            }
        }else{
            System.out.println("garcom já exite com este cpf");
        }


    }


    private static void listaGarcom(){
        BD_Garcom.forEach(Garcom::printGarcomIdNomeMesas);

    }


    private static boolean validaSeIdGarcomExiste( String idSuporto){
       return BD_Garcom.stream().anyMatch(value -> Objects.equals(value.getIdGarcom(), idSuporto));
    }

    private static boolean validaSeEmailGarcomExiste( String email){
        return BD_Garcom.stream().anyMatch((value) -> Objects.equals(value.getEmail(), email));

    }


    private static boolean validaSeIdMesaExiste( Integer idSuporto){
        return BD_Mesa.stream().anyMatch((value) -> Objects.equals(value.getIdMesa(), idSuporto));
    }

    private static void start(){
        System.out.println("-----Garcom ja cadastrados no sistema-----");
        Garcom garcom1 = new Garcom(BD_Garcom, "joao", "133", new Date(), "j13", "34", TipoSexo.MASCULINO, 20.00);
        Garcom garcom2 = new Garcom(BD_Garcom, "nuno", "565955", new Date(), "jsdfsdf13", "34", TipoSexo.MASCULINO, 20.00);
        Garcom garcom3 = new Garcom(BD_Garcom, "fabio", "5848", new Date(), "sgdsgg", "34", TipoSexo.MASCULINO, 20.00);

        Mesa mesa1 = new Mesa(1, 10, garcom2.getIdGarcom());
        Mesa mesa2 = new Mesa(2, 5, garcom1.getIdGarcom());
        Mesa mesa3 = new Mesa(3, 2, garcom1.getIdGarcom());
        Mesa mesa4 = new Mesa(4, 20, garcom3.getIdGarcom());


        mesa2.setSituacao(TipoSituacaoMesa.OCUPADO);
        garcom2.addMesaResponsavel(mesa1);
        garcom1.addMesaResponsavel(mesa2);
        garcom1.addMesaResponsavel(mesa3);
        garcom3.addMesaResponsavel(mesa4);


        BD_Garcom.add(garcom1);
        BD_Garcom.add(garcom2);
        BD_Garcom.add(garcom3);


        BD_Mesa.add(mesa1);
        BD_Mesa.add(mesa2);
        BD_Mesa.add(mesa3);
        BD_Mesa.add(mesa4);

    }

}
