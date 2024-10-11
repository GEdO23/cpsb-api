package br.com.cpsb.service;

import br.com.cpsb.model.Pet;
import br.com.cpsb.model.Raca;
import br.com.cpsb.repository.PetRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private RacaService racaService;

    @Autowired
    private PetRepository repo;

    public Pet getUpdatedPet(Long id, Pet updatedPet) {
        Pet foundPet = findPet(id);
        foundPet.setNome(updatedPet.getNome());

        Raca raca = racaService.findRaca(updatedPet.getRaca());
        foundPet.setRaca(raca);

        return foundPet;
    }

    public Pet findPet(Long id) {
        Optional<Pet> petOptional = repo.findById(id);

        if (petOptional.isEmpty()) {
            throw new RuntimeException("Pet não encontrado");
        }

        return petOptional.get();
    }

    public List<Pet> findAll() {
        return repo.findAll();
    }

    public void save(Pet pet) {
        Raca racaEncontrada = racaService.findRaca(pet.getRaca());
        pet.setRaca(racaEncontrada);
        repo.save(pet);
    }

    public Optional<Pet> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        Pet foundPet = findPet(id);
        repo.deleteById(foundPet.getId());
    }
}
