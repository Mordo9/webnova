package com.novamentis.webnova.util;

import net.sourceforge.tess4j.Tesseract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class OcrService {

    // Método general
    public static String extraerTexto(File archivo) {
        String tipo = obtenerTipoArchivo(archivo);

        if ("pdf".equalsIgnoreCase(tipo)) {
            return extraerTextoDesdePdf(archivo);
        } else if (esImagen(tipo)) {
            return extraerTextoDesdeImagen(archivo);
        } else {
            return "Tipo de archivo no soportado";
        }
    }

    // Para imágenes
    public static String extraerTextoDesdeImagen(File imagenFile) {
        try {
            BufferedImage img = ImageIO.read(imagenFile);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
            tesseract.setLanguage("spa");
            return tesseract.doOCR(img);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al procesar la imagen: " + e.getMessage();
        }
    }

    // Para PDFs (cada página como imagen)
    public static String extraerTextoDesdePdf(File archivoPdf) {
        StringBuilder textoExtraido = new StringBuilder();
        try (PDDocument documento = PDDocument.load(archivoPdf)) {
            PDFRenderer renderizador = new PDFRenderer(documento);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
            tesseract.setLanguage("spa");

            for (int i = 0; i < documento.getNumberOfPages(); i++) {
                BufferedImage imagen = renderizador.renderImageWithDPI(i, 300);
                String textoPagina = tesseract.doOCR(imagen);
                textoExtraido.append(textoPagina).append("\n--- Página ").append(i + 1).append(" ---\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al procesar el PDF: " + e.getMessage();
        }
        return textoExtraido.toString();
    }

    // Helper para saber el tipo de archivo
    private static String obtenerTipoArchivo(File file) {
        String nombre = file.getName();
        int dot = nombre.lastIndexOf(".");
        if (dot > 0 && dot < nombre.length() - 1) {
            return nombre.substring(dot + 1).toLowerCase();
        }
        return "";
    }

    // Helper para saber si es imagen
    private static boolean esImagen(String extension) {
        return extension.equals("jpg") || extension.equals("jpeg") ||
               extension.equals("png") || extension.equals("bmp") ||
               extension.equals("tif") || extension.equals("tiff");
    }
}
