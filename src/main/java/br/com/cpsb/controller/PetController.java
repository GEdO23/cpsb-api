package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.service.PetService;
import br.com.cpsb.service.RacaService;
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
    private PetService service;

    @Autowired
    private RacaService racaService;

    @GetMapping("/lista_pets")
    public ModelAndView listaPets() {
        ModelAndView mv = new ModelAndView("pet_lista");
        List<Pet> pets = service.findAll();
        mv.addObject("pets", pets);
        return mv;
    }

    @PostMapping("/cadastrar_pet")
    public ModelAndView cadastrarPet(Pet pet) {
        ModelAndView mv = new ModelAndView("redirect:/lista_pets");
        service.save(pet);
        mv.addObject("pet", pet);
        return mv;
    }

    @GetMapping("/formulario_cadastrar_pet")
    public ModelAndView formularioCadastrarPet() {
        ModelAndView mv = new ModelAndView("pet_form_cadastrar");
        mv.addObject("pet", new Pet());
        List<String> foundRacas = racaService.findAllNomes();
        mv.addObject("lista_racas", foundRacas);
        return mv;
    }

    @PostMapping("/atualizar_pet/{id}")
    public ModelAndView atualizarPet(@PathVariable Long id, Pet pet, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("pet_form_atualizar");
            mv.addObject("pet", pet);
            List<String> foundRacas = racaService.findAllNomes();
            mv.addObject("lista_racas", foundRacas);
            return mv;
        }

        Pet updatedPet = service.getUpdatedPet(id, pet);
        service.save(updatedPet);

        return new ModelAndView("redirect:/lista_pets");
    }

    @GetMapping("/formulario_atualizar_pet/{id}")
    public ModelAndView formularioAtualizarPet(@PathVariable Long id) {
        Optional<Pet> petOptional = service.findById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/lista_pets");
        }

        Pet pet = petOptional.get();

        ModelAndView mv = new ModelAndView("pet_form_atualizar");
        mv.addObject("pet", pet);
        List<String> foundRacas = racaService.findAllNomes();
        mv.addObject("lista_racas", foundRacas);

        return mv;
    }

    @GetMapping("/detalhes_pet/{id}")
    public ModelAndView detalhesPet(@PathVariable Long id) {
        Optional<Pet> petOptional = service.findById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/lista_pets");
        }

        Pet pet = petOptional.get();
        ModelAndView mv = new ModelAndView("pet_detalhes");
        mv.addObject("pet", pet);

        return mv;
    }

    @GetMapping("/remover_pet/{id}")
    public String removerPet(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/lista_pets";
    }
}
