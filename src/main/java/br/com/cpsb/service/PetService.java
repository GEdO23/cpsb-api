package br.com.cpsb.service;

import br.com.cpsb.model.Breed;
import br.com.cpsb.model.Pet;
import br.com.cpsb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService implements ServiceDto<Long, Pet> {

    @Autowired
    private BreedService breedService;

    @Autowired
    private PetRepository repo;

    @Override
    public List<Pet> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Pet> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void post(Pet pet) {
        Breed foundBreed = breedService.getByName(pet.getBreed().getName());
        pet.setBreed(foundBreed);
        repo.save(pet);
    }

    @Override
    public void put(Long id, Pet pet) {
        Optional<Pet> foundPet = repo.findById(id);

        if (foundPet.isPresent()) {
            pet.setId(id);

            Breed foundBreed = breedService.getByName(pet.getBreed().getName());
            pet.getBreed().setId(foundBreed.id);

            repo.save(pet);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Pet> foundPet = repo.findById(id);

        if (foundPet.isPresent()) {
            repo.deleteById(id);
        }
    }
}
