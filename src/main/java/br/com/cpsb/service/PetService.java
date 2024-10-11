package br.com.cpsb.service;

import br.com.cpsb.model.Breed;
import br.com.cpsb.model.Pet;
import br.com.cpsb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private BreedService breedService;

    @Autowired
    private PetRepository repo;

    public Pet getUpdatedPet(Long id, Pet updatedPet) {
        Pet foundPet = findPet(id);
        foundPet.setName(updatedPet.getName());

        Breed breed = breedService.findBreed(updatedPet.getBreed());
        foundPet.setBreed(breed);

        return foundPet;
    }

    public Pet findPet(Long id) {
        Optional<Pet> petOptional = repo.findById(id);

        if (petOptional.isEmpty()) {
            throw new RuntimeException("Pet not found");
        }

        return petOptional.get();
    }

    public List<Pet> findAll() {
        return repo.findAll();
    }

    public void save(Pet pet) {
        Breed breedFound = breedService.findBreed(pet.getBreed());
        pet.setBreed(breedFound);
        repo.save(pet);
    }

    public Optional<Pet> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        Pet petFound = findPet(id);
        repo.deleteById(petFound.getId());
    }
}
