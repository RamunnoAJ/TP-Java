package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Stand;
import dominio.zona.Zona;
import dominio.zona.ZonaComun;

import java.util.List;

/**
 * Representa a un comerciante, que trabaja en un stand
 * y puede acceder a zonas comunes y a su propio stand.
 */
public class Comerciante extends Persona{
    private Stand stand;

    /**
     * Crea un nuevo Comerciante.
     *
     * @param id       Identificador único del comerciante
     * @param nombre   Nombre del comerciante
     * @param accesos  Lista de accesos registrados para el comerciante
     * @param z        Zona asociada (debe ser un Stand)
     */
    public Comerciante(int id, String nombre, List<Acceso> accesos, Zona z) {
        super(id, nombre, accesos);
        this.stand = (Stand)z;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    @Override
    public String toString() {
        return " Comerciante. " + super.toString();
    }

    /**
     * Indica si el comerciante tiene acceso a la zona dada.
     * Puede acceder a zonas comunes o a su propio stand.
     *
     * @param z Zona a verificar
     * @return true si tiene acceso, false en caso contrario
     */
    public boolean tieneAcceso(Zona z) {
        return (z instanceof ZonaComun ||(z instanceof Stand && stand.equals(z)));
    }
}
