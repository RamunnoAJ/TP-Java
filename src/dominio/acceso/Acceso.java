package dominio.acceso;


import dominio.zona.Zona;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Acceso implements Serializable {
    private Zona zona;
    private LocalDateTime fecha;
    private int duracion;
    private Estado estado;

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

    public String toString() {
        return String.format("Zona: %s  – Fecha: %s  – Duración: %d min  – Estado: %s",
                zona.getCodigo(), fecha, duracion, estado);
    }
}
