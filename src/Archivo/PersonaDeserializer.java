package Archivo;
import Persona.Persona;
import Persona.Artista;
import Persona.Asistente;
import Persona.Comerciante;
import Persona.StaffOrganizador;
import com.google.gson.*;
import java.lang.reflect.Type;

public class PersonaDeserializer implements JsonDeserializer<Persona> {

    @Override
    public Persona deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String id = obj.get("id").getAsString();

        if (obj.has("zonaspermitidas")) {
            if (id.startsWith("00")) {
                return context.deserialize(json, Artista.class);
            } else if (id.startsWith("22")) {
                return context.deserialize(json, StaffOrganizador.class);
            } else {
                return context.deserialize(json, Comerciante.class);
            }
        } else {
            return context.deserialize(json, Asistente.class);
        }
    }
}

