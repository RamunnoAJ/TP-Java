package Archivo;

import com.google.gson.*;
import dominio.acceso.Acceso;
import dominio.acceso.Estado;
import dominio.zona.Zona;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

public class AccesoDeserializer implements JsonDeserializer<Acceso> {
    private final Map<String, Zona> zonasDisponibles;

    public AccesoDeserializer(Map<String, Zona> zonasDisponibles) {
        this.zonasDisponibles = zonasDisponibles;
    }

    @Override
    public Acceso deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        String codigoZona = obj.get("zona").getAsString();
        Zona zona = zonasDisponibles.get(codigoZona);
        if(zona == null) {
            throw new JsonParseException("Zona no encontrada");
        }
        String fechaTexto = obj.get("fecha").getAsString();
        String horaTexto = obj.get("hora").getAsString();
        int minutos = obj.get("cantMinutos").getAsInt();
        String estadoTexto = obj.get("estado").getAsString();

        // Combinar fecha y hora
        String fechaCompleta = fechaTexto + " " + horaTexto;

        // Parsear a LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy HH:mm", new Locale("es", "ES"));
        LocalDateTime fecha = LocalDateTime.parse(fechaCompleta, formatter);

        // Convertir texto a enum
        Estado estado = Estado.valueOf(estadoTexto.toUpperCase());

        return new Acceso(zona, fecha, minutos, estado);
    }
}
