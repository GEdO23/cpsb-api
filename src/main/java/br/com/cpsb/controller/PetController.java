package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.service.BreedService;
import br.com.cpsb.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService service;

    @Autowired
    private BreedService breedService;

    @GetMapping()
    public ModelAndView petListPage() {
        ModelAndView mv = new ModelAndView("pet/pet_list");
        List<Pet> pets = service.getAll();
        mv.addObject("pets", pets);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView petDetailsPage(@PathVariable Long id) {
        Optional<Pet> foundPet = service.getById(id);

        if (foundPet.isEmpty()) {
            return new ModelAndView("redirect:/pets");
        }

        Pet pet = foundPet.get();
        ModelAndView mv = new ModelAndView("pet/details");
        mv.addObject("pet", pet);

        return mv;
    }

    @GetMapping("/form-register")
    public ModelAndView petFormRegisterPage() {
        ModelAndView mv = new ModelAndView("pet/form_create");
        mv.addObject("pet", new Pet());
        List<String> breedsNames = breedService.getBreedsNames();
        mv.addObject("breed_list", breedsNames);
        return mv;
    }

    @GetMapping("/form-update/{id}")
    public ModelAndView petFormUpdatePage(@PathVariable Long id) {
        Optional<Pet> foundPet = service.getById(id);

        if (foundPet.isEmpty()) {
            return new ModelAndView("redirect:/pets");
        }

        Pet pet = foundPet.get();

        ModelAndView mv = new ModelAndView("pet/form_update");
        mv.addObject("pet", pet);
        List<String> foundBreeds = breedService.getBreedsNames();
        mv.addObject("breed_list", foundBreeds);

        return mv;
    }

    @PostMapping("/save")
    public ModelAndView savePet(Pet pet) {
        ModelAndView mv = new ModelAndView("redirect:/pets");
        service.post(pet);
        mv.addObject("pet", pet);
        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updatePet(@PathVariable Long id, Pet pet, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("pet/form_update");
            mv.addObject("pet", pet);
            List<String> foundBreeds = breedService.getBreedsNames();
            mv.addObject("breed_list", foundBreeds);
            return mv;
        }

        service.put(id, pet);

        return new ModelAndView("redirect:/pets");
    }
    
    @GetMapping("/remove/{id}")
    public String removePet(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/pets";
    }
}
