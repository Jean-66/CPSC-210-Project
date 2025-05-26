package persistence;

import model.DogFood;
import model.DogFoodList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            DogFoodList dogFoodList = new DogFoodList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDogFoodList() {
        try {
            DogFoodList dogFoodList = new DogFoodList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDogFoodList.json");
            writer.open();
            writer.write(dogFoodList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDogFoodList.json");
            dogFoodList = reader.read();
            assertEquals(10, dogFoodList.sizeDataSet());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDogFoodList() {
        try {
            DogFoodList dogFoodList = new DogFoodList();
            dogFoodList.addFood(new DogFood("food1", "small",1111,"bone growth"));
            dogFoodList.addFood(new DogFood("food2", "big",22,"smooth hair"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDogFoodList.json");
            writer.open();
            writer.write(dogFoodList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDogFoodList.json");
            dogFoodList = reader.read();
            List<DogFood> dataset = dogFoodList.getDataset();
            assertEquals(12, dataset.size());
            checkDogFood("food1", dataset.get(10));
            checkDogFood("food2", dataset.get(11));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
