package Persona;

import Acceso.Acceso;
import Zona.Zona;
import Zona.Escenario;
import Zona.ZonaComun;
import java.util.List;

public class Artista extends Persona{
    private Escenario escenario;

    public Artista(int id, String nombre, List<Acceso> accesos, Zona z) {

        super(id, nombre, accesos);
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
    //En caso de agregar otra zona restringida a la cual un artista tendria acceso, agregarla con otro ||
    public boolean tieneAcceso(Zona z) {
        if (z instanceof ZonaComun ||(z instanceof Escenario && escenario.equals(z))){
            return true;
        }else{
            return false;
        }
    }
}

