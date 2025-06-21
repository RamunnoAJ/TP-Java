package cargador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Acumula y reporta mensajes de error durante la carga de datos.
 */
public class InformeErrores {
    private List<String> errores = new ArrayList<>();

    /**
     * Agrega un nuevo mensaje de error al informe.
     *
     * @param mensaje el texto descriptivo del error
     */
    public void agregar(String mensaje) {
        errores.add(mensaje);
    }

    /**
     * Indica si se han registrado errores.
     *
     * @return {@code true} si hay al menos un mensaje de error, {@code false} en caso contrario
     */
    public boolean tieneErrores() {
        return !errores.isEmpty();
    }

    /**
     * Muestra por pantalla un mensaje de éxito o, si existen errores,
     * guarda el informe en un archivo.
     */
    public void imprimir() {
        if (tieneErrores()) {
            guardarEnArchivo("errores_carga.txt");
        } else {
            System.out.println("Carga exitosa. Sin errores.");
        }
    }

    /**
     * Guarda todos los mensajes de error en un archivo de texto.
     *
     * @param nombreArchivo la ruta y nombre del archivo de salida
     */
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
