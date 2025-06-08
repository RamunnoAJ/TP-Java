package Zona;

public class ZonaComun extends Zona{
    public ZonaComun(String codigo, String descripcion) {
        super(codigo, descripcion);
    }

    @Override
    public String toString() {
        return "Zona comun. "+ super.toString();
    }
}
