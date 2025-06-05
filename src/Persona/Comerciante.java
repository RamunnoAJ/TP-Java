package Persona;

import Acceso.Acceso;

public class Comerciante extends PersonaConAccesoRestringido{
    private Stand stand;

    public Comerciante(int id, String nombre, Acceso acceso, Zona zona, Stand stand) {
        super(id, nombre, acceso, zona);
        this.stand = stand;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }
}
