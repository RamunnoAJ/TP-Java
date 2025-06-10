package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Stand;
import dominio.zona.Zona;
import dominio.zona.ZonaComun;

import java.util.List;


public class Comerciante extends PersonaConAccesoRestringido{
    private Stand stand;

    public Comerciante(int id, String nombre, List<Acceso> accesos, List<Zona> zon, Zona z) {
        super(id, nombre, accesos,zon);
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
        return "Comerciante. " + super.toString();
    }

    public boolean tieneAcceso(Zona z) {
        return (z instanceof ZonaComun ||(z instanceof Stand && stand.equals(z))||estaPermitida(z));
    }
}
