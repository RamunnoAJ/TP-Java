package dominio.excepciones;

import dominio.zona.Zona;

public class ControlInvalidoException extends RuntimeException {
    public ControlInvalidoException(Zona origen, Zona destino) {
        if (origen == null)
          throw new NULLorigenException();
        else if (destino==null)
            throw new NULLdestinoException();
        else
            throw new ZonaInvalidaException(origen);
    }
}
