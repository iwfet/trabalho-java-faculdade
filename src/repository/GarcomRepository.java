package repository;

import molde.Garcom;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GarcomRepository {

    boolean findByCpf(String cpf);

    boolean save(String sql);

    void save(Garcom garcom);

    List<Garcom> findAll() ;

    Optional<Garcom> findById(Long idGarcom);

    Optional<Garcom> buscaEmail(String email);

    Optional<Garcom> findByEmail(String email);
}
