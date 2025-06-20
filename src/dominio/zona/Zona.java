package dominio.zona;

import dominio.persona.Persona;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class Zona implements Serializable {
    private String codigo;
    private String descripcion;
    private LinkedList<Persona> personas;

    public Zona(String codigo, String descripcion, LinkedList<Persona> personas){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.personas = personas != null ? personas : new LinkedList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LinkedList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(LinkedList<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo + ", descripcion=" + descripcion;
    }

    public int getCapMax(){
        return 0;
    }

    public boolean equals(Zona o) {
        return codigo.equals(o.getCodigo());
    }

    public boolean agregarPersona(Persona p) {
        if (personas != null && !personas.contains(p)) {
            personas.add(p);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarPersona(Persona p) {
        if (personas != null && personas.contains(p)) {
            personas.remove(p);
            return true;
        } else {
            return false;
        }
    }

    public boolean hayEspacio (){return true;}

    public boolean estaPersona(Persona p) {
        return personas != null && personas.contains(p);
    }
}
