package banco;

import javax.swing.*;
import java.sql.SQLException;

public class Start extends Transactions {
    public Start() {
        testeConexao();
        startTabelaGarcom();
        startTabelaMesa();

    }

    private void testeConexao(){
        getConnection();
        closeConnection();
    }


    private void startTabelaGarcom(){
        try {
            transactionCreateTable("SELECT * FROM garcom LIMIT 2");
        }catch(SQLException ex){

            if(ex.toString().contains("org.postgresql.util.PSQLException: ERROR: relation \"garcom\" does not exist")){
                var sqlCreateTable ="CREATE TABLE public.garcom (\n" +
                        "\tid_garcom serial NOT NULL,\n" +
                        "\tnome varchar NOT NULL,\n" +
                        "\tcpf varchar(15) NOT NULL,\n" +
                        "\tdata_nascimento date NOT NULL,\n" +
                        "\temail varchar NOT NULL,\n" +
                        "\ttelefone varchar(20) NOT NULL,\n" +
                        "\tsexo varchar(20) NOT NULL,\n" +
                        "\tsalario float8 NOT NULL,\n" +
                        "\tCONSTRAINT garcom_pk PRIMARY KEY (id_garcom),\n" +
                        "\tCONSTRAINT garcom_un UNIQUE (cpf)\n" +
                        ");\n";
                try {
                    transactionCreateTable(sqlCreateTable);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Erro inesperado\n"+e,"ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Erro inesperado\n"+ex,"ERRO",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private void startTabelaMesa(){
        try {
            transactionCreateTable("SELECT * FROM mesa LIMIT 2");
        }catch(SQLException ex){

            if(ex.toString().contains("org.postgresql.util.PSQLException: ERROR: relation \"mesa\" does not exist")){
                var sqlCreateTable ="CREATE TABLE public.mesa (\n" +
                        "\tid_mesa serial NOT NULL,\n" +
                        "\tsituacao varchar(10) NOT NULL,\n" +
                        "\tmax_cap int4 NOT NULL,\n" +
                        "\tid_garcom integer,\n" +
                        "\tCONSTRAINT mesa_pk PRIMARY KEY (id_mesa),\n" +
                        "\tCONSTRAINT mesa_fk FOREIGN KEY (id_garcom) REFERENCES public.garcom(id_garcom) \n" +
                        ");\n";
                try {
                    transactionCreateTable(sqlCreateTable);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,"Erro inesperado\n"+e,"ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Erro inesperado\n"+ex,"ERRO",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
