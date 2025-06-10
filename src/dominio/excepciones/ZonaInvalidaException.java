package dominio.excepciones;

import dominio.zona.Zona;

public class ZonaInvalidaException extends RuntimeException {
    public ZonaInvalidaException(Zona origen) {
        super("la zona "+origen+"no es valida");
    }
}
