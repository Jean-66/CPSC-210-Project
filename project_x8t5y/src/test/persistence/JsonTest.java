package persistence;

import model.DogFood;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDogFood(String name, DogFood dogFood) {
        assertEquals(name, dogFood.getName());
    }
}
