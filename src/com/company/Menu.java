package com.company;

import banco.Start;
import enun.TipoSexo;
import molde.Garcom;
import molde.Mesa;
import repository.GarcomRepository;
import repository.MesaRepository;
import utils.LancaMensagem;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static enun.TipoSexo.FEMININO;
import static enun.TipoSexo.MASCULINO;

public class Menu extends LancaMensagem {

    private final GarcomRepository garcomRepository;
    private final MesaRepository mesaRepository;

    public Menu(GarcomRepository garcomRepository, MesaRepository mesaRepository) {
        this.garcomRepository = garcomRepository;
        this.mesaRepository = mesaRepository;
    }

    public void menu(){
        new Start();
        int operacao = 1;
        while(operacao != 0) {
            operacao = mostraMenu();
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
//                    buscaMesaCapacidade();
                    break;
                }
                case 5:{
//                    relatorioMesaTodas();
                    break;
                }
                case 6:{
//                    relatorioGarcomMesaOcupada();
                    break;
                }

                case 7:{
//                    buscarGarcomEmail();
                    break;
                }
                case 8:{
//                    buscaGarcomResponsavelMesa();
                    break;
                }

                case 9:{
//                    relatorioMesasGarcom();
                    break;
                }
                case 10:{
//                    relatorioMesaLivreEGarcom();
                    break;
                }
                case 11:{
//                    relatorioQuantidadeMesaGarcons();
                    break;
                }
                case 12:{
//                    removerGarcom();
                    break;
                }

                case 13:{
//                    atualizaSituacaoMesa();
                    break;
                }
                case 14:{
//                    atualizaCapacidadeMesa();
                    break;
                }
                case 15:{
//                    atualizaGarcomMesa();
                    break;
                }

                case 16:{
                    cadastroGracom();
                    break;
                }
                case 17:{
                    listaGarcom();
                    break;
                }
                case 0:
                    System.out.println("PROGRAMA ENCERRADO");
                    return;
            }
        }
    }


    private int mostraMenu() {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção:" +
                    "\n1 - Realizar cadastro Mesa " +
                    "\n2 - Realizar a exclusão de mesa " +
                    "\n3 - Buscar uma mesa pelo número " +
                    "\n4 - Relatório com todas as mesas com a capacidade >= a uma dada quantidade de clientes " +
                    "\n5 - Relatório com todas as mesas " +
                    "\n6 - Relatório com todas as mesas que um garçom atende e que estão ocupadas " +
                    "\n7 - Buscar garçom pelo e-mail " +
                    "\n8 - Busca nome do garçom responsável por uma dada mesa " +
                    "\n9 - Relatório com todas as mesas que um garçom atende " +
                    "\n10 - Relatório com todas as mesas livres e o nome do garçom responsável pela mesa " +
                    "\n11 - Relatório da quantidade de mesas que cada garçom está atendendo " +
                    "\n12 - Remove garcom ID " +
                    "\n13 - Atualiza situaçao mesa " +
                    "\n14 - Atualiza capacidade mesa " +
                    "\n15 - Atualiza garcon mesa " +
                    "\n16 - Cadatrar Garcom " +
                    "\n17 - Lista Garcom " +
                    "\n0 - Sair "
            ));
        }catch (Exception e){
            return  0;
        }
    }






    private void cadastroMesa()  {
        var numeroMesa = Integer.parseInt(JOptionPane.showInputDialog("Numero mesa"));
        if (mesaRepository.findById(numeroMesa).isEmpty()){
            var idGarcom = Long.parseLong(JOptionPane.showInputDialog("ID garcom"));
            if(garcomRepository.findById(idGarcom).isPresent()){
                int capacidadeMesa = Integer.parseInt(JOptionPane.showInputDialog("Numero capacidade mesa"));
                final boolean save = mesaRepository.save(new Mesa(numeroMesa, capacidadeMesa, idGarcom).generateInsert());
                if(save)lancaSucesso("Cadastrado Mesa ");
            }else{
                lancaErro("ID Garcom n existe");
            }
        }else{
            lancaErro("Numero mesa já sendo utilizada");
        }
    }


    private  void removeMesa(){
        var numeroMesa = Integer.parseInt(JOptionPane.showInputDialog("Numero mesa"));

        if (mesaRepository.findById(numeroMesa).isPresent()){
            final boolean save = mesaRepository.deleteById(numeroMesa);
            if(save)lancaSucesso("Mesa removida com sucesso");
        }else{
            lancaErro("Numero mesa nao existe");
        }
    }


    private  void buscaMesaNumero(){
        var numeroMesa = Integer.parseInt(JOptionPane.showInputDialog("Numero mesa"));
        final Optional<Mesa> mesa = mesaRepository.findById(numeroMesa);
        if(mesa.isPresent()){


            var pane = new JPanel();
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
            StringBuilder sb = new StringBuilder();

            sb.append("ID mesa: ").append(mesa.get().getIdMesa()).append("\n");
            pane.add(new JLabel(sb.toString()));
            sb.delete(0, sb.length());
            sb.append("capacidade maxima: ").append(mesa.get().getMaxCap()).append("\n");
            pane.add(new JLabel(sb.toString()));
            sb.delete(0, sb.length());
            sb.append("situacao: ").append(mesa.get().getSituacao()).append("\n");
            pane.add(new JLabel(sb.toString()));
            sb.delete(0, sb.length());
            sb.append("ID garcom: ").append(mesa.get().getIdGarcom()).append("\n");
            pane.add(new JLabel(sb.toString()));
            sb.delete(0, sb.length());
            sb.append("\n");
            pane.add(new JLabel(sb.toString()));
            sb.delete(0, sb.length());
            sb.append("\n");
            pane.add(new JLabel(sb.toString()));
            sb.delete(0, sb.length());
            JOptionPane.showMessageDialog(new JFrame(), pane, "Numbers", JOptionPane.PLAIN_MESSAGE);

        }else{
            lancaErro("Mesa n existe");
        }


    }








    private  void cadastroGracom(){
        final var nomeGarcom = JOptionPane.showInputDialog("Nome garcom");
        final var cpf = JOptionPane.showInputDialog("CPF garcom");
        if(garcomRepository.findByCpf(cpf)) {
            final var dataEntrada = JOptionPane.showInputDialog("Data de nascimento garcom, formato dd-MM-yyyy");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            try {
                date = formatter.parse(dataEntrada);
            } catch (ParseException e) {
                lancaErro(e.toString());
            }
            final var email = JOptionPane.showInputDialog("Email garcom");
            final String telefone = JOptionPane.showInputDialog("Telefone garcom");
            final var value =Integer.parseInt(JOptionPane.showInputDialog("Sexo garcom"+
                    "\n1 - Masculino "+"\n2 - Feminino"));
            TipoSexo sexo = null;
            if(value==1){
                sexo=MASCULINO;
            }else if(value ==2){
                sexo=FEMININO;
            }

            if(sexo != null) {
                Integer salario = Integer.parseInt(JOptionPane.showInputDialog("Salario garcom sem os centavos"));

                final boolean save = garcomRepository.save(new Garcom(nomeGarcom, cpf, date, email, telefone, sexo, salario).generateInsert());
                if(save)lancaSucesso("Cadastrado garcom ");

            }else{
                lancaErro("Erro ao cadastar sexo n valido");
            }
        }else{
            lancaErro("garcom já exite com este cpf");
        }

    }



    private  void listaGarcom(){
        var pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        StringBuilder sb = new StringBuilder();

        sb.append("-----GARCOMS-----\n");
        pane.add(new JLabel(sb.toString()));
        sb.delete(0, sb.length());
       garcomRepository.findAll().forEach((value)->{

           sb.append("IdGarcom: ").append(value.getIdGarcom()).append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("nome: ").append(value.getNome()).append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("cpf: ").append(value.getCpf()).append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("data_nascimento: ").append(value.convetDateAndString()).append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("email: ").append(value.getEmail()).append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("telefone: ").append(value.getTelefone()).append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("sexo: ").append(value.getSexo()).append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("salario: ").append(value.getSalario());
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());
           sb.append("\n");
           pane.add(new JLabel(sb.toString()));
           sb.delete(0, sb.length());


       });
        JOptionPane.showMessageDialog(new JFrame(), pane, "Numbers", JOptionPane.PLAIN_MESSAGE);
    }






}
