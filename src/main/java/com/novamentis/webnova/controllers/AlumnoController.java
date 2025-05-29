package com.novamentis.webnova.controllers;

import com.novamentis.webnova.repository.AlumnoRepository;
import com.novamentis.webnova.model.Alumno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostMapping("/alumno")
    public String guardarAlumno(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam("fechaNacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento,
            @RequestParam String sexo,
            @RequestParam String ciudad,
            @RequestParam String direccionAlumno,
            @RequestParam String discapacidad,
            @RequestParam int edad,
            @RequestParam String tipoSangre,
            @RequestParam String nivel,
            @RequestParam String grado,
            @RequestParam("documento") MultipartFile documento,
            @RequestParam String apoderadoNombre,
            @RequestParam String apoderadoApellido,
            @RequestParam String apoderadoDireccion,
            @RequestParam String apoderadoTelefono,
            @RequestParam String estadoCivil,
            RedirectAttributes redirectAttributes) throws IOException {

        // Ruta de la carpeta donde guardar el archivo
        String uploadDir = "C:/uploads/";
        File uploadPath = new File(uploadDir);

        // Crea el directorio si no existe
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        // Guardar archivo si es necesario
        String nombreArchivo = documento.getOriginalFilename();
        if (!documento.isEmpty() && "application/pdf".equals(documento.getContentType())) {
            File destino = new File(uploadPath, nombreArchivo);
            documento.transferTo(destino);
        }

        // Crear y guardar alumno
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setFechaNacimiento(fechaNacimiento);
        alumno.setSexo(sexo);
        alumno.setCiudad(ciudad);
        alumno.setDireccionAlumno(direccionAlumno);
        alumno.setDiscapacidad(discapacidad);
        alumno.setEdad(edad);
        alumno.setTipoSangre(tipoSangre);
        alumno.setNivel(nivel);
        alumno.setGrado(grado);
        alumno.setDocumentoNombre(nombreArchivo);
        alumno.setApoderadoNombre(apoderadoNombre);
        alumno.setApoderadoApellido(apoderadoApellido);
        alumno.setApoderadoDireccion(apoderadoDireccion);
        alumno.setApoderadoTelefono(apoderadoTelefono);
        alumno.setEstadoCivil(estadoCivil);

        // Asignar ID de usuario aleatorio temporal
        String usuarioId = UUID.randomUUID().toString();
        alumno.setUsuarioId(usuarioId);

        alumnoRepository.save(alumno);
        redirectAttributes.addFlashAttribute("mensaje", "Alumno registrado con éxito");
        return "redirect:/registro";
    }
}