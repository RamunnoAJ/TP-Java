package Persona;

import java.util.List;
import Acceso.Acceso;
import Zona.Stand;
import Zona.ZonaComun;
import Zona.Zona;

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
        return "Comerciante. "+super.toString();
    }

    //En caso de agregar otra zona restringida a la cual un artista tendria acceso, agregarla con otro ||
    public boolean tieneAcceso(Zona z) {
        if (z instanceof ZonaComun ||(z instanceof Stand && stand.equals(z))){
            return true;
        }else{
            return false;
        }
    }
}
