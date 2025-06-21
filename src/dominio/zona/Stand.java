package dominio.zona;

import dominio.persona.Comerciante;
import dominio.persona.Persona;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Representa un stand de comida o merchandising dentro de una zona restringida.
 */
public class Stand extends ZonaRestringida {
    private String ubicacion;
    private ZonaComun zonacomun;
    private Comerciante responsable;
    private List<Comerciante> empleados;

    /**
     * Constructor de Stand.
     *
     * @param codigo      Código alfanumérico único del stand
     * @param descripcion Descripción del stand
     * @param personas    Lista inicial de personas presentes en la zona
     * @param CapMax      Capacidad máxima del stand
     * @param ubicacion   Ubicación dentro de la zona común
     * @param zonacomun   Zona común asociada al stand
     * @param responsable Comerciante responsable del stand
     * @param empleados   Lista inicial de empleados del stand
     */
    public Stand(String codigo, String descripcion, LinkedList<Persona> personas, int CapMax, String ubicacion, ZonaComun zonacomun, Comerciante responsable, List<Comerciante> empleados) {
        super(codigo, descripcion, personas, CapMax);
        this.ubicacion = ubicacion;
        this.zonacomun = zonacomun;
        this.responsable = responsable;
        this.empleados = empleados != null ? empleados : new ArrayList<>();
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

    /**
     * Agrega un empleado al stand, si no existe ya en la lista.
     *
     * @param comerciante el comerciante a agregar
     * @return true si se agregó, false si ya estaba o la lista es null
     */
    public boolean agregarEmpleado(Comerciante comerciante) {
        if(empleados.contains(comerciante)||empleados==null){
            return false;
        } else {
            empleados.add(comerciante);
            return true;
        }
    }

    /**
     * Elimina un empleado del stand, si existe en la lista.
     *
     * @param comerciante el comerciante a eliminar
     * @return true si se eliminó, false si no estaba o la lista es null
     */
    public boolean eliminarEmpleado(Comerciante comerciante) {
        if(!empleados.contains(comerciante)||empleados==null){
            return false;
        }else{
            empleados.remove(comerciante);
            return true;
        }
    }
}
