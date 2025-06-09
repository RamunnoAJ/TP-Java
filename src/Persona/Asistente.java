package Persona;

import java.util.List;
import Acceso.Acceso;
import Zona.Zona;
import Zona.Escenario;
import Zona.ZonaComun;

public class Asistente extends Persona{
    public Asistente(int id, String nombre, List<Acceso> accesos) {
        super(id, nombre, accesos);
    }

    @Override
    public String toString() {
        return "Asistente. " + super.toString();
    }

    @Override

    public boolean tieneAcceso(Zona z) {
        if(z instanceof ZonaComun ||z instanceof Escenario){
            return true;
        }else{
            return false;
        }
    }
}

