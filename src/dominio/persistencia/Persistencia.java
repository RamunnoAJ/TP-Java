package dominio.persistencia;

import java.io.*;
import java.util.List;

public class Persistencia {
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
}
