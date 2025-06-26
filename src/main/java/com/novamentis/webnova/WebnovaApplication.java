package com.novamentis.webnova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebnovaApplication {

    public static void main(String[] args) {
        // Imprime el valor del parámetro JVM al iniciar
        System.out.println("TOMCAT MAX FILES: " + System.getProperty("org.apache.tomcat.fileupload.maxFiles"));
        SpringApplication.run(WebnovaApplication.class, args);
    }
}
