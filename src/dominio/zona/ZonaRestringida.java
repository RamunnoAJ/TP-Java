package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

/**
 * Representa una zona restringida del festival con capacidad máxima.
 */
public abstract class ZonaRestringida extends Zona{
    private int capMax;

    /**
     * Constructor de ZonaRestringida.
     *
     * @param codigo      código alfanumérico único de la zona
     * @param descripcion descripción de la zona
     * @param personas    lista inicial de personas en la zona
     * @param capMax      capacidad máxima de la zona
     */
    ZonaRestringida(String codigo, String descripcion, LinkedList<Persona> personas, int capMax) {
        super(codigo, descripcion, personas);
        this.capMax=capMax;
    }

    public int getCapMax() {
        return capMax;
    }

    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }

    /**
     * Indica si queda espacio para agregar más personas.
     *
     * @return true si el número de personas actual es menor que la capacidad máxima; false en caso contrario
     */
    @Override
    public boolean hayEspacio (){return (capMax> getPersonas().size());}

    @Override
    public String toString() {
        return super.toString() + " Capacidad maxima: " + capMax;
    }
}
