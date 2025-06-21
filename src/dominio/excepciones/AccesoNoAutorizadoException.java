package dominio.excepciones;


import dominio.persona.Persona;
import dominio.zona.Zona;

public class AccesoNoAutorizadoException extends RuntimeException {
    public AccesoNoAutorizadoException(Persona p, Zona z) {
      super(p.toString() + " no autorizada para " + z.toString());
    }
}
