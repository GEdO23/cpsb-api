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
    public ModelAndView breedListPage() {
        ModelAndView mv = new ModelAndView("breed/breed_list");
        List<Breed> breeds = service.getAll();
        mv.addObject("breeds", breeds);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView breedDetailsPage(@PathVariable Long id) {
        Optional<Breed> foundBreed = service.getById(id);

        if (foundBreed.isEmpty()) {
            return new ModelAndView("redirect:/breeds");
        }

        Breed breed = foundBreed.get();
        ModelAndView mv = new ModelAndView("breed/details");
        mv.addObject("breed", breed);
        return mv;
    }

    @GetMapping("/form-register")
    public ModelAndView breedFormRegisterPage() {
        ModelAndView mv = new ModelAndView("breed/form_create");
        mv.addObject("breed", new Breed());
        return mv;
    }

    @GetMapping("/form-update/{id}")
    public ModelAndView breedFormUpdatePage(@PathVariable Long id) {
        Optional<Breed> foundBreed = service.getById(id);

        if (foundBreed.isEmpty()) {
            return new ModelAndView("redirect:/breeds");
        }

        Breed breed = foundBreed.get();

        ModelAndView mv = new ModelAndView("breed/form_update");
        mv.addObject("breed", breed);

        return mv;
    }

    @PostMapping("/save")
    public ModelAndView saveBreed(Breed breed) {
        ModelAndView mv = new ModelAndView("redirect:/breeds");
        service.post(breed);
        mv.addObject("breed", breed);
        return mv;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateBreed(@PathVariable Long id, Breed breed, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("breed/form_update");
            mv.addObject("breed", breed);
            return mv;
        }

        service.put(id, breed);

        return new ModelAndView("redirect:/breeds");
    }

    @GetMapping("/remove/{id}")
    public String removeBreed(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/breeds";
    }
}
