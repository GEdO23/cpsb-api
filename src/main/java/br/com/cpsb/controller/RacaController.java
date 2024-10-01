package br.com.cpsb.controller;

import br.com.cpsb.model.Pet;
import br.com.cpsb.model.Raca;
import br.com.cpsb.repository.RacaRepository;
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
    private RacaRepository racaRepository;

    @GetMapping("/lista_racas")
    public ModelAndView listaRacas() {
        ModelAndView mv = new ModelAndView("lista_racas");

        List<Raca> racas = racaRepository.findAll();
        mv.addObject("racas", racas);

        return mv;
    }

    @PostMapping("/cadastrar_raca")
    public ModelAndView cadastrarRaca(Raca new_raca) {
        ModelAndView mv = new ModelAndView("redirect:/lista_racas");

        racaRepository.save(new_raca);

        mv.addObject("raca", new_raca);

        return mv;
    }

    @GetMapping("/formulario_cadastrar_raca")
    public ModelAndView formularioCadastrarRaca() {
        ModelAndView mv = new ModelAndView("formulario_cadastrar_raca");

        mv.addObject("raca", new Raca());

        return mv;
    }

    @GetMapping("/detalhes_raca/{id}")
    public ModelAndView detalhesPet(@PathVariable Long id) {
        Optional<Raca> racaOptional = racaRepository.findById(id);

        if (racaOptional.isPresent()) {
            Raca raca = racaOptional.get();

            ModelAndView mv = new ModelAndView("detalhes_raca");
            mv.addObject("raca", raca);

            return mv;
        } else {
            return new ModelAndView("redirect:/lista_racas");
        }
    }
}
