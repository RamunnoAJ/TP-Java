package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Zona;
import java.io.Serializable;
import java.util.List;

abstract public class Persona implements Serializable {
    private final int id;
    private final String nombre;
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

    public String toString() {
        return " Nombre: " + nombre + ", Id: " + id;
    }

    public abstract boolean tieneAcceso(Zona z);

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;

        return this.id == persona.getId();
    }

    public boolean agregarAcceso(Acceso a) {
        if (accesos == null || accesos.contains(a)){
            return false;
        }else{
            accesos.add(a);
            return true;
        }
    }

}


