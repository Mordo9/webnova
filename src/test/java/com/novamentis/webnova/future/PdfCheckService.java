package com.novamentis.webnova.future;

public class PdfCheckService {

    public String extraerTextoDePdf(File archivoPdf) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata");
        tesseract.setLanguage("spa");
        try {
            String texto = tesseract.doOCR(archivoPdf);
            return texto;
        } catch (TesseractException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean validarCoincidencia(String textoExtraido, String nombreEsperado, String dniEsperado) {
        return textoExtraido.contains(nombreEsperado) && textoExtraido.contains(dniEsperado);
    }
}
