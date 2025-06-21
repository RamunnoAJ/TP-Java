package dominio.excepciones;

/**
 * Excepción lanzada cuando la zona de origen y la de destino son iguales
 * al intentar mover una persona.
 */
public class DestinoInvalidoException extends RuntimeException {
    /**
     * Crea la excepción con un mensaje indicando que origen y destino coinciden.
     */
    public DestinoInvalidoException() {
        super("La zona origen es igual a la de destino.");
    }
}
