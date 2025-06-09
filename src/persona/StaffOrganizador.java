package persona;

import java.util.List;
import acceso.Acceso;
import zona.Zona;

public class StaffOrganizador extends Persona{
    public StaffOrganizador(int id, String nombre, List<Acceso> accesos) {
        super(id, nombre, accesos);
    }

    @Override
    public String toString() {
        return "Staff Organizador" + super.toString();
    }

    @Override
    //Devuelve siempre true porque el staff organizador puede acceder a cualquier parte del evento
    public boolean tieneAcceso(Zona z) {
        return true;
    }
}
