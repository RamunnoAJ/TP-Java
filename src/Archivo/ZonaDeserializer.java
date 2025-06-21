package Archivo;
import Zona.Zona;
import Zona.Escenario;
import Zona.ZonaComun;
import Zona.ZonaRestringida;
import Zona.Stand;
import com.google.gson.*;
import java.lang.reflect.Type;

public class ZonaDeserializer implements JsonDeserializer<Zona> {

    @Override
    public Zona deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        if (obj.has("eventos")) {
            return context.deserialize(json, Escenario.class);
        } else if (obj.has("comerciante")) {
            // Si tiene comerciante es un Stand
            return context.deserialize(json, Stand.class);
        } else if (obj.has("CapMax")) {
            // Tiene capmax pero no comerciante  es ZonaRestringida común
            return context.deserialize(json, ZonaRestringida.class);
        } else {
            return context.deserialize(json, ZonaComun.class);
        }
    }
    }

