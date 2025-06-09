package dominio.excepciones;

public class ZonaInvalidaException extends RuntimeException {
    public ZonaInvalidaException() {
      super("Zona inválida");
    }//para diferenciar si es nulo
}
