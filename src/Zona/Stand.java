package Zona;

import Persona.Comerciante;

public class Stand extends ZonaRestringida {
    private String ubicacion;
    private ZonaComun zonacomun;
    private Comerciante comerciante;

    public Stand(String codigo, String descripcion, int CapMax, String ubicacion, ZonaComun zonacomun, Comerciante comerciante) {
        super(codigo, descripcion, CapMax);
        this.ubicacion = ubicacion;
        this.zonacomun = zonacomun;
        this.comerciante = comerciante;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ZonaComun getZonacomun() {
        return zonacomun;
    }

    public void setZonacomun(ZonaComun zonacomun) {
        this.zonacomun = zonacomun;
    }

    public Comerciante getComerciante() {
        return comerciante;
    }

    public void setComerciante(Comerciante comerciante) {
        this.comerciante = comerciante;
    }
}
