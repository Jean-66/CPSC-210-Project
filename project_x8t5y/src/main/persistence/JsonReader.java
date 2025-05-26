package persistence;

import model.DogFood;
import model.DogFoodList;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Stream;

// Represents a reader that reads dogFoodList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads dogFoodList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DogFoodList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDogFoodList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses dogFoodList from JSON object and returns it
    private DogFoodList parseDogFoodList(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        DogFoodList dogFoodList = new DogFoodList();
        dogFoodList.setDataset(new LinkedList<>());
        addDogFoods(dogFoodList, jsonObject);
        return dogFoodList;
    }

    // MODIFIES: dogFoodList
    // EFFECTS: parses dogFood from JSON object and adds them to dogFoodList
    private void addDogFoods(DogFoodList dogFoodList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dogFood");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addDogFood(dogFoodList, nextThingy);
        }
    }

    // MODIFIES: dogFoodList
    // EFFECTS: parses dogFood from JSON object and adds it to dogFoodList
    private void addDogFood(DogFoodList dogFoodList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String particle = jsonObject.getString("particle");
        int price = jsonObject.getInt("price");
        String impact = jsonObject.getString("impact");
        DogFood dogFood1 = new DogFood(name, particle,price,impact);
        dogFoodList.addFood(dogFood1);
    }

}
