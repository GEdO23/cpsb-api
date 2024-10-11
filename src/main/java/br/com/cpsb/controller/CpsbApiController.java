package br.com.cpsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CpsbApiController {
    
    @GetMapping("/home")
    public String index() {
        return "home";
    }
    
    @GetMapping("/denied_access")
    public String deniedAccess() {
        return "error_403";
    }
}
