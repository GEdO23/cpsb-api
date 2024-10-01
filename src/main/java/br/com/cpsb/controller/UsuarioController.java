package br.com.cpsb.controller;

import br.com.cpsb.model.Usuario;
import br.com.cpsb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("formulario_login_usuario");

        mv.addObject("usuario", new Usuario());

        return mv;
    }

    @PostMapping("/login")
    public ModelAndView logar(Usuario usuario) {

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findAll().stream().filter(u ->
                u.getNome().equals(usuario.getNome())
        ).findFirst();
        
        if (usuarioEncontrado.isPresent()) {
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView mv = new ModelAndView("/formulario_login_usuario");
            mv.addObject("usuario", new Usuario());
            return mv;
        }
    }

}
