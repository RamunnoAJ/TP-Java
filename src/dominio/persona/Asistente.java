package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Escenario;
import dominio.zona.Zona;
import dominio.zona.ZonaComun;

import java.util.List;

//No lo puse como persona con acceso restringido porque no puede acceder a ninguna zona que no sean todos los escenarios o las comunes
//entonces que tenga la lista de zonasPermitidas no tiene sentido
public class Asistente extends Persona{
    public Asistente(int id, String nombre,List<Acceso> accesos) {
        super(id, nombre, accesos);
    }

    @Override
    public String toString() {
        return "Asistente. " + super.toString();
    }

    @Override

    public boolean tieneAcceso(Zona z) {
        return (z instanceof ZonaComun ||z instanceof Escenario);

    }
}

