package persistence;

import model.DogFood;
import model.DogFoodList;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DogFoodList dogFoodList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyDogFoodList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyDogFoodList.json");
        try {
            DogFoodList dogFoodList = reader.read();
            assertEquals(10, dogFoodList.sizeDataSet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDogFoodList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralDogFoodList.json");
        try {
            DogFoodList dogFoodList = reader.read();
            List<DogFood> dataset = dogFoodList.getDataset();
            assertEquals(12, dataset.size());
            checkDogFood("food1", dataset.get(10));
            checkDogFood("food2", dataset.get(11));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
