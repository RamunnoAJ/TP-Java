package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

public abstract class  ZonaComun extends Zona{
    public ZonaComun(String codigo, String descripcion, LinkedList<Persona> personas) {
        super(codigo, descripcion, personas);
    }

    @Override
    public String toString() {
        return " Zona comun. "+ super.toString();
    }
}
