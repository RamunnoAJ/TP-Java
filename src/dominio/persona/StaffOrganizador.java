package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Zona;

import java.util.List;

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
