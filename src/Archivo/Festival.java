package Archivo;
import dominio.zona.Zona;
import java.util.List;

public class Festival {
     static List<Zona> zonas;
    public static List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }
}
