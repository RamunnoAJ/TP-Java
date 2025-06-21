package Archivo;
import Archivo.Festival;
import Persona.Persona;
import Zona.Zona;
import com.google.gson.*;
/*estoy probando*/
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class ReadJson {

    public static void main(String args[]){
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Zona.class, new ZonaDeserializer())
                    .registerTypeAdapter(Persona.class, new PersonaDeserializer())
                    .setPrettyPrinting()
                    .create();

            Reader reader = new FileReader("archivofestival.json");
            Festival festival = gson.fromJson(reader, Festival.class);
            reader.close();

            // Mostrar el contenido del festival
            lectura.main(festival);

        } catch (Exception e) {
            System.err.println("❌ Error al procesar el archivo JSON:");
            e.printStackTrace();
        }
    }
}


