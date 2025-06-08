package Zona;

public class ZonaRestringida extends Zona{
    private int capMax;

    ZonaRestringida(String codigo, String descripcion, int capMax) {
        super(codigo, descripcion);
        this.capMax=capMax;
    }

    public int getCapMax() {
        return capMax;
    }

    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }

    @Override
    public String toString() {
        return "ZonaRestringida. " + "Capacidad maxima: " + capMax + super.toString();
    }
}
