package br.com.cpsb.service;

import br.com.cpsb.model.Breed;
import br.com.cpsb.model.Pet;
import br.com.cpsb.repository.BreedRepository;
import br.com.cpsb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BreedService implements ServiceDto<Long, Breed> {

    @Autowired
    private BreedRepository repo;

    @Autowired
    private PetRepository petRepo;

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

    public Breed getDefault() {
        Optional<Breed> foundBreedNone = repo.findByName("None");

        if (foundBreedNone.isEmpty()) {
            throw new RuntimeException("BREED 'None' NOT FOUND");
        }

        return foundBreedNone.get();
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

        if (foundBreed.isEmpty()) {
            throw new RuntimeException("BREED NOT FOUND");
        }

        Breed breed = foundBreed.get();
        Breed breedNone = getDefault();

        if (breed == breedNone) {
            throw new RuntimeException("CANNOT DELETE BREED 'None'");
        }

        List<Pet> breedPets = petRepo.findAll().stream()
                .filter(pet -> pet.getBreed() == breed).toList();

        breedPets.forEach(pet -> pet.setBreed(breedNone));

        repo.deleteById(id);
    }
}
