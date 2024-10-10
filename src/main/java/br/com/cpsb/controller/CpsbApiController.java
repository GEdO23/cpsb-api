package br.com.cpsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CpsbApiController {
    
    @GetMapping("/pagina_principal")
    public String index() {
        return "index";
    }
    
    @GetMapping("/acesso_negado")
    public String acessoNegado() {
        return "erro_403";
    }
}
