package br.com.cpsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CpsbApiController {
    
    @GetMapping()
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
