package dominio.persona;

import dominio.acceso.Acceso;
import dominio.zona.Zona;
import java.io.Serializable;
import java.util.List;

/**
 * Clase abstracta que representa una persona en el sistema,
 * con un identificador, nombre y lista de accesos.
 */
abstract public class Persona implements Serializable {
    private final int id;
    private final String nombre;
    private List<Acceso> accesos;

    /**
     * Constructor de Persona.
     *
     * @param id       Identificador único de la persona
     * @param nombre   Nombre completo de la persona
     * @param accesos  Lista de accesos iniciales de la persona
     */
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

    /**
     * Define si la persona tiene acceso a una zona dada.
     *
     * @param z Zona a verificar
     * @return true si la persona puede acceder, false en caso contrario
     */
    public abstract boolean tieneAcceso(Zona z);

    /**
     * Compara esta persona con otro objeto.
     * Dos personas son iguales si comparten el mismo ID.
     *
     * @param o Objeto a comparar
     * @return true si el objeto es una Persona con el mismo ID
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return this.id == persona.getId();
    }

    /**
     * Agrega un acceso a la lista de accesos si no existe.
     *
     * @param a Acceso a agregar
     * @return true si se agregó exitosamente, false si ya existía o la lista es nula
     */
    public boolean agregarAcceso(Acceso a) {
        if (accesos == null || accesos.contains(a)){
            return false;
        }else{
            accesos.add(a);
            return true;
        }
    }

}


