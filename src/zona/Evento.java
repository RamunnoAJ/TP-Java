package zona;

public class Evento {
    private String fecha;
    private String hora;
    private String artista;

    public Evento(String fecha, String hora, String artista){
        this.fecha = fecha;
        this.hora = hora;
        this.artista = artista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String toString(){
        return "Fecha: " + fecha + "\nHora: " + hora + "\nArtista: " + artista;
    }
}
