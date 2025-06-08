package Zona;

import Persona.Persona;

import java.util.LinkedList;

public class Zona {
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
}
