package banco;

import utils.LancaMensagem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco extends LancaMensagem{
    private static Connection connection;
    public static String conexao= "";

    public static Connection getConnection(){

        try{
            if(connection == null || connection.isClosed()){
                Class.forName("org.postgresql.Driver");
                String url;
                if(!conexao.equals("")){
                    url = "jdbc:postgresql://"+conexao+":5432/faculdade";
                }else{
                    url = "jdbc:postgresql://localhost:5432/faculdade";
                }
                String user = "postgres";
                String password = "KNY41iNV5";

                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);

            }
        }catch(ClassNotFoundException ex){

            JOptionPane.showMessageDialog(null,"Driver não encontrado.","ERRO",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Conexão NÃO estabelecida.","ERRO",JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, "Conexão NÃO estabelecida.", ex);
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            connection.commit();
            if(!connection.isClosed()){
                connection.close();
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao salvar dados.\n"+ex,"ERRO",JOptionPane.ERROR_MESSAGE);
            try{
                connection.rollback();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Erro a realizar o RollBack\n"+e,"ERRO",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
