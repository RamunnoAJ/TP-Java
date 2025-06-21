package dominio.zona;

import dominio.persona.Persona;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Clase abstracta que representa una zona del festival.
 */
public abstract class Zona implements Serializable {
    private String codigo;
    private String descripcion;
    private LinkedList<Persona> personas;

    /**
     * Constructor de Zona.
     *
     * @param codigo      código alfanumérico único de la zona
     * @param descripcion descripción de la zona
     * @param personas    lista inicial de personas en la zona
     */
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
        return " Descripción: " + descripcion + ", Codigo: " + codigo+ ".";
    }

    public int getCapMax(){
        return 0;
    }

    /**
     * Compara dos zonas por su código.
     *
     * @param o la otra zona a comparar
     * @return true si los códigos son iguales, false en caso contrario
     */
    public boolean equals(Zona o) {
        return codigo.equals(o.getCodigo());
    }

    /**
     * Agrega una persona a la zona si no está ya presente.
     *
     * @param p persona a agregar
     * @return true si se agrega con éxito, false si ya estaba o lista es null
     */
    public boolean agregarPersona(Persona p) {
        if (personas != null && !personas.contains(p)) {
            personas.add(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina una persona de la zona si está presente.
     *
     * @param p persona a eliminar
     * @return true si se elimina con éxito, false si no estaba o lista es null
     */
    public boolean eliminarPersona(Persona p) {
        if (personas != null && personas.contains(p)) {
            personas.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Indica si aún hay espacio en la zona.
     *
     * @return true si hay espacio, false en caso contrario
     */
    public boolean hayEspacio (){return true;}

    /**
     * Comprueba si una persona está actualmente en la zona.
     *
     * @param p persona a buscar
     * @return true si la persona está en la zona, false de lo contrario
     */
    public boolean estaPersona(Persona p) {
        return personas != null && personas.contains(p);
    }
}
