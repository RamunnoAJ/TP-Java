package Archivo;
import com.google.gson.*;
import dominio.acceso.Acceso;
import dominio.persona.*;
import dominio.zona.Zona;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class PersonaDeserializer implements JsonDeserializer<Persona> {
    private final Map<String, Zona> mapaZonas;

    public PersonaDeserializer(Map<String, Zona> mapaZonas) {
        this.mapaZonas = mapaZonas;
    }

    public PersonaDeserializer() {
       this.mapaZonas=null;
    }

    @Override
    public Persona deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        System.out.println("DEBUG: Deserializando persona → " + json);
        if (!json.isJsonObject()) {
            throw new JsonParseException("Se esperaba un objeto JSON para una Persona, pero se encontró: " + json);
        }

        JsonObject obj = json.getAsJsonObject();

        if (!obj.has("id")) {
            throw new JsonParseException("Falta el campo 'id' en Persona: " + obj);
        }

        String id = obj.get("id").getAsString();

        boolean tieneNombre = obj.has("nombre");
        boolean tieneAccesos = obj.has("accesos");

        if (!tieneNombre || !tieneAccesos) {
            return null;
        }

        String nombre = obj.get("nombre").getAsString();

        // Usamos un Gson con el AccesoDeserializer para que los accesos se deserialicen bien
        Gson gsonConAccesos = new GsonBuilder()
                .registerTypeAdapter(Acceso.class, new AccesoDeserializer(mapaZonas))
                .create();

        Type tipoListaAccesos = new com.google.gson.reflect.TypeToken<List<Acceso>>(){}.getType();
        List<Acceso> accesos = gsonConAccesos.fromJson(obj.get("accesos"), tipoListaAccesos);

        if (id.startsWith("00")) {
            return context.deserialize(json, Artista.class);
        } else if (id.startsWith("22")) {
            return context.deserialize(json, StaffOrganizador.class);
        } else if (id.startsWith("33")) {
            return context.deserialize(json, Comerciante.class);
        } else if (id.startsWith("11")) {
            return context.deserialize(json, Asistente.class);
        }

        return null;
    }
}

