package Excepciones;

import Zona.ZonaRestringida;

public class CapacidadAlcanzadaException extends RuntimeException {
    public CapacidadAlcanzadaException(ZonaRestringida z) {

      super(z.toString() + " está al máximo de su capacidad (" + z.getCapMax() + ")");
    }
}
