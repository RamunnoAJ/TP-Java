package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Stand;
import dominio.zona.Zona;
import dominio.zona.ZonaComun;

import java.util.List;


public class Comerciante extends Persona{
    private Stand stand;

    public Comerciante(int id, String nombre, List<Acceso> accesos, Zona z) {
        super(id, nombre, accesos);
        this.stand = (Stand)z;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    @Override
    public String toString() {
        return " Comerciante. " + super.toString();
    }

    public boolean tieneAcceso(Zona z) {
        return (z instanceof ZonaComun ||(z instanceof Stand && stand.equals(z)));
    }
}
