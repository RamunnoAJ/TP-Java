package Persona;

import java.util.List;
import Acceso.Acceso;
import Zona.Stand;
import Zona.Zona;

public class Comerciante extends PersonaConAccesoRestringido{
    private Stand stand;

    public Comerciante(int id, String nombre, List<Acceso> accesos, Zona zona, Stand stand) {
        super(id, nombre, accesos, zona);
        this.stand = stand;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    @Override
    public String toString() {
        return "Comerciante. "+super.toString();
    }
}
