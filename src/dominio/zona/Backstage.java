package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

public class Backstage extends ZonaRestringida {
    public Backstage(String codigo, String descripcion, LinkedList<Persona> personas, int capMax) {
        super(codigo, descripcion, personas, capMax);
    }

    @Override
    public String toString() {
        return "Backstage " + super.toString();
    }
}
