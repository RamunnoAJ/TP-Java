package Persona;

import java.util.List;
import Acceso.Acceso;

abstract public class Persona {
    private int id;
    private String nombre;
    private List<Acceso> accesos;

    public Persona(int id, String nombre, List<Acceso> accesos) {
        this.id = id;
        this.nombre = nombre;
        this.accesos = accesos;
    }

    public List<Acceso> getAccesos() {
        return accesos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAccesos(List<Acceso> accesos) {
        this.accesos = accesos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "Nombre: " + nombre + ", Id: " + id + "}";
    }

    //hay que desarrollar una funcion de este estilo
    public boolean tieneAcceso(Zona z){

    }
}

