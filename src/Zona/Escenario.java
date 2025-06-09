package Zona;
import java.util.*;
import java.lang.String;

public class Escenario extends ZonaRestringida{
    private List<Evento> eventos;
    public Escenario(String codigo, String descripcion, int capMax){
        super(codigo, descripcion,capMax);
    }

    @Override
    public String toString() {
        return "Escenario. Capacidad Maxima: "+ getCapMax() + super.toString();
    }

    //Faltarian funciones para agregar eventos musicales, y no se si eliminar tambien

    public void muestraEventosMusicales(){
        System.out.println("Eventos musicales: ");
        for(Evento evento : eventos){
            System.out.println(evento.toString());
        }
    }
}
