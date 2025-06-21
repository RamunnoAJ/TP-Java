package dominio.acceso;


import dominio.zona.Zona;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Representa un registro de acceso de una persona a una zona en un momento dado.
 */
public class Acceso implements Serializable {
    private Zona zona;
    private LocalDateTime fecha;
    private int duracion;
    private Estado estado;

    /**
     * Crea un nuevo registro de acceso.
     *
     * @param zona     la zona a la que se accede
     * @param duracion la duración inicial (en minutos) del acceso
     * @param estado   el estado del acceso (AUTORIZADO o DENEGADO)
     */
    public Acceso(Zona zona, int duracion, Estado estado) {
        this.zona = zona;
        this.fecha = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.duracion = duracion;
        this.estado = estado;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Devuelve una representación en texto del registro de acceso.
     *
     * @return cadena formateada con detalles de zona, fecha, duración y estado
     */
    @Override
    public String toString() {
        return String.format("Zona:  %s (%s)  –  Fecha: %s  – Duración: %d min  – Estado: %s",
                zona.getDescripcion(), zona.getCodigo(), fecha, duracion, estado);
    }
}
