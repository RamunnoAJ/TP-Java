package dominio.excepciones;


import dominio.zona.ZonaRestringida;

/**
 * Excepción lanzada cuando se intenta acceder a una zona restringida
 * que ha alcanzado su capacidad máxima.
 */
public class CapacidadAlcanzadaException extends RuntimeException {
    /**
     * Crea la excepción indicando que la zona restringida está llena.
     *
     * @param z la zona restringida que ha alcanzado su capacidad máxima
     */
    public CapacidadAlcanzadaException(ZonaRestringida z) {

      super(z.toString() + " está al máximo de su capacidad (" + z.getCapMax() + ")");
    }
}
