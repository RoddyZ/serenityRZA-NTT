package starter.DataManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonDataProvider {

    private static final String JSON_FILE_PATH = "src/test/resources/datos.json";

    public static Map<String, String> getData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(JSON_FILE_PATH);
        return objectMapper.readValue(file, Map.class);
    }
}