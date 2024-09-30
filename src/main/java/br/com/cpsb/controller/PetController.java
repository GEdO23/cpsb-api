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

    //TODO Edição de pet

    //TODO Detalhes de pet
    
}
