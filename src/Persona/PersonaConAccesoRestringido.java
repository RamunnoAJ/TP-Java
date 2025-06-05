package Persona;

import Acceso.Acceso;

public class PersonaConAccesoRestringido extends Persona {
    private Zona zona;

    public PersonaConAccesoRestringido(int id, String nombre, Acceso acceso, Zona zona) {
        super(id, nombre, acceso);
        this.zona = zona;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
