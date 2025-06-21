package cargador;

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
            System.out.println("Errores encontrados durante la carga:");
            errores.forEach(System.out::println);
        } else {
            System.out.println("Carga exitosa. Sin errores.");
        }
    }

    public List<String> getErrores() {
        return errores;
    }
}
