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
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView petList() {
        ModelAndView mv = new ModelAndView("pet/pet_list");
        List<Pet> pets = service.get();
        mv.addObject("pets", pets);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(Pet pet) {
        ModelAndView mv = new ModelAndView("redirect:/pets");
        service.post(pet);
        mv.addObject("pet", pet);
        return mv;
    }

    @GetMapping("/form-register")
    public ModelAndView petFormRegister() {
        ModelAndView mv = new ModelAndView("pet/form_create");
        mv.addObject("pet", new Pet());
        List<String> foundBreeds = breedService.getBreedsNames();
        mv.addObject("breed_list", foundBreeds);
        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, Pet pet, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("pet/form_update");
            mv.addObject("pet", pet);
            List<String> foundRacas = breedService.getBreedsNames();
            mv.addObject("breed_list", foundRacas);
            return mv;
        }

        service.put(id, pet);

        return new ModelAndView("redirect:/pets");
    }

    @GetMapping("/form-update/{id}")
    public ModelAndView petFormUpdate(@PathVariable Long id) {
        Optional<Pet> petOptional = service.getById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/pets");
        }

        Pet pet = petOptional.get();

        ModelAndView mv = new ModelAndView("pet/form_update");
        mv.addObject("pet", pet);
        List<String> foundBreeds = breedService.getBreedsNames();
        mv.addObject("breed_list", foundBreeds);

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView petDetails(@PathVariable Long id) {
        Optional<Pet> petOptional = service.getById(id);

        if (petOptional.isEmpty()) {
            return new ModelAndView("redirect:/pets");
        }

        Pet pet = petOptional.get();
        ModelAndView mv = new ModelAndView("pet/details");
        mv.addObject("pet", pet);

        return mv;
    }

    @GetMapping("/remove/{id}")
    public String petRemove(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/pets";
    }
}
