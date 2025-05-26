package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogFoodTest {
    private DogFood brand1;
    private DogFood brand2;
    private DogFood brand3;
    private DogFood brand4;
    private DogFood brand5;
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;



    @BeforeEach
    public void runBefore() {
        brand1 = new DogFood("a","small",100,"bone growth");
        brand2 = new DogFood("b","big",300,"smooth hair");
        brand3 = new DogFood("c","small",200,"boost immunity");
        brand4 = new DogFood("d","small",300,"lalala");
        brand5 = new DogFood("e","big",300,"plplpl");
        dog1 = new Dog("small",1);
        dog2 = new Dog("big",5);
        dog3 = new Dog("small",10);
    }

    @Test
    public void testCheckDog() {
        assertTrue(brand1.checkDog(dog1));
        assertFalse(brand1.checkDog(dog2));
        assertFalse(brand1.checkDog(dog3));
        assertFalse(brand2.checkDog(dog1));
        assertTrue(brand2.checkDog(dog2));
        assertFalse(brand2.checkDog(dog3));
        assertFalse(brand3.checkDog(dog1));
        assertFalse(brand3.checkDog(dog2));
        assertTrue(brand3.checkDog(dog3));
    }

    @Test
    public void testMarkBrand() {
        assertEquals("young",brand1.markBrand());
        assertEquals("middle-age",brand2.markBrand());
        assertEquals("old",brand3.markBrand());
        assertEquals("Invalid Food!!",brand4.markBrand());
        assertEquals("Invalid Food!!",brand5.markBrand());
    }

    @Test
    public void testToString() {
        assertEquals("a small 100 bone growth",brand1.toString());
        assertEquals("b big 300 smooth hair",brand2.toString());
        assertEquals("c small 200 boost immunity",brand3.toString());
    }

    @Test
    public void testGetName() {
        assertEquals("a",brand1.getName());
        assertEquals("b",brand2.getName());
        assertEquals("c",brand3.getName());
    }

    @Test
    public void testGetParticle() {
        assertEquals("small",brand1.getParticle());
        assertEquals("big",brand2.getParticle());
        assertEquals("small",brand3.getParticle());
    }

    @Test
    public void testGetPrice() {
        assertEquals(100,brand1.getPrice());
        assertEquals(300,brand2.getPrice());
        assertEquals(200,brand3.getPrice());
    }

    @Test
    public void testGetImpact() {
        assertEquals("bone growth",brand1.getImpact());
        assertEquals("smooth hair",brand2.getImpact());
        assertEquals("boost immunity",brand3.getImpact());
    }

    @Test
    public void testSetName() {
        brand1.setName("abc");
        assertEquals("abc",brand1.getName());
    }

    @Test
    public void testSetParticle() {
        brand1.setParticle("big");
        assertEquals("big",brand1.getParticle());
    }

    @Test
    public void testSetPrice() {
        brand1.setPrice(555);
        assertEquals(555,brand1.getPrice());
    }

    @Test
    public void testSetImpact() {
        brand1.setImpact("smooth hair");
        assertEquals("smooth hair",brand1.getImpact());
    }

}
