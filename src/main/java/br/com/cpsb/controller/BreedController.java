package br.com.cpsb.controller;

import br.com.cpsb.model.Breed;
import br.com.cpsb.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/breeds")
public class BreedController {

    @Autowired
    private BreedService service;

    @GetMapping()
    public ModelAndView breedList() {
        ModelAndView mv = new ModelAndView("breed/breed_list");
        List<Breed> breeds = service.get();
        mv.addObject("breeds", breeds);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(Breed breed) {
        ModelAndView mv = new ModelAndView("redirect:/breeds");
        service.post(breed);
        mv.addObject("breed", breed);
        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, Breed breed, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("breed/form_update");
            mv.addObject("breed", breed);
            return mv;
        }

        service.put(id, breed);

        return new ModelAndView("redirect:/breeds");
    }

    @GetMapping("/form-register")
    public ModelAndView formRegister() {
        ModelAndView mv = new ModelAndView("breed/form_create");
        mv.addObject("breed", new Breed());
        return mv;
    }

    @GetMapping("/form-update/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        Optional<Breed> foundBreed = service.getById(id);

        if (foundBreed.isEmpty()) {
            return new ModelAndView("redirect:/breeds");
        }

        Breed breed = foundBreed.get();

        ModelAndView mv = new ModelAndView("breed/form_update");
        mv.addObject("breed", breed);

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable Long id) {
        Optional<Breed> breedOptional = service.getById(id);

        if (breedOptional.isEmpty()) {
            return new ModelAndView("redirect:/breeds");
        }

        Breed breed = breedOptional.get();
        ModelAndView mv = new ModelAndView("breed/details");
        mv.addObject("breed", breed);
        return mv;
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/breeds";
    }
}
