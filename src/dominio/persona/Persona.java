package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Zona;

import java.io.Serializable;
import java.util.List;

abstract public class Persona implements Serializable {
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

    public abstract boolean tieneAcceso(Zona z);

    public boolean equals(Persona p){
        return id==p.getId();
    }

    public boolean agregarAcceso(Acceso a) {
        if (accesos == null || accesos.contains(a)){
            return false;
        }else{
            accesos.add(a);
            return true;
        }
    }

    public void mostrarAccesos(){
        //NO va a ser la persona la que muestre los datos, va a ser la interfaz grafica
        //Asi que esta funcion no tendria que estar en persona
    }
}


