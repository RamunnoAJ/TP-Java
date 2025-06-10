package dominio.excepciones;

public class NULLorigenException extends RuntimeException {
    public NULLorigenException() {
        super("no existe el origen");
    }
}
