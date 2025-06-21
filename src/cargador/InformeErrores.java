package cargador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InformeErrores {
    private List<String> errores = new ArrayList<>();

    public void agregar(String mensaje) {
        errores.add(mensaje);
    }

    public boolean tieneErrores() {
        return !errores.isEmpty();
    }

    public void imprimir() {
        if (tieneErrores()) {
            guardarEnArchivo("errores_carga.txt");
        } else {
            System.out.println("Carga exitosa. Sin errores.");
        }
    }

    public void guardarEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Errores encontrados durante la carga:\n");
            for (String error : errores) {
                writer.write("- " + error + "\n");
            }
            System.out.println("️Se encontraron errores. Guardados en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el informe de errores: " + e.getMessage());
        }
    }
}
