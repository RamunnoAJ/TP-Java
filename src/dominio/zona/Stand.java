package dominio.zona;

import dominio.persona.Comerciante;

import java.util.List;

public class Stand extends ZonaRestringida {
    private String ubicacion;
    private ZonaComun zonacomun;
    private Comerciante responsable;
    private List<Comerciante> empleados;

    public Stand(String codigo, String descripcion, int CapMax, String ubicacion, ZonaComun zonacomun, Comerciante comerciante) {
        super(codigo, descripcion, CapMax);
        this.ubicacion = ubicacion;
        this.zonacomun = zonacomun;
        this.responsable = comerciante;
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

    public Comerciante getResponsable() {
        return responsable;
    }

    public void setResponsable(Comerciante comerciante) {
        this.responsable = comerciante;
    }

    public List<Comerciante> getEmpleados(){
        return empleados;
    }

    public void setEmpleados(List<Comerciante> empleados) {
        this.empleados = empleados;
    }

    public boolean agregarEmpleado(Comerciante comerciante) {
        if(empleados.contains(comerciante)||empleados==null){
            return false;
        } else {
            empleados.add(comerciante);
            return true;
        }
    }

    public boolean eliminarEmpleado(Comerciante comerciante) {
        if(!empleados.contains(comerciante)||empleados==null){
            return false;
        }else{
            empleados.remove(comerciante);
            return true;
        }
    }
}
