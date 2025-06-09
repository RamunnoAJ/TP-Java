package Acceso;

import Zona.Zona;

import java.time.LocalDateTime;

public class Acceso {
    private Zona zona;
    private LocalDateTime fecha;

    private int cantMinutos;
    private Estado estado;

    public Acceso(Zona zona, int cantMinutos, Estado estado) {
        this.zona = zona;
        this.fecha = LocalDateTime.now();
        this.cantMinutos = cantMinutos;
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

    public int getCantMinutos() {
        return cantMinutos;
    }

    public void setCantMinutos(int cantMinutos) {
        this.cantMinutos = cantMinutos;
    }

    public String toString() {
        return zona.toString() + " " + fecha + " " + "Cantidad de minutos en la zona" + cantMinutos + "Acceso" + estado.toString();
    }
}
