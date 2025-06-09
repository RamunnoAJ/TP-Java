package excepciones;

import persona.Persona;
import zona.Zona;

public class AccesoNoAutorizadoException extends RuntimeException {
    public AccesoNoAutorizadoException(Persona p, Zona z) {
      super(p.toString() + " no autorizada para " + z.toString());
    }
}
