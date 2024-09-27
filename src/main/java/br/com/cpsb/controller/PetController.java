package br.com.cpsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PetController {
    
    @GetMapping()
    public ModelAndView showIndex() {
        return new ModelAndView("index");
    }

    //TODO Lista de pets

    //TODO Cadastro de pet

    //TODO Edição de pet

    //TODO Detalhes de pet
    
}
