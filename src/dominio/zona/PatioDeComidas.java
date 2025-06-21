package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

/**
 * Representa una zona común de tipo patio de comidas.
 */
public class PatioDeComidas extends ZonaComun{
    /**
     * Constructor de PatioDeComidas.
     *
     * @param codigo      Código alfanumérico único de la zona
     * @param descripcion Descripción de la zona
     * @param personas    Lista inicial de personas presentes en la zona
     */
    public PatioDeComidas(String codigo, String descripcion, LinkedList<Persona> personas) {
        super(codigo, descripcion, personas);
    }
}
