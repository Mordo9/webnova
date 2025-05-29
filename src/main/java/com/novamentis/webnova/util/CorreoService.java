package com.novamentis.webnova.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarConfirmacion(String para, String nombreAlumno) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setSubject("Inscripción recibida - InovaMentis");
        mensaje.setText("Hola, hemos recibido la inscripción de " + nombreAlumno +
                ". Tus documentos han sido validados y el proceso continúa en revisión.\n\nGracias por confiar en InovaMentis.");
        mailSender.send(mensaje);
    }
}
