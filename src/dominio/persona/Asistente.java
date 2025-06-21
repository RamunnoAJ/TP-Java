package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Escenario;
import dominio.zona.Zona;
import dominio.zona.ZonaComun;

import java.util.List;

/**
 * Representa a un asistente (espectador), que puede acceder
 * a zonas comunes y escenarios.
 */
public class Asistente extends Persona{
    /**
     * Crea un nuevo Asistente.
     *
     * @param id       Identificador único del asistente
     * @param nombre   Nombre del asistente
     * @param accesos  Lista de accesos registrados para el asistente
     */
    public Asistente(int id, String nombre,List<Acceso> accesos) {
        super(id, nombre, accesos);
    }

    @Override
    public String toString() {
        return " Asistente. " + super.toString();
    }

    /**
     * Indica si el asistente tiene acceso a la zona dada.
     * Puede acceder solo a zonas comunes y escenarios.
     *
     * @param z Zona a verificar
     * @return true si tiene acceso, false en caso contrario
     */
    @Override
    public boolean tieneAcceso(Zona z) {
        return (z instanceof ZonaComun ||z instanceof Escenario);
    }
}

