package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.repository.PetRepository;
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
    private PetRepository petRepository;

    //Lista de pets
    @GetMapping()
    public ModelAndView listaPets() {
        ModelAndView mv = new ModelAndView("lista_pets");

        List<Pet> pets = petRepository.findAll();
        mv.addObject("pets", pets);

        return mv;
    }

    //Cadastro de pet
    @PostMapping("/cadastrar_pet")
    public ModelAndView cadastrarPet(Pet new_pet) {
        ModelAndView mv = new ModelAndView("redirect:/");

        petRepository.save(new_pet);

        mv.addObject("pet", new_pet);

        return mv;
    }

    @GetMapping("/formulario_cadastrar_pet")
    public ModelAndView formularioCadastrarPet() {
        ModelAndView mv = new ModelAndView("formulario_cadastrar_pet");

        mv.addObject("pet", new Pet());

        return mv;
    }

    //Edição de pet
    @PostMapping("/atualizar_pet/{id}")
    public ModelAndView atualizarPet(@PathVariable Long id, Pet newPet, BindingResult bd) {

        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("formulario_atualizar_pet");
            mv.addObject("pet", newPet);
            return mv;
        }

        Optional<Pet> op = petRepository.findById(id);

        if (op.isPresent()) {
            Pet pet = op.get();
            pet.setNome(newPet.getNome());
            petRepository.save(pet);
        }

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/formulario_atualizar_pet/{id}")
    public ModelAndView formularioAtualizarPet(@PathVariable Long id) {

        Optional<Pet> op = petRepository.findById(id);

        if (op.isPresent()) {
            Pet pet = op.get();

            ModelAndView mv = new ModelAndView("formulario_atualizar_pet");
            mv.addObject("pet", pet);

            return mv;
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    //Detalhes de pet
    @GetMapping("/detalhes_pet/{id}")
    public ModelAndView detalhesPet(@PathVariable Long id) {
        Optional<Pet> op = petRepository.findById(id);

        if (op.isPresent()) {
            Pet pet = op.get();

            ModelAndView mv = new ModelAndView("detalhes_pet");
            mv.addObject("pet", pet);

            return mv;
        } else {
            return new ModelAndView("redirect:/");
        }
    }


    //TODO Remover pet
    @GetMapping("/remover_pet/{id}")
    public String removerPet(@PathVariable Long id) {
        Optional<Pet> op = petRepository.findById(id);

        if (op.isPresent()) {
            petRepository.deleteById(id);
        }
        
        return "redirect:/";
    }

}
