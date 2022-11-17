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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int operacao = 1;
        while(operacao != 0) {
            mostraMenu();
            System.out.print("\nEntre com a operação desejada:");
            operacao = sc.nextInt();
            switch (operacao) {
                case 1:{
                    cadastroMesa(sc,BD_Mesa,BD_Garcom);
                    break;
                }
                case 2:{
                    cadastroGracom(sc,BD_Garcom);
                    break;
                }
                case 3:{
                    listaGarcom(BD_Garcom);
                    break;
                }
                case 4:{
                    cadastraMesaGarcom(sc,BD_Mesa,BD_Garcom);
                    break;
                }
                case 8:
                    System.out.println("PROGRAMA ENCERRADO");
                    return;
            }
        }



    }
    private static void mostraMenu() {
        System.out.println("O que deseja fazer:");
        System.out.println("1 - Cadatrar Mesa");
        System.out.println("2 - Cadatrar Garcom");
        System.out.println("3 - Lista garcom tudo");
        System.out.println("4 - Busca mesa pela capacidade de clientes");
        System.out.println("0 - Sair");
    }
    private static void cadastroMesa(Scanner sc, List<Mesa> mesas, List<Garcom> garcom){
        System.out.println("Numero mesa");
        int numeroMesa;
        numeroMesa = sc.nextInt();
        long count = mesas.stream().filter((value) -> value.getIdMes() == numeroMesa).count();
        if (count == 0){
            System.out.println("Numero capacidade mesa");
            int capacidadeMesa = sc.nextInt();
            mesas.add(new Mesa(numeroMesa,capacidadeMesa));
            System.out.println("Cadastrado");
        }else{
            System.out.println("Numero mesa já sendo utilizada");
        }
    }

    private static void cadastraMesaGarcom(Scanner sc, List<Mesa> mesas, List<Garcom> garcom){
        System.out.println("Numero mesa");
        int numeroMesa = sc.nextInt();
        Collection<Mesa> collect = mesas.stream().filter((value) -> value.getIdMes() == numeroMesa).collect(Collectors.toCollection());
        if(valor !=0 ){
            
        }
        


    }

    private static void cadastroGracom(Scanner sc, List<Garcom> garcom){
        System.out.println("Nome garcom");
        final String nomeGarcom = sc.next();

        System.out.println("CPF garcom");
        final String cpf = sc.next();

        Long count = garcom.stream().filter((value) -> Objects.equals(value.getCpf(), cpf)).count();

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

            garcom.add(new Garcom(garcom,nomeGarcom,cpf,date,email,telefone,sexo,salario));
            System.out.println("Cadastrado garcom \n");
        }else{
            System.out.println("garcom já exite com este cpf");
        }


    }


    private static void listaGarcom(List<Garcom> garcom){
        garcom.forEach((value)->{
            System.out.println("id garcom:"+value.getIdGarcom());
            System.out.println(value.getNome());
            value.getMesasRsponsavel().forEach((mesa)->{
                System.out.println(mesa.getIdMes());
                System.out.println(mesa.getMaxCap());
                System.out.println(mesa.getSituacao());
            });
        });


    }



    private void start(){

    }
}
