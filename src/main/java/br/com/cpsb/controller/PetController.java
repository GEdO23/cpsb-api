package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.model.Raca;
import br.com.cpsb.repository.PetRepository;
import br.com.cpsb.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class PetController {

    @Autowired
    private PetRepository repoPet;

    @Autowired
    private RacaRepository repoRaca;

    private void preencherFormulario(Pet pet, ModelAndView mv) {
        mv.addObject("pet", pet);
        List<String> racas = repoRaca.findAllNomes();
        mv.addObject("lista_racas", racas);
    }

    private Pet updatedPet(Long id, Pet p) {
        Pet pet = findPet(id);
        pet.setNome(p.getNome());

        Raca raca = findRaca(p.getRaca());
        pet.setRaca(raca);

        return pet;
    }

    private Pet findPet(Long id) {
        Optional<Pet> petOptional = repoPet.findById(id);

        if (petOptional.isEmpty()) {
            throw new RuntimeException("Pet não encontrado");
        }

        return petOptional.get();
    }

    private Raca findRaca(Raca raca) {
        Optional<Raca> racaOptional = repoRaca.findByNome(raca.getNome());

        if (racaOptional.isEmpty()) {
            throw new RuntimeException("Raça não encontrada");
        }

        return racaOptional.get();
    }

    @GetMapping("/lista_pets")
    public ModelAndView listaPets() {
        ModelAndView mv = new ModelAndView("lista_pets");
        List<Pet> pets = repoPet.findAll();
        mv.addObject("pets", pets);
        return mv;
    }

    @PostMapping("/cadastrar_pet")
    public ModelAndView cadastrarPet(Pet pet) {
        ModelAndView mv = new ModelAndView("redirect:/lista_pets");

        Raca racaEncontrada = findRaca(pet.getRaca());
        pet.setRaca(racaEncontrada);

        repoPet.save(pet);
        mv.addObject("pet", pet);

        return mv;
    }

    @GetMapping("/formulario_cadastrar_pet")
    public ModelAndView formularioCadastrarPet() {
        ModelAndView mv = new ModelAndView("formulario_cadastrar_pet");
        preencherFormulario(new Pet(), mv);
        return mv;
    }

    @PostMapping("/atualizar_pet/{id}")
    public ModelAndView atualizarPet(@PathVariable Long id, Pet pet, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("formulario_atualizar_pet");
            preencherFormulario(pet, mv);
            return mv;
        }

        repoPet.save(updatedPet(id, pet));

        return new ModelAndView("redirect:/lista_pets");
    }

    @GetMapping("/formulario_atualizar_pet/{id}")
    public ModelAndView formularioAtualizarPet(@PathVariable Long id) {
        Optional<Pet> petOptional = repoPet.findById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/lista_pets");
        }

        Pet pet = petOptional.get();
        ModelAndView mv = new ModelAndView("formulario_atualizar_pet");
        preencherFormulario(pet, mv);

        return mv;
    }

    @GetMapping("/detalhes_pet/{id}")
    public ModelAndView detalhesPet(@PathVariable Long id) {
        Optional<Pet> petOptional = repoPet.findById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/lista_pets");
        }

        Pet pet = petOptional.get();

        ModelAndView mv = new ModelAndView("detalhes_pet");
        mv.addObject("pet", pet);

        return mv;
    }

    @GetMapping("/remover_pet/{id}")
    public String removerPet(@PathVariable Long id) {
        Optional<Pet> petOptional = repoPet.findById(id);

        if (petOptional.isPresent()) {
            repoPet.deleteById(id);
        }

        return "redirect:/lista_pets";
    }
}
