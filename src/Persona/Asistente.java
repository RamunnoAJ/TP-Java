package Persona;

import java.util.List;
import Acceso.Acceso;
import Zona.Zona;

public class Asistente extends PersonaConAccesoRestringido{
    public Asistente(int id, String nombre, List<Acceso> accesos, Zona zona) {
        super(id, nombre, accesos, zona);
    }

    @Override
    public String toString() {
        return "Asistente. " + super.toString();
    }
}

