package Archivo;
import com.google.gson.reflect.TypeToken;
import dominio.persona.Comerciante;
import dominio.persona.Persona;
import dominio.zona.*;
import dominio.zona.Escenario;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ZonaDeserializer implements JsonDeserializer<Zona> {
    private final Map<String, Zona> mapaZonas;

    public ZonaDeserializer(Map<String, Zona> mapaZonas) {
        this.mapaZonas = mapaZonas;
    }
    public ZonaDeserializer() {
        this.mapaZonas = null; // o Collections.emptyMap();
    }
    @Override
    public Zona deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new JsonParseException("ZONA INVALIDA: " + json);
        }

        JsonObject obj = json.getAsJsonObject();

        String codigo = obj.get("codigo").getAsString();
        String descripcion = obj.get("descripcion").getAsString();

        // Deserializar personas
        LinkedList<Persona> personas = new LinkedList<>();
        JsonArray arrayPersonas = obj.getAsJsonArray("personas");
        for (JsonElement pElem : arrayPersonas) {
            Persona persona = context.deserialize(pElem, Persona.class);
            if (persona != null) {
                personas.add(persona);
            }
        }

        Zona zona;

        if (codigo.startsWith("AA")) { // Escenario
            int capMax = obj.get("CapMax").getAsInt();

            Type tipoEventos = new TypeToken<List<Evento>>(){}.getType();
            List<Evento> eventos = context.deserialize(obj.get("eventos"), tipoEventos);

            zona = new Escenario(codigo, descripcion, personas, capMax, eventos);

        } else if (codigo.startsWith("BB")) { // ZonaComun
            zona = new PatioDeComidas(codigo, descripcion, personas);

        } else if (codigo.startsWith("CC")) { // ZonaRestringida
            int capMax = obj.get("CapMax").getAsInt();
            zona = new Backstage(codigo, descripcion, personas, capMax);
        } else if (codigo.startsWith("DD")) { // Stand
            int capMax = obj.get("CapMax").getAsInt();
            Comerciante    responsable = context.deserialize(obj.get("responsable"), Persona.class);
            List<Comerciante> empleados = context.deserialize(obj.get("empleados"), new TypeToken<List<Persona>>() {}.getType());
            zona = new Stand(codigo, descripcion, personas, capMax, null, null,responsable,empleados);
        } else {
            throw new JsonParseException("Código de zona no reconocido: " + codigo);
        }

        return zona;
    }
}

