package Acceso;

import Zona.Zona;

public class Acceso {
    private Zona zona;
    private String fecha;
    private String hora;
    private int cantMinutos;
    private Estado estado;

    public Acceso(Zona zona, String fecha, String hora, int cantMinutos, Estado estado) {
        this.zona = zona;
        this.fecha = fecha;
        this.hora = hora;
        this.cantMinutos = cantMinutos;
        this.estado = estado;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
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
}
