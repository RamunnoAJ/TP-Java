package zona;

public class Stand extends ZonaRestringida {
    private String[] ubicacion;
    private ZonaComun zonacomun;
    private Comerciante comerciante;
    Stand(String[] codigo, String[] descripcion, int CapMax,String[] ubicacion,ZonaComun zonacomun,Comerciante comerciante) {
        super(codigo, descripcion, CapMax);
        this.ubicacion = ubicacion;
        this.zonacomun = zonacomun;
        this.comerciante = comerciante;
    }
}
