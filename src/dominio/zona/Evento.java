package dominio.zona;

import dominio.persona.Artista;
import java.io.Serializable;

/**
 * Representa un evento musical programado en un escenario.
 */
public class Evento implements Serializable {
    private String fecha;
    private String hora;
    private Artista artista;

    /**
     * Constructor de Evento.
     *
     * @param fecha   Fecha en formato texto del evento
     * @param hora    Hora en formato texto del evento
     * @param artista Artista que participa en el evento
     */
    public Evento(String fecha, String hora, Artista artista){
        this.fecha = fecha;
        this.hora = hora;
        this.artista = artista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String toString(){
        return "Fecha: " + fecha + "\nHora: " + hora + "\nArtista: " + artista;
    }
}
