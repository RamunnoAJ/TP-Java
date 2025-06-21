package dominio.persistencia;

import java.io.*;
import java.util.List;

/**
 * Utilidades para persistir y recuperar listas de objetos en archivos.
 */
public class Persistencia {

    private static final String ZONAS_FILE  = "zonas.dat";
    private static final String STANDS_FILE = "stands.dat";
    private static final String PERSONAS_FILE = "personas.dat";
    private static final String ACCESOS_FILE = "accesos.dat";
    private static final String EVENTOS_FILE = "eventos.dat";

    /**
     * Serializa y guarda una lista de objetos en un archivo.
     *
     * @param lista   la lista de objetos a guardar
     * @param archivo la ruta del archivo donde se almacenará la lista
     * @param <T>     el tipo de los objetos de la lista
     * @throws RuntimeException si ocurre un error de E/S al escribir el archivo
     */
    private static <T> void guardarLista(List<T> lista, String archivo) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar " + archivo, e);
        }
    }

    /**
     * Carga y deserializa una lista de objetos desde un archivo.
     *
     * @param archivo la ruta del archivo desde el cual se leerá la lista
     * @param <T>     el tipo de los objetos de la lista
     * @return la lista de objetos recuperada del archivo
     * @throws RuntimeException si ocurre un error de E/S o el archivo no contiene la clase esperada
     */
    @SuppressWarnings("unchecked")
    private static <T> List<T> cargarLista(String archivo) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer " + archivo, e);
        }
    }

    public static void guardarZonas(List<dominio.zona.Zona> zonas) {
        guardarLista(zonas, ZONAS_FILE);
    }


    public static List<dominio.zona.Zona> cargarZonas() {
        return cargarLista(ZONAS_FILE);
    }

    public static void guardarStands(List<dominio.zona.Stand> stands) {
        guardarLista(stands, STANDS_FILE);
    }

    public static List<dominio.zona.Stand> cargarStands() {
        return cargarLista(STANDS_FILE);
    }

    public static void guardarPersonas(List<dominio.persona.Persona> personas) {
        guardarLista(personas, PERSONAS_FILE);
    }

    public static List<dominio.persona.Persona> cargarPersonas() {
        return cargarLista(PERSONAS_FILE);
    }

    public static void guardarAccesos(List<dominio.acceso.Acceso> accesos) {
        guardarLista(accesos, ACCESOS_FILE);
    }

    public static List<dominio.acceso.Acceso> cargarAccesos() {
        return cargarLista(ACCESOS_FILE);
    }

    public static void guardarEventos(List<dominio.zona.Evento> eventos) {
        guardarLista(eventos, EVENTOS_FILE);
    }

    public static List<dominio.zona.Evento> cargarEventos() {
        return cargarLista(EVENTOS_FILE);
    }
}



