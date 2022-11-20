package com.company;

import molde.Garcom;
import molde.Mesa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

                case 9:{
                    cadastroGracom();
                    break;
                }
                case 10:{
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
        System.out.println("4 - Busca mesa pela capacidade de clientes");
        System.out.println("5 - Relatorio mesas");
        System.out.println("5 - Busca mesas ocupada de X garcom");
        System.out.println("9 - Cadatrar Garcom");
        System.out.println("10 - Lista Garcom");
        System.out.println("0 - Sair");
    }

    private static void cadastroMesa(){
        System.out.println("Numero mesa");
        int numeroMesa;
        numeroMesa = sc.nextInt();
        long count = BD_Mesa.stream().filter((value) -> value.getIdMesa() == numeroMesa).count();
        if (count == 0){
            System.out.println("ID garcom");
            var idGarcom = sc.next();
            if(validaSeIdGarcomExiste(idGarcom)){
                System.out.println("Numero capacidade mesa");
                int capacidadeMesa = sc.nextInt();
                BD_Mesa.add(new Mesa(numeroMesa,capacidadeMesa,idGarcom));
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
            BD_Mesa.forEach((value)->{
                if (value.getIdMesa() == mesa) {
                    value.getTudoPrint();
                }
            });
        }else{
            System.out.println("Mesa não existe");
        }

    }
    private static void buscaMesaCapacidade(){
        System.out.println("Informe a capacidade de clienets em Mesa");
        int capaciade = sc.nextInt();
        BD_Mesa.forEach((value)->{
            if (value.getMaxCap() >= capaciade) {
                value.getTudoPrint();
            }
        });
    }


    private static void relatorioMesaTodas(){
        BD_Mesa.forEach(Mesa::getTudoPrint);
    }

    private static  void relatorioGarcomMesaOcupada(){

    }


    private static void cadastroGracom(){
        System.out.println("Nome garcom");
        final String nomeGarcom = sc.next();

        System.out.println("CPF garcom");
        final String cpf = sc.next();

        Long count = BD_Garcom.stream().filter((value) -> Objects.equals(value.getCpf(), cpf)).count();

        System.out.println(count);
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
            final String sexo = sc.next();

            System.out.println("Salario garcom");
            double salario = sc.nextDouble();

            BD_Garcom.add(new Garcom(BD_Garcom,nomeGarcom,cpf,date,email,telefone,sexo,salario));
            System.out.println("Cadastrado garcom \n");
        }else{
            System.out.println("garcom já exite com este cpf");
        }


    }


    private static void listaGarcom(){
        BD_Garcom.forEach((value)->{
            System.out.println("id garcom:"+value.getIdGarcom());
            System.out.println(value.getNome());
//            List<Mesa> mesasRsponsavel = value.getMesasRsponsavel();
//            mesasRsponsavel.forEach((mesa) -> System.out.println(mesa.getSituacao()));

        });


    }


    private static boolean validaSeIdGarcomExiste( String idSuporto){
        long idgarcom = BD_Garcom.stream().filter((value) -> Objects.equals(value.getIdGarcom(), idSuporto)).count();
        if(idgarcom== 0)return false;
        return true;
    }


    private static boolean validaSeIdMesaExiste( Integer idSuporto){
        long idgarcom = BD_Mesa.stream().filter((value) -> Objects.equals(value.getIdMesa(), idSuporto)).count();
        if(idgarcom== 0)return false;
        return true;
    }

    private static void start(){
        Garcom garcom1 = new Garcom(BD_Garcom, "joao", "133", new Date(), "j13", "34", "f", 20.00);
        Garcom garcom2 = new Garcom(BD_Garcom, "nuno", "565955", new Date(), "jsdfsdf13", "34", "f", 20.00);
        Garcom garcom3 = new Garcom(BD_Garcom, "fabio", "5848", new Date(), "sgdsgg", "34", "f", 20.00);
        BD_Garcom.add(garcom1);
        BD_Garcom.add(garcom2);
        BD_Garcom.add(garcom3);


        Mesa mesa1 = new Mesa(1, 10, garcom2.getIdGarcom());
        Mesa mesa2 = new Mesa(2, 5, garcom1.getIdGarcom());
        Mesa mesa3 = new Mesa(3, 2, garcom1.getIdGarcom());
        Mesa mesa4 = new Mesa(4, 20, garcom3.getIdGarcom());


        BD_Mesa.add(mesa1);
        BD_Mesa.add(mesa2);
        BD_Mesa.add(mesa3);
        BD_Mesa.add(mesa4);
    }



}
