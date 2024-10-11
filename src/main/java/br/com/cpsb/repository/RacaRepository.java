package br.com.cpsb.repository;

import br.com.cpsb.model.Raca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RacaRepository extends JpaRepository<Raca, Long> {
    Optional<Raca> findByNome(String raca);

    default List<String> findAllNomes() {
        return findAll().stream().map(Raca::getNome).toList();
    }
}
