package Persona;

import java.util.List;
import Acceso.Acceso;

public class StaffOrganizador extends Persona{
    public StaffOrganizador(int id, String nombre, List<Acceso> accesos) {
        super(id, nombre, accesos);
    }

    @Override
    public String toString() {
        return "Staff Organizador" + super.toString();
    }
}
