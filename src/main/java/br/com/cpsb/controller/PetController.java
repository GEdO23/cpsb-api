package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.service.BreedService;
import br.com.cpsb.service.PetService;
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
    private BreedService breedService;

    @GetMapping("/pet_list")
    public ModelAndView petList() {
        ModelAndView mv = new ModelAndView("pet_list");
        List<Pet> pets = service.findAll();
        mv.addObject("pets", pets);
        return mv;
    }

    @PostMapping("/pet/save")
    public ModelAndView save(Pet pet) {
        ModelAndView mv = new ModelAndView("redirect:/pet_list");
        service.save(pet);
        mv.addObject("pet", pet);
        return mv;
    }

    @GetMapping("/pet_form_register")
    public ModelAndView petFormRegister() {
        ModelAndView mv = new ModelAndView("pet_form_register");
        mv.addObject("pet", new Pet());
        List<String> foundBreeds = breedService.findAllNames();
        mv.addObject("breed_list", foundBreeds);
        return mv;
    }

    @PostMapping("/pet/update/{id}")
    public ModelAndView update(@PathVariable Long id, Pet pet, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("pet_form_update");
            mv.addObject("pet", pet);
            List<String> foundRacas = breedService.findAllNames();
            mv.addObject("breed_list", foundRacas);
            return mv;
        }

        Pet updatedPet = service.getUpdatedPet(id, pet);
        service.save(updatedPet);

        return new ModelAndView("redirect:/pet_list");
    }

    @GetMapping("/pet_form_update/{id}")
    public ModelAndView petFormUpdate(@PathVariable Long id) {
        Optional<Pet> petOptional = service.findById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/pet_list");
        }

        Pet pet = petOptional.get();

        ModelAndView mv = new ModelAndView("pet_form_update");
        mv.addObject("pet", pet);
        List<String> foundBreeds = breedService.findAllNames();
        mv.addObject("breed_list", foundBreeds);

        return mv;
    }

    @GetMapping("/pet_details/{id}")
    public ModelAndView petDetails(@PathVariable Long id) {
        Optional<Pet> petOptional = service.findById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/pet_list");
        }

        Pet pet = petOptional.get();
        ModelAndView mv = new ModelAndView("pet_details");
        mv.addObject("pet", pet);

        return mv;
    }

    @GetMapping("/pet_remove/{id}")
    public String petRemove(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/pet_list";
    }
}
