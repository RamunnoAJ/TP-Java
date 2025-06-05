package zona;

public class ZonaRestringida extends Zona{
    private int CapMax;

    ZonaRestringida(String[] codigo, String[] descripcion, int CapMax) {
        super(codigo, descripcion);
        this.CapMax=CapMax;
    }
}
