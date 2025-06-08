package Zona;
import java.util.*;
import java.lang.String;

public class Escenario extends Zona{
    private int capMax;
    private List<Evento> eventos;
    public Escenario(String codigo, String descripcion, int capMax){
        super(codigo, descripcion);
        this.capMax = capMax;
    }

    public int getCapMax(){
        return capMax;
    }
    public void setCapMax(int capMax){
        this.capMax = capMax;
    }

    @Override
    public String toString() {
        return "Escenario. Capacidad Maxima: "+ capMax + super.toString();
    }
}
