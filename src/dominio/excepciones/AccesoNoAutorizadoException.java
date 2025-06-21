package dominio.excepciones;


import dominio.persona.Persona;
import dominio.zona.Zona;

/**
 * Excepción lanzada cuando una persona intenta acceder a una zona
 * para la cual no tiene permiso.
 */

public class AccesoNoAutorizadoException extends RuntimeException {
    /**
     * Crea una excepción indicando que la persona no está autorizada
     * para acceder a la zona especificada.
     *
     * @param p la persona que intentó el acceso
     * @param z la zona a la que se intentó acceder
     */
    public AccesoNoAutorizadoException(Persona p, Zona z) {
      super(p.toString() + " no autorizada para " + z.toString());
    }
}
