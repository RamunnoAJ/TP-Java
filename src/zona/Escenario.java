package zona;
import java.util.*;
import java.lang.String;

public class Escenario extends Zona{
    private int CapMax;
    private List<Evento> eventos;
    Escenario(String[] codigo,String[] descripcion,int CapMax){
        super(codigo, descripcion);
        this.CapMax = CapMax;
    }
}
