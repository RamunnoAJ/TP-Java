package zona;
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

    public boolean agregarEventoMusical(Evento evento) {
        if (evento != null) {
            eventos.add(evento);
            return true;
        } else {
            return false;
        }
    }

//lo unico que se usaria de aca para mostrar el getter de las listas
    public void muestraEventosMusicales(){ //El muestra lo va a hacer la interfaz grafica
        System.out.println("Eventos musicales: ");
        for(Evento evento : eventos){
            System.out.println(evento.toString());
        }
    }
}
