package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Zona;

import java.util.List;

/**
 * Representa al staff organizador del festival.
 * Permite acceso a todas las zonas.
 */
public class StaffOrganizador extends Persona{
    /**
     * Constructor de StaffOrganizador.
     *
     * @param id       Identificador único del staff
     * @param nombre   Nombre completo del staff
     * @param accesos  Lista de accesos iniciales (puede estar vacía)
     */
    public StaffOrganizador(int id, String nombre, List<Acceso> accesos) {
        super(id, nombre, accesos);
    }

    @Override
    public String toString() {
        return " Staff Organizador. " + super.toString();
    }

    /**
     * Indica si este staff organizador tiene acceso a la zona indicada.
     * @param z Zona a la cual se desea verificar el acceso
     * @return true siempre, ya que el staff organizador puede acceder a todas las zonas
     */
    @Override
    public boolean tieneAcceso(Zona z) {
        return true;
    }
}
