package Excepciones;

public class ZonaInvalidaException extends RuntimeException {
    public ZonaInvalidaException() {
      super("Zona inválida");
    }
}
