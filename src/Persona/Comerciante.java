package Persona;

import java.util.List;
import Acceso.Acceso;
import Zona.Stand;
import Zona.ZonaComun;
import Zona.Zona;

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
        return "Comerciante. "+super.toString();
    }

    public boolean tieneAcceso(Zona z) {
        if (z instanceof ZonaComun ||(z instanceof Stand && stand.equals(z))||estaPermitida(z)){
            return true;
        }else{
            return false;
        }
    }
}
