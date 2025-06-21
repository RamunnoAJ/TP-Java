package dominio.excepciones;

/**
 * Excepción lanzada cuando se intenta mover una persona a una zona nula (no existe destino).
 */
public class NULLdestinoException extends RuntimeException {
    /**
     * Crea la excepción con un mensaje indicando que el destino es nulo o inexistente.
     */
    public NULLdestinoException() {
        super("No existe el destino.");
    }
}
