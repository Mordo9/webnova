package com.novamentis.webnova.future;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class AlumnoMatriculaDTO {

    // Datos Personales del Alumno
    private String nombre;
    private String apellido;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Asegura el parseo correcto de la fecha desde HTML
    private LocalDate fechaNacimiento;
    private String sexo;
    private String ciudad;
    private String direccionAlumno;
    private String discapacidad;
    private Integer edad; // Usar Integer para permitir nulos si no es requerido, o int si siempre debe tener valor
    private String tipoSangre;
    private String nivel;
    private String grado;
    // El archivo se manejará por separado o como MultipartFile aquí

    // Información del Apoderado
    private String apoderadoNombre;
    private String apoderadoApellido;
    private String apoderadoDireccion;
    private String apoderadoTelefono;
    private String estadoCivil;

    // Getters y Setters para todos los campos...
    // Ejemplo:
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccionAlumno() {
        return direccionAlumno;
    }

    public void setDireccionAlumno(String direccionAlumno) {
        this.direccionAlumno = direccionAlumno;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getApoderadoNombre() {
        return apoderadoNombre;
    }

    public void setApoderadoNombre(String apoderadoNombre) {
        this.apoderadoNombre = apoderadoNombre;
    }

    public String getApoderadoApellido() {
        return apoderadoApellido;
    }

    public void setApoderadoApellido(String apoderadoApellido) {
        this.apoderadoApellido = apoderadoApellido;
    }

    public String getApoderadoDireccion() {
        return apoderadoDireccion;
    }

    public void setApoderadoDireccion(String apoderadoDireccion) {
        this.apoderadoDireccion = apoderadoDireccion;
    }

    public String getApoderadoTelefono() {
        return apoderadoTelefono;
    }

    public void setApoderadoTelefono(String apoderadoTelefono) {
        this.apoderadoTelefono = apoderadoTelefono;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return "AlumnoMatriculaDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccionAlumno='" + direccionAlumno + '\'' +
                ", discapacidad='" + discapacidad + '\'' +
                ", edad=" + edad +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", nivel='" + nivel + '\'' +
                ", grado='" + grado + '\'' +
                ", apoderadoNombre='" + apoderadoNombre + '\'' +
                ", apoderadoApellido='" + apoderadoApellido + '\'' +
                ", apoderadoDireccion='" + apoderadoDireccion + '\'' +
                ", apoderadoTelefono='" + apoderadoTelefono + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                '}';
    }
}