package Persona;

import Acceso.Acceso;
import Zona.Zona;

import java.util.List;

public class PersonaConAccesoRestringido extends Persona {
    private Zona zona;

    public PersonaConAccesoRestringido(int id, String nombre, List<Acceso> accesos, Zona zona) {
        super(id, nombre, accesos);
        this.zona = zona;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
