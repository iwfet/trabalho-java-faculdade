package repository;

import molde.Garcom;

import java.sql.SQLException;
import java.util.List;

public interface GarcomRepository {

    boolean findByCpf(String cpf);

    boolean save(String sql);

    void save(Garcom garcom);

    List<Garcom> findAll() ;
}
