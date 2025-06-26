package com.novamentis.webnova.util;
import java.io.File;

public class TestOCR {
    public static void main(String[] args) {
        // Ruta de prueba a un PDF en tu máquina
        File archivoPdf = new File("C:/uploads/test.pdf");

        if (!archivoPdf.exists()) {
            System.err.println("Archivo no encontrado: " + archivoPdf.getAbsolutePath());
            return;
        }

        String texto = OcrService.extraerTextoDesdePdf(archivoPdf);

        System.out.println("TEXTO EXTRAÍDO:");
        System.out.println("------------------------------------------------");
        System.out.println(texto);
    }
}