package Persona;

import Acceso.Acceso;

public class Artista extends PersonaConAccesoRestringido{
    public Artista(int id, String nombre, Acceso acceso, Zona zona) {
        super(id, nombre, acceso, zona);
    }
}
