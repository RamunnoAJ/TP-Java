package dominio.persistencia;

import java.io.*;
import java.util.List;

public class Persistencia {

    private static final String ZONAS_FILE  = "zonas.dat";
    private static final String STANDS_FILE = "stands.dat";

    // Preguntar si podemos usar tipos genéricos (La <T>) o tenemos que implementar un guardar y cargar para cada tipo de lista
    public static <T> void guardarLista(List<T> lista, String archivo) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar " + archivo, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> cargarLista(String archivo) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer " + archivo, e);
        }
    }

    public static <T> void cargarZonas(T objeto, String archivo) {}//Para que compile momentaneamente esta vacio, queria probar la GUI

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
}



