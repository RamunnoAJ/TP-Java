package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Backstage;
import dominio.zona.Escenario;
import dominio.zona.Zona;
import dominio.zona.ZonaComun;

import java.util.List;

/**
 * Representa a un artista, hereda de Persona y agrega acceso a un escenario
 */
public class Artista extends Persona{
    private Escenario escenario;

    /**
     * Crea un nuevo Artista.
     *
     * @param id       Identificador único del artista
     * @param nombre   Nombre del artista
     * @param accesos  Lista de accesos registrados para el artista
     * @param z        Zona asignada al artista (debe ser un Escenario)
     */
    public Artista(int id, String nombre, List<Acceso> accesos, Zona z) {

        super(id, nombre, accesos);
        this.escenario = (Escenario)z;
    }

    @Override
    public String toString() {
        return " Artista. "+super.toString();
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    /**
     * Indica si el artista tiene acceso a la zona dada.
     * Puede acceder a zonas comunes, a su escenario o al backstage.
     *
     * @param z Zona a verificar
     * @return true si tiene acceso, false en caso contrario
     */
    @Override
    public boolean tieneAcceso(Zona z) {
        return (z instanceof ZonaComun ||(z instanceof Escenario && escenario.equals(z))||z instanceof Backstage);
    }
}

