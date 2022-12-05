package repository.impl;

import banco.Transactions;
import enun.TipoSexo;
import enun.TipoSituacaoMesa;
import molde.Garcom;
import molde.Mesa;
import repository.MesaRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
