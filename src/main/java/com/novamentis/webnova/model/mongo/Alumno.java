package com.novamentis.webnova.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "alumnos")
public class Alumno {

    @Id
    private String id;

    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String sexo;
    private String ciudad;
    private String direccionAlumno;
    private String discapacidad;
    private int edad;
    private String tipoSangre;
    private String nivel;
    private String grado;
    private String documentoNombre;

    private String apoderadoNombre;
    private String apoderadoApellido;
    private String apoderadoDireccion;
    private String apoderadoTelefono;
    private String estadoCivil;

    private String usuarioId;
}
