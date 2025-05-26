package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DogFoodListTest {
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;
    private Dog dog4;
    private Dog dog5;
    private Dog dog6;
    private DogFood dogFood;
    private DogFoodList dogFoodList;

    @BeforeEach
    public void runBefore() {
        dog1 = new Dog("small",1);
        dog2 = new Dog("small",5);
        dog3 = new Dog("small",10);
        dog4 = new Dog("big",1);
        dog5 = new Dog("big",5);
        dog6 = new Dog("big",10);
        dogFood = new DogFood("ddd","small",300,"bone growth");
        dogFoodList = new DogFoodList();
    }

    @Test
    public void testAddSuitable1() {
        dogFoodList.addSuitable(dog1);
        assertEquals(2,dogFoodList.sizeSuitable());
        assertEquals("a",dogFoodList.getIndex(0).getName());
        assertEquals("g",dogFoodList.getIndex(1).getName());
        assertEquals("a small 100 bone growth",dogFoodList.getIndex(0).toString());
        assertEquals("g small 1000 bone growth",dogFoodList.getIndex(1).toString());
    }

    @Test
    public void testAddSuitable2() {
        dogFoodList.addSuitable(dog2);
        assertEquals(1,dogFoodList.sizeSuitable());
        assertEquals("c",dogFoodList.getIndex(0).getName());
        assertEquals("c small 100 smooth hair",dogFoodList.getIndex(0).toString());
    }

    @Test
    public void testAddSuitable3() {
        dogFoodList.addSuitable(dog3);
        assertEquals(2,dogFoodList.sizeSuitable());
        assertEquals("e",dogFoodList.getIndex(0).getName());
        assertEquals("j",dogFoodList.getIndex(1).getName());
        assertEquals("e small 100 boost immunity",dogFoodList.getIndex(0).toString());
        assertEquals("j small 1000 boost immunity",dogFoodList.getIndex(1).toString());

    }

    @Test
    public void testAddSuitable4() {
        dogFoodList.addSuitable(dog4);
        assertEquals(2,dogFoodList.sizeSuitable());
        assertEquals("b",dogFoodList.getIndex(0).getName());
        assertEquals("h",dogFoodList.getIndex(1).getName());
        assertEquals("b big 100 bone growth",dogFoodList.getIndex(0).toString());
        assertEquals("h big 1000 bone growth",dogFoodList.getIndex(1).toString());
    }

    @Test
    public void testAddSuitable5() {
        dogFoodList.addSuitable(dog5);
        assertEquals(2,dogFoodList.sizeSuitable());
        assertEquals("d",dogFoodList.getIndex(0).getName());
        assertEquals("i",dogFoodList.getIndex(1).getName());
        assertEquals("d big 100 smooth hair",dogFoodList.getIndex(0).toString());
        assertEquals("i big 1000 smooth hair",dogFoodList.getIndex(1).toString());
    }

    @Test
    public void testAddSuitable6() {
        dogFoodList.addSuitable(dog6);
        assertEquals(1,dogFoodList.sizeSuitable());
        assertEquals("f",dogFoodList.getIndex(0).getName());
        assertEquals("f big 100 boost immunity",dogFoodList.getIndex(0).toString());
    }

    @Test
    public void testSizeDataSet () {
        assertEquals(10,dogFoodList.sizeDataSet());
    }

    @Test
    public void testAddFood() {
        int int1 = dogFoodList.sizeDataSet();
        dogFoodList.addFood(dogFood);
        int int2 = dogFoodList.sizeDataSet();
        int int3 = int1 + 1;
        assertEquals(int3,int2);
    }
}
