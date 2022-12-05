package banco;

import utils.anotecion.Colum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Transactions extends ConexaoBanco{

    public static void transactionCreateTable(final String sql) throws SQLException {
        try {
            getConnection().prepareStatement(sql).executeQuery();
        }catch(SQLException ex){
            if(!ex.toString().contains("org.postgresql.util.PSQLException: Nenhum resultado foi retornado pela consulta"))throw ex;
        }finally{
            closeConnection();
        }
    }

    public ResultSet transactionSelect(final String sql){
        try {
            final Statement stmt = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            return stmt.executeQuery(sql);

        }catch (SQLException e){
            lancaErro(e.toString());
        } finally{
            closeConnection();
        }
        return null;
    }


    public  <T> List<T> transactionSelect(final String sql, final Class<T> clazz ){
        try {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields) {
            field.setAccessible(true);
        }
        List<T> list = new ArrayList<>();

            final ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            while(resultSet.next()) {

                T dto = clazz.getConstructor().newInstance();
                for(Field field: fields) {
                    Colum col = field.getAnnotation(Colum.class);
                    if(col!=null) {
                        String name = col.name();
                        try{
                            String value = resultSet.getString(name);
                            field.set(dto, field.getType().getConstructor().newInstance(value));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(dto);
            }
            return list;
        } catch (SQLException | NoSuchMethodException| InvocationTargetException| InstantiationException| IllegalAccessException e) {
            lancaErro(e.toString());
        } finally{
            closeConnection();
        }
        return null;
    }


    public Boolean transactionInsert(final String sql){
        try {
            final int i = getConnection().createStatement().executeUpdate(sql);
            return i!=0;
        }catch (SQLException e){
            lancaErro(e.toString());
            return false;
        } finally{
            closeConnection();
        }
    }


    public Integer tamanhoResultSet(ResultSet resultSet){

        int size =0;
        if (resultSet != null)
        {
            try {
                if(resultSet.last()) {
                    size = resultSet.getRow();
                    resultSet.beforeFirst();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return size;
    }


    public  Boolean transactionDelete(final String sql){
        try {
            final int i = getConnection().createStatement().executeUpdate(sql);
            return i!=0;
        }catch (SQLException e){
            lancaErro(e.toString());
            return false;
        } finally{
            closeConnection();
        }
    }



    public Boolean transactionUpdate(final String sql){
        try {
            final int i = getConnection().createStatement().executeUpdate(sql);
            return i!=0;
        }catch (SQLException e){
            lancaErro(e.toString());
            return false;
        } finally{
            closeConnection();
        }
    }
}
