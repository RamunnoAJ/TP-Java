package dominio.zona;

import dominio.persona.Persona;

import java.util.*;
import java.lang.String;

public class Escenario extends ZonaRestringida{
    private List<Evento> eventos;
    public Escenario(String codigo, String descripcion, LinkedList<Persona> personas, int capMax, List<Evento> eventos){
        super(codigo, descripcion, personas, capMax);
        this.eventos = eventos != null ? eventos :new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Escenario. " + super.toString();
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

}
