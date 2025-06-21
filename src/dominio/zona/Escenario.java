package dominio.zona;

import dominio.persona.Persona;

import java.util.*;
import java.lang.String;

/**
 * Representa un escenario del festival, que es una zona restringida
 * con eventos musicales programados.
 */
public class Escenario extends ZonaRestringida{
    private List<Evento> eventos;

    /**
     * Constructor de Escenario.
     *
     * @param codigo      Código único alfanumérico de la zona
     * @param descripcion Descripción de la zona
     * @param personas    Lista de personas actualmente en la zona
     * @param capMax      Capacidad máxima de la zona
     * @param eventos     Lista inicial de eventos musicales (puede ser null)
     */
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

    /**
     * Agrega un evento musical a la programación de este escenario,
     * si no está ya presente.
     *
     * @param evento Evento a agregar
     * @return true si se agregó con éxito; false si era null o ya existía
     */
    public boolean agregarEventoMusical(Evento evento) {
        if (evento != null && !eventos.contains(evento)) {
            eventos.add(evento);
            return true;
        } else {
            return false;
        }
    }

}
