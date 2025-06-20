package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Zona;

import java.util.List;

public class Concurrente extends PersonaConAccesoRestringido{
        public Concurrente(int id, String nombre, List<Acceso> accesos, List<Zona> zonas) {
            super(id, nombre, accesos, zonas);
        }

    @Override
    public boolean tieneAcceso(Zona z) {
        return this.estaPermitida(z);
    }
}
