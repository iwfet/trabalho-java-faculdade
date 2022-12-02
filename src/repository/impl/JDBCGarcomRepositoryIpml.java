package repository.impl;

import banco.Transactions;
import enun.TipoSexo;
import molde.Garcom;
import repository.GarcomRepository;

import javax.swing.text.html.Option;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Optional.empty;

public class JDBCGarcomRepositoryIpml extends Transactions implements GarcomRepository {




    @Override
    public boolean findByCpf(String cpf) {
        try {
            final var sql = format("SELECT * FROM garcom WHERE cpf='%s';", cpf);
            return !transactionSelect(sql).next();
        } catch (Exception e) {
            lancaErro(e.toString());
        }
        return false;
    }


    @Override
    public boolean save(final String sql) {
        try {
            return trtransactionInsert(sql);
        } catch (Exception e) {
            lancaErro(e.toString());
            return false;
        }

    }



    @Override
    public void save(Garcom garcom) {
        try {
//            final Tabela tabela = garcom.getClass().getAnnotation(Tabela.class);
//            if (isNull(tabela))
//                lancaErro(format("Não foi possivel localizar anotação @Tabela em %s", garcom));
//
//            final List<Method> methods = Arrays.stream(garcom.getClass().getDeclaredMethods())
//                    .filter((value) -> value.getClass().getAnnotation(Get.class) != null).collect(toList());
//
//            String sql = gerarLinhaInsertSQL(garcom, tabela.name(), methods);
//
////            final Method getNome = garcom.getClass().getDeclaredMethod("getNome");
////            getNome.setAccessible(true);
////            final Object invoke = getNome.invoke(garcom);
////            System.out.println(invoke);
//
//
//
////            transactionInsert(garcom.getClass());

        } catch (Exception e) {
            lancaErro(e.toString());
        }

    }

    @Override
    public List<Garcom> findAll(){
        try {
        final var sql = "SELECT * FROM garcom ";
        final ResultSet resultSet = transactionSelect(sql);
        final ArrayList<Garcom> objects = new ArrayList<>();
        while (resultSet.next()){
            final Garcom garcom = new Garcom();
            garcom.setIdGarcom(resultSet.getLong("id_garcom"));
            garcom.setNome(resultSet.getString("nome"));
            garcom.setCpf(resultSet.getString("cpf"));
            garcom.setDataNascimento(resultSet.getDate("data_nascimento"));
            garcom.setEmail(resultSet.getString("email"));
            garcom.setTelefone(resultSet.getString("telefone"));
            if(resultSet.getString("sexo").equals(TipoSexo.MASCULINO.getValue())){
                garcom.setSexo(TipoSexo.MASCULINO);
            }else if(resultSet.getString("sexo").equals(TipoSexo.FEMININO.getValue())){
                garcom.setSexo(TipoSexo.FEMININO);
            }
            garcom.setSalario(resultSet.getInt("salario"));
            objects.add(garcom);
        }

            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Garcom> findById(Long idGarcom){
        try {
            final var sql = format("SELECT * FROM garcom WHERE id_garcom='%d';", idGarcom);
            final ResultSet resultSet = transactionSelect(sql);
            final Integer tamanho = tamanhoResultSet(resultSet);
            if( tamanho == 1){
                final Garcom garcom = new Garcom();
                while (resultSet.next()) {
                    garcom.setIdGarcom(resultSet.getLong("id_garcom"));
                    garcom.setNome(resultSet.getString("nome"));
                    garcom.setCpf(resultSet.getString("cpf"));
                    garcom.setDataNascimento(resultSet.getDate("data_nascimento"));
                    garcom.setEmail(resultSet.getString("email"));
                    garcom.setTelefone(resultSet.getString("telefone"));
                    if (resultSet.getString("sexo").equals(TipoSexo.MASCULINO.getValue())) {
                        garcom.setSexo(TipoSexo.MASCULINO);
                    } else if (resultSet.getString("sexo").equals(TipoSexo.FEMININO.getValue())) {
                        garcom.setSexo(TipoSexo.FEMININO);
                    }
                    garcom.setSalario(resultSet.getInt("salario"));

                    if(!resultSet.next())break;
                }
                return Optional.of(garcom);
            }

        }catch (SQLException e) {
           lancaErro(e.toString());
           return empty();
        }
        return empty();
    }


}
