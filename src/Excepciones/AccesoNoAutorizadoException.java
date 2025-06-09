package Excepciones;

import Persona.Persona;
import Zona.Zona;

public class AccesoNoAutorizadoException extends RuntimeException {
    public AccesoNoAutorizadoException(Persona p, Zona z) {
      super(p.toString() + " no autorizada para " + z.toString());
    }
}
