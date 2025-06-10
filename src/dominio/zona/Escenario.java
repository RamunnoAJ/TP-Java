package dominio.zona;

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

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public boolean agregarEventoMusical(Evento evento) {
        if (evento != null && !eventos.contains(evento)) {
            eventos.add(evento);
            return true;
        } else {
            return false;
        }
    }

    //BORRO EL MUESTRA DE LOS EVENTOS MUSICALES, ESO LO TENDRIA QUE IR HACIENDO LA INTERFAZ GRAFICA
    //CON EL GETTER DE LA LISTA DE EVENTOS, NO TENDRIA QUE ESTAR EN LA CLASE DOMINIO
}
