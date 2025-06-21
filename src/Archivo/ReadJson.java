package Archivo;
import Archivo.Festival;
import com.google.gson.reflect.TypeToken;
import dominio.acceso.Acceso;
import dominio.persona.Persona;
import dominio.zona.Stand;
import dominio.zona.Zona;
import com.google.gson.*;
import dominio.zona.ZonaComun;
/*estoy probando*/
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Map;

public class ReadJson {

    public static void main(String args[]){
        try {
            // PRIMERA ETAPA: leer zonas sin AccesoDeserializer
            Gson gsonInicial = new GsonBuilder()
                    .registerTypeAdapter(Zona.class, new ZonaDeserializer())
                    .registerTypeAdapter(Persona.class, new PersonaDeserializer())
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();

            Reader reader = new FileReader("src/Archivo/archivofestival.json");
            Type zonaListType = new TypeToken<List<Zona>>(){}.getType();
            List<Zona> zonas = gsonInicial.fromJson(reader, zonaListType);
            reader.close();

            // Crear mapa de zonas por código
            Map<String, Zona> mapaZonas = new HashMap<>();
            for (Zona z : zonas) {
                mapaZonas.put(z.getCodigo(), z);
            }

            // SEGUNDA ETAPA: volver a leer JSON pero ahora con AccesoDeserializer
            Gson gsonFinal = new GsonBuilder()
                    .registerTypeAdapter(Zona.class, new ZonaDeserializer(mapaZonas))
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .registerTypeAdapter(Persona.class, new PersonaDeserializer(mapaZonas))
                    .registerTypeAdapter(Acceso.class, new AccesoDeserializer(mapaZonas))
                    .setPrettyPrinting()
                    .create();

            // Releer el JSON con el nuevo GSON (ahora con accesos bien resueltos)
            Reader readerFinal = new FileReader("src/Archivo/archivofestival.json");
            List<Zona> zonasFinal = gsonFinal.fromJson(readerFinal, zonaListType);
            readerFinal.close();

            // Resolver referencias cruzadas (zonacomun en Stand)
            JsonArray zonasArray = JsonParser.parseReader(new FileReader("archivofestival.json")).getAsJsonArray();
            for (JsonElement elem : zonasArray) {
                JsonObject obj = elem.getAsJsonObject();
                String codigo = obj.get("codigo").getAsString();

                Zona zona = mapaZonas.get(codigo);
                if (zona instanceof Stand stand && obj.has("zonacomun")) {
                    String codZonaComun = obj.get("zonacomun").getAsString();
                    Zona referenciada = mapaZonas.get(codZonaComun);
                    if (referenciada instanceof ZonaComun zonaComun) {
                        stand.setZonacomun(zonaComun);
                    }
                }
            }

            // Mostrar datos
            MuestraDatos.mostrarZonas(zonasFinal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }


