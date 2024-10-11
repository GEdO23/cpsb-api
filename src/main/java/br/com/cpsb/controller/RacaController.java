package br.com.cpsb.controller;

import br.com.cpsb.model.Raca;
import br.com.cpsb.repository.RacaRepository;
import br.com.cpsb.service.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class RacaController {

    @Autowired
    private RacaService service;

    @GetMapping("/lista_racas")
    public ModelAndView listaRacas() {
        ModelAndView mv = new ModelAndView("lista_racas");
        List<Raca> racas = service.findAll();
        mv.addObject("racas", racas);
        return mv;
    }

    @PostMapping("/cadastrar_raca")
    public ModelAndView cadastrarRaca(Raca raca) {
        ModelAndView mv = new ModelAndView("redirect:/lista_racas");
        service.save(raca);
        mv.addObject("raca", raca);
        return mv;
    }

    @GetMapping("/formulario_cadastrar_raca")
    public ModelAndView formularioCadastrarRaca() {
        ModelAndView mv = new ModelAndView("form_cadastrar_raca");
        mv.addObject("raca", new Raca());
        return mv;
    }

    @GetMapping("/detalhes_raca/{id}")
    public ModelAndView detalhesRaca(@PathVariable Long id) {
        Optional<Raca> racaOptional = service.findById(id);

        if (racaOptional.isEmpty()) {
            return new ModelAndView("redirect:/lista_racas");
        }

        Raca raca = racaOptional.get();
        ModelAndView mv = new ModelAndView("detalhes_raca");
        mv.addObject("raca", raca);
        return mv;
    }
}
