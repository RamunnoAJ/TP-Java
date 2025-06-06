package Persona;

import Acceso.Acceso;
import Zona.Zona;
import java.util.List;

public class Artista extends PersonaConAccesoRestringido{
    public Artista(int id, String nombre, List<Acceso> accesos, Zona zona) {
        super(id, nombre, accesos, zona);
    }
}
