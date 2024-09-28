package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PetController {
    
    @Autowired
    private PetRepository petRepository;

    //Lista de pets
    @GetMapping()
    public ModelAndView listPets() {
        ModelAndView mv = new ModelAndView("lista_pets");
        
        List<Pet> pets = petRepository.findAll();
        mv.addObject("pets", pets);
        
        return mv;
    }

    //Cadastro de pet
    @PostMapping("/inserir_pet")
    public ModelAndView registerPet(Pet new_pet) {
        ModelAndView mv = new ModelAndView("cadastro_pet");
        
        petRepository.save(new_pet);
        
        mv.addObject("pet", new_pet);
        
        return mv;
    }
    
    @GetMapping("/cadastro_pet")
    public ModelAndView registerPet() {
        ModelAndView mv = new ModelAndView("cadastro_pet");
        
        mv.addObject("pet", new Pet());
        
        return mv;
    }

    //TODO Edição de pet

    //TODO Detalhes de pet
    
}
