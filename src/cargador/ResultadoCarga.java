package cargador;

import dominio.acceso.Acceso;
import dominio.zona.Zona;
import dominio.persona.Persona;

import java.util.List;

/**
 * Contiene los resultados de la carga desde XML:
 * las zonas, las personas y sus accesos.
 */
public class ResultadoCarga {
    private List<Zona> zonas;
    private List<Persona> personas;
    private List<Acceso> accesos;

    /**
     * Construye un resultado de carga con las zonas y personas procesadas.
     *
     * @param zonas     lista de zonas cargadas
     * @param personas  lista de personas cargadas
     */
    public ResultadoCarga(List<Zona> zonas, List<Persona> personas) {
        this.zonas = zonas;
        this.personas = personas;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public List<Acceso> getAccesos() {
        return accesos;
    }
}
