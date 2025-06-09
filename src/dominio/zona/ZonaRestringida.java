package dominio.zona;

public abstract class ZonaRestringida extends Zona{
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
    public boolean hayEspacio (){return (capMax>= getPersonas().size());}

    @Override
    public String toString() {
        return "ZonaRestringida. " + "Capacidad maxima: " + capMax + super.toString();
    }
}
