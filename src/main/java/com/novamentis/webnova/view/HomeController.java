package com.novamentis.webnova.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
}
