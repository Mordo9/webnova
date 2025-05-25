package com.novamentis.webnova.future;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.novamentis.webnova.future.AlumnoMatriculaDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class AlumnoController {


    private static final String UPLOAD_DIR = "./uploads/"; // Directorio relativo al proyecto

    @GetMapping("/")
    public String mostrarFormulario() {

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                System.err.println("No se pudo crear el directorio de subida: " + e.getMessage());
            }
        }
        return "tuPaginaDeFormulario";
    }


    @PostMapping("/alumno") 
    public String registrarAlumno(
            AlumnoMatriculaDTO alumnoDTO,
            @RequestParam("documento") MultipartFile documentoFile,
            RedirectAttributes redirectAttributes) {

        System.out.println("Datos del alumno recibidos: " + alumnoDTO.toString());

        if (documentoFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Por favor, seleccione un archivo para subir.");

        } else {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Obtener el nombre original del archivo
                String originalFilename = documentoFile.getOriginalFilename();
                // Crear un nombre de archivo único para evitar colisiones (opcional pero recomendado)
                String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
                
                // Ruta completa del archivo a guardar
                Path filePath = uploadPath.resolve(uniqueFilename);

                // Guardar el archivo en el servidor
                Files.copy(documentoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Archivo guardado en: " + filePath.toString());
                redirectAttributes.addFlashAttribute("successMessage",
                        "Alumno registrado exitosamente! Documento subido: " + uniqueFilename);
                
                // Aquí guardarías alumnoDTO y la ruta del archivo (filePath.toString() o uniqueFilename)
                // en tu base de datos a través de un servicio y repositorio.
                // Ejemplo:
                // alumnoService.registrar(alumnoDTO, filePath.toString());

            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Error al subir el archivo: " + e.getMessage());
            }
        }

        // Aquí puedes procesar alumnoDTO y guardar la información en la base de datos.
        // La información del archivo (nombre, ruta) también debería guardarse.

        // Redirigir a una página de éxito o de vuelta al formulario
        return "redirect:/"; // Redirige a la raíz (donde podrías tener el formulario o una página de inicio)
                             // o a una página específica de confirmación.
    }
}