package dominio.zona;

import dominio.persona.Persona;
import java.util.LinkedList;

public abstract class ZonaRestringida extends Zona{
    private int capMax;

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

    @Override
    public boolean hayEspacio (){return (capMax> getPersonas().size());}

    @Override
    public String toString() {
        return super.toString() + " Capacidad maxima: " + capMax;
    }
}
