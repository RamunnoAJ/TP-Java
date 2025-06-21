package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

public class PatioDeComidas extends ZonaComun{
    public PatioDeComidas(String codigo, String descripcion, LinkedList<Persona> personas) {
        super(codigo, descripcion, personas);
    }
}
