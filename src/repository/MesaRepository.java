package repository;

import molde.Mesa;

import java.util.Optional;

public interface MesaRepository {
    Optional<Mesa> findById(Integer numeroMesa);

    boolean save(String sql);

    boolean deleteById(Integer numeroMesa);
}
