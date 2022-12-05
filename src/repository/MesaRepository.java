package repository;

import molde.Mesa;

import java.util.List;
import java.util.Optional;

public interface MesaRepository {
    Optional<Mesa> findById(final Integer numeroMesa);

    boolean save(final String sql);

    boolean deleteById(final Integer numeroMesa);

    List<Mesa> buscaCapacidade(final Integer capacidade);

    List<Mesa> findAll();

    List<Mesa> buscaPorGarcomMesaOcupada(final Long idGarcom);
}
