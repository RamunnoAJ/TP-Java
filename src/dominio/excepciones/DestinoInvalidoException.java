package dominio.excepciones;

public class DestinoInvalidoException extends RuntimeException {
    public DestinoInvalidoException() {
        super("La zona origen es igual a la de destino.");
    }
}
