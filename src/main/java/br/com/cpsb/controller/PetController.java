package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PetController {
    
    @Autowired
    private PetRepository petRepository;

    //TODO Lista de pets
    @GetMapping()
    public ModelAndView listPets() {
        ModelAndView mv = new ModelAndView("lista_pets");
        
        List<Pet> pets = petRepository.findAll();
        mv.addObject("pets", pets);
        
        return mv;
    }

    //TODO Cadastro de pet

    //TODO Edição de pet

    //TODO Detalhes de pet
    
}
