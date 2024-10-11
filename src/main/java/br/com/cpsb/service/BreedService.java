package br.com.cpsb.service;

import br.com.cpsb.model.Breed;
import br.com.cpsb.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreedService implements ServiceDto<Long, Breed>{

    @Autowired
    private BreedRepository repo;

    public List<String> getBreedsNames() {
        return repo.findAll()
                .stream()
                .map(Breed::getName)
                .toList();
    }

    public Breed getByName(String name) {
        Optional<Breed> foundBreed = repo.findByName(name);

        if (foundBreed.isEmpty()) {
            throw new RuntimeException("BREED NOT FOUND");
        }

        return foundBreed.get();
    }

    @Override
    public List<Breed> get() {
        return repo.findAll();
    }

    @Override
    public Optional<Breed> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void post(Breed breed) {
        repo.save(breed);
    }

    @Override
    public void put(Long id, Breed breed) {
        Optional<Breed> foundBreed = repo.findById(id);

        if (foundBreed.isPresent()) {
            breed.setId(id);
            repo.save(breed);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Breed> foundBreed = repo.findById(id);

        if (foundBreed.isPresent()) {
            repo.deleteById(id);
        }
    }
}
