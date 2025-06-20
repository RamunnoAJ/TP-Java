package dominio.excepciones;

import dominio.zona.Zona;

public class NULLdestinoException extends RuntimeException {
    public NULLdestinoException() {
        super("No existe el destino.");
    }
}
