package dominio.zona;

public abstract class  ZonaComun extends Zona{
    public ZonaComun(String codigo, String descripcion) {
        super(codigo, descripcion);
    }

    //estaria bueno hacer mas zonas comunes, y zona comun y zona restringida hacerlas abstractas
    @Override
    public String toString() {
        return "Zona comun. "+ super.toString();
    }
}
