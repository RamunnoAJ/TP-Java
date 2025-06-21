package dominio.excepciones;

public class NULLdestinoException extends RuntimeException {
    public NULLdestinoException() {
        super("No existe el destino.");
    }
}
