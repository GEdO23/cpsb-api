package br.com.cpsb.controller;

import br.com.cpsb.model.Breed;
import br.com.cpsb.model.Pet;
import br.com.cpsb.service.BreedService;
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
public class BreedController {

    @Autowired
    private BreedService service;

    @GetMapping("/breed_list")
    public ModelAndView breedList() {
        ModelAndView mv = new ModelAndView("breed_list");
        List<Breed> breeds = service.get();
        mv.addObject("breeds", breeds);
        return mv;
    }

    @PostMapping("/breed/save")
    public ModelAndView save(Breed breed) {
        ModelAndView mv = new ModelAndView("redirect:/breed_list");
        service.post(breed);
        mv.addObject("breed", breed);
        return mv;
    }

    @GetMapping("/breed_form_register")
    public ModelAndView breedFormRegister() {
        ModelAndView mv = new ModelAndView("breed_form_register");
        mv.addObject("breed", new Breed());
        return mv;
    }

    @GetMapping("/breed_details/{id}")
    public ModelAndView breedDetails(@PathVariable Long id) {
        Optional<Breed> breedOptional = service.getById(id);

        if (breedOptional.isEmpty()) {
            return new ModelAndView("redirect:/breed_list");
        }

        Breed breed = breedOptional.get();
        ModelAndView mv = new ModelAndView("breed_details");
        mv.addObject("breed", breed);
        return mv;
    }
}
