package dominio.persistencia;

import java.io.*;
import java.util.List;

public class Persistencia {

    private static final String ZONAS_FILE  = "zonas.dat";
    private static final String STANDS_FILE = "stands.dat";
    private static final String PERSONAS_FILE = "personas.dat";
    private static final String ACCESOS_FILE = "accesos.dat";
    private static final String EVENTOS_FILE = "eventos.dat";

    // Preguntar si podemos usar tipos genéricos (La <T>) o tenemos que implementar un guardar y cargar para cada tipo de lista
    private static <T> void guardarLista(List<T> lista, String archivo) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar " + archivo, e);
        }
    }

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



