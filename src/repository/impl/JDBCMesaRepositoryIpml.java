package repository.impl;

import banco.Transactions;
import dto.MesasLivreGarcomResponsavel;
import enun.TipoSituacaoMesa;
import molde.Mesa;
import repository.MesaRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static enun.TipoSituacaoMesa.LIVRE;
import static enun.TipoSituacaoMesa.OCUPADO;
import static enun.TipoSituacaoMesa.RESERVADO;
import static java.lang.String.format;
import static java.util.Optional.empty;

public class JDBCMesaRepositoryIpml extends Transactions implements MesaRepository {


    @Override
    public Optional<Mesa> findById(Integer ID){
        try {
            final var sql = format("SELECT * FROM mesa WHERE id_mesa=%d;", ID);
            final ResultSet resultSet = transactionSelect(sql);
            final Integer tamanho = tamanhoResultSet(resultSet);
            if(tamanho == 1){
                final Mesa mesa = new Mesa();
                while (resultSet.next()) {
                     mesa.setIdMesa(resultSet.getInt("id_mesa"));
                     mesa.setMaxCap(resultSet.getInt("max_cap"));
                     mesa.setIdGarcom(resultSet.getLong("id_garcom"));

                    if (resultSet.getString("situacao").equals(LIVRE.getValue())) {
                        mesa.setSituacao(LIVRE);
                    } else if (resultSet.getString("situacao").equals(OCUPADO.getValue())) {
                        mesa.setSituacao(OCUPADO);
                    } else if (resultSet.getString("situacao").equals(RESERVADO.getValue())) {
                        mesa.setSituacao(RESERVADO);
                    }
                }
                return Optional.of(mesa);
            }
        }catch (SQLException e) {
            lancaErro(e.toString());
            return empty();
        }
        return empty();
    }

    @Override
    public boolean save(final String sql) {
        try {
            return transactionInsert(sql);
        } catch (Exception e) {
            lancaErro(e.toString());
            return false;
        }

    }

    @Override
    public boolean deleteById(Integer ID) {
        final var sql = format("DELETE FROM mesa WHERE id_mesa=%d;", ID);
        return  transactionDelete(sql);
    }

    @Override
    public List<Mesa> buscaCapacidade(Integer capacidade) {
        try {
        final var sql = format("SELECT * FROM mesa WHERE max_cap >=%d;", capacidade);
        final ResultSet resultSet = transactionSelect(sql);
        List<Mesa> objects = new ArrayList<>();
        while (resultSet.next()){
            final Mesa mesa = new Mesa();
            mesa.setIdMesa(resultSet.getInt("id_mesa"));
            mesa.setMaxCap(resultSet.getInt("max_cap"));
            mesa.setIdGarcom(resultSet.getLong("id_garcom"));
            if (resultSet.getString("situacao").equals(LIVRE.getValue())) {
                mesa.setSituacao(LIVRE);
            } else if (resultSet.getString("situacao").equals(OCUPADO.getValue())) {
                mesa.setSituacao(OCUPADO);
            } else if (resultSet.getString("situacao").equals(RESERVADO.getValue())) {
                mesa.setSituacao(RESERVADO);
            }
            objects.add(mesa);

        }
        return objects;

        }catch (SQLException e) {
            lancaErro(e.toString());

        }
        return null;
    }

    @Override
    public List<Mesa> findAll() {

        try {
            final var sql = "SELECT * FROM mesa ;";
            final ResultSet resultSet = transactionSelect(sql);
            List<Mesa> objects = new ArrayList<>();
            while (resultSet.next()){
                final Mesa mesa = new Mesa();
                mesa.setIdMesa(resultSet.getInt("id_mesa"));
                mesa.setMaxCap(resultSet.getInt("max_cap"));
                mesa.setIdGarcom(resultSet.getLong("id_garcom"));
                if (resultSet.getString("situacao").equals(LIVRE.getValue())) {
                    mesa.setSituacao(LIVRE);
                } else if (resultSet.getString("situacao").equals(OCUPADO.getValue())) {
                    mesa.setSituacao(OCUPADO);
                } else if (resultSet.getString("situacao").equals(RESERVADO.getValue())) {
                    mesa.setSituacao(RESERVADO);
                }
                objects.add(mesa);

            }
            return objects;

        }catch (SQLException e) {
            lancaErro(e.toString());

        }
        return null;
    }

    @Override
    public List<Mesa> buscaPorGarcomMesaOcupada(Long idGarcom) {
        try {
            final var sql = format("SELECT * FROM mesa WHERE id_garcom = %d AND situacao = '%s' ;",idGarcom,OCUPADO.getValue());
            final ResultSet resultSet = transactionSelect(sql);
            List<Mesa> objects = new ArrayList<>();
            while (resultSet.next()){
                final Mesa mesa = new Mesa();
                mesa.setIdMesa(resultSet.getInt("id_mesa"));
                mesa.setMaxCap(resultSet.getInt("max_cap"));
                mesa.setIdGarcom(resultSet.getLong("id_garcom"));
                if (resultSet.getString("situacao").equals(LIVRE.getValue())) {
                    mesa.setSituacao(LIVRE);
                } else if (resultSet.getString("situacao").equals(OCUPADO.getValue())) {
                    mesa.setSituacao(OCUPADO);
                } else if (resultSet.getString("situacao").equals(RESERVADO.getValue())) {
                    mesa.setSituacao(RESERVADO);
                }
                objects.add(mesa);

            }
            return objects;

        }catch (SQLException e) {
            lancaErro(e.toString());

        }
        return null;
    }

    @Override
    public List<Mesa> buscaMesasGarcom(long idGarcom) {
        try {
            final var sql = format("SELECT * FROM mesa WHERE id_garcom = %d  ;",idGarcom);
            final ResultSet resultSet = transactionSelect(sql);
            List<Mesa> objects = new ArrayList<>();
            while (resultSet.next()){
                final Mesa mesa = new Mesa();
                mesa.setIdMesa(resultSet.getInt("id_mesa"));
                mesa.setMaxCap(resultSet.getInt("max_cap"));
                mesa.setIdGarcom(resultSet.getLong("id_garcom"));
                if (resultSet.getString("situacao").equals(LIVRE.getValue())) {
                    mesa.setSituacao(LIVRE);
                } else if (resultSet.getString("situacao").equals(OCUPADO.getValue())) {
                    mesa.setSituacao(OCUPADO);
                } else if (resultSet.getString("situacao").equals(RESERVADO.getValue())) {
                    mesa.setSituacao(RESERVADO);
                }
                objects.add(mesa);

            }
            return objects;

        }catch (SQLException e) {
            lancaErro(e.toString());

        }
        return null;
    }

    @Override
    public List<MesasLivreGarcomResponsavel> buscaLivreMaisGarcom() {
        try {
            final var sql = format("select m.*, g.nome  from  mesa m ,garcom g where g.id_garcom =m.id_garcom  and m.situacao = '%s' ;", LIVRE.getValue());
            final ResultSet resultSet = transactionSelect(sql);
            List<MesasLivreGarcomResponsavel> objects = new ArrayList<>();
            while (resultSet.next()){
                final var mesa = new MesasLivreGarcomResponsavel();
                mesa.setIdMesa(resultSet.getInt("id_mesa"));
                mesa.setMaxCap(resultSet.getInt("max_cap"));
                mesa.setIdGarcom(resultSet.getLong("id_garcom"));
                if (resultSet.getString("situacao").equals(LIVRE.getValue())) {
                    mesa.setSituacao(LIVRE);
                } else if (resultSet.getString("situacao").equals(OCUPADO.getValue())) {
                    mesa.setSituacao(OCUPADO);
                } else if (resultSet.getString("situacao").equals(RESERVADO.getValue())) {
                    mesa.setSituacao(RESERVADO);
                }
                mesa.setNome(resultSet.getString("nome"));
                objects.add(mesa);

            }
            return objects;

        }catch (SQLException e) {
            lancaErro(e.toString());

        }
        return null;

    }

    @Override
    public boolean atualizaSituacao(int numeroMesa, TipoSituacaoMesa situacao) {
        final var sql = format("UPDATE mesa SET situacao = '%s' where id_mesa= %d ;",situacao.getValue(), numeroMesa);
        return transactionUpdate(sql);
    }

    @Override
    public boolean atualizaCapacidade(int numeroMesa, int capacidade) {
        final var sql = format("UPDATE mesa SET max_cap = %d where id_mesa= %d ;",capacidade, numeroMesa);
        return transactionUpdate(sql);

    }

    @Override
    public boolean atualizaGarcomResponsavel(int numeroMesa, long idGarcom) {
        final var sql = format("UPDATE mesa SET id_garcom = %d where id_mesa= %d ;",idGarcom, numeroMesa);
        return transactionUpdate(sql);
    }


}
