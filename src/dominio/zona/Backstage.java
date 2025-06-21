package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

/**
 * Representa un backstage del festival, que es una zona restringida
 */
public class Backstage extends ZonaRestringida {
    /**
     * Constructor de Backstage.
     *
     * @param codigo      Código único alfanumérico de la zona
     * @param descripcion Descripción de la zona
     * @param personas    Lista de personas actualmente en la zona
     * @param capMax      Capacidad máxima de la zona
     */
    public Backstage(String codigo, String descripcion, LinkedList<Persona> personas, int capMax) {
        super(codigo, descripcion, personas, capMax);
    }

    @Override
    public String toString() {
        return "Backstage " + super.toString();
    }
}
