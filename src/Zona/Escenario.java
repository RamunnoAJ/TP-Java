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
}
