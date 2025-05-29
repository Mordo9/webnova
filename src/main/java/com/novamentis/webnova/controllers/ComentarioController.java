package com.novamentis.webnova.controllers;

import com.novamentis.webnova.model.mongo.Comentario;
import com.novamentis.webnova.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ComentarioController {

    private final ComentarioRepository comentarioRepo;

    @Autowired
    public ComentarioController(ComentarioRepository comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    @PostMapping("/comentarios")
    public String guardarComentario(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            RedirectAttributes attrs
    ) {
        Comentario c = new Comentario();
        c.setName(name);
        c.setEmail(email);
        c.setPhone(phone);
        c.setSubject(subject);
        c.setMessage(message);
        comentarioRepo.save(c);

        attrs.addFlashAttribute("success", "¡Gracias por tu mensaje!");
        return "redirect:/";
    }
}
