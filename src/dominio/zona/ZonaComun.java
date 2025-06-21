package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

/**
 * Representa una zona común del festival, accesible por todos.
 */
public abstract class  ZonaComun extends Zona{
    /**
     * Constructor de ZonaComun.
     *
     * @param codigo      código alfanumérico único de la zona común
     * @param descripcion descripción de la zona común
     * @param personas    lista inicial de personas en la zona común
     */
    public ZonaComun(String codigo, String descripcion, LinkedList<Persona> personas) {
        super(codigo, descripcion, personas);
    }

    @Override
    public String toString() {
        return " Zona comun. "+ super.toString();
    }
}
