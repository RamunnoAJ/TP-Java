package Persona;

import Acceso.Acceso;
import java.util.List;
import Zona.Stand;
import Zona.Zona;
import Zona.ZonaComun;

public class Empleado extends Persona {
    private Stand stand;

    public Empleado(int id, String nombre, List<Acceso> acceso, Zona z) {
        super(id,nombre,acceso);
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
        return "Empleado. " + super.toString();
    }

    @Override
    public boolean tieneAcceso(Zona z) {
        if (z instanceof ZonaComun ||(z instanceof Stand && stand.equals(z))){
            return true;
        }else{
            return false;
        }
    }
}
//falta implementar tiene accceso