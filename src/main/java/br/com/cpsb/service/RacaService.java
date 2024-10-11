package br.com.cpsb.service;

import br.com.cpsb.model.Raca;
import br.com.cpsb.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RacaService {

    @Autowired
    private RacaRepository repo;

    public Raca findRaca(Raca raca) {
        Optional<Raca> racaOptional = repo.findByNome(raca.getNome());

        if (racaOptional.isEmpty()) {
            throw new RuntimeException("Raça não encontrada");
        }

        return racaOptional.get();
    }

    public List<String> findAllNomes() {
        return repo.findAll().stream().map(Raca::getNome).toList();
    }

    public List<Raca> findAll() {
        return repo.findAll();
    }

    public void save(Raca raca) {
        repo.save(raca);
    }

    public Optional<Raca> findById(Long id) {
        return repo.findById(id);
    }
}
