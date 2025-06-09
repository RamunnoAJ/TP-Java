package Persona;

import Acceso.Acceso;
import Zona.Zona;
import java.util.List;

public class Artista extends PersonaConAccesoRestringido{
    public Artista(int id, String nombre, List<Acceso> accesos, Zona zona) {
        super(id, nombre, accesos, zona);
    }

    @Override
    public String toString() {
        return "Artista. "+super.toString();
    }

    @Override
    public boolean tieneAcceso(Zona z) {
        //hay que desarrollarlo
    }
}

