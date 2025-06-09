package dominio.zona;

import dominio.persona.Persona;

import java.util.LinkedList;

public abstract class Zona {
    private String codigo;
    private String descripcion;
    private LinkedList<Persona> personas;

    public Zona(String codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
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
        return "Zona{" + "codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }

    public int getCapMax(){
        return 0;
    }

    public boolean equals(Zona o) {
        return codigo.equals(o.getCodigo());
    }

    public void agregarPersona(Persona p){
        personas.add(p);
    }

    public void eliminarPersona(Persona p){
        personas.remove(p);
    }

    public boolean hayEspacio (){return true;}
}
