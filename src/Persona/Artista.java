package Persona;

import Acceso.Acceso;
import Zona.Zona;
import Zona.Escenario;
import Zona.ZonaComun;
import java.util.List;

public class Artista extends PersonaConAccesoRestringido{
    private Escenario escenario;

    public Artista(int id, String nombre, List<Acceso> accesos,List<Zona> zon, Zona z) {

        super(id, nombre, accesos, zon);
        this.escenario = (Escenario)z;
    }

    @Override
    public String toString() {
        return "Artista. "+super.toString();
    }

    public Escenario getEscenario() {
        return escenario;
    }
    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    @Override
    public boolean tieneAcceso(Zona z) {
        if (z instanceof ZonaComun ||(z instanceof Escenario && escenario.equals(z))||estaPermitida(z)){
            return true;
        }else{
            return false;
        }
    }
}

