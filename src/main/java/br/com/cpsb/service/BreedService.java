package br.com.cpsb.service;

import br.com.cpsb.model.Breed;
import br.com.cpsb.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreedService {

    @Autowired
    private BreedRepository repo;

    public Breed findBreed(Breed breed) {
        Optional<Breed> breedOptional = repo.findByName(breed.getName());

        if (breedOptional.isEmpty()) {
            throw new RuntimeException("Breed not found");
        }

        return breedOptional.get();
    }

    public List<String> findAllNames() {
        return repo.findAll().stream().map(Breed::getName).toList();
    }

    public List<Breed> findAll() {
        return repo.findAll();
    }

    public void save(Breed breed) {
        repo.save(breed);
    }

    public Optional<Breed> findById(Long id) {
        return repo.findById(id);
    }
}
