package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;
    private Dog dog4;
    private Dog dog5;
    private Dog dog6;
    private Dog dog7;
    private Dog dog8;
    private Dog dog9;
    private Dog dog10;
    private Dog dog11;
    private Dog dog12;
    private Dog dog13;

    @BeforeEach
    public void runBefore() {
        dog1 = new Dog("small",1);
        dog2 = new Dog("big",5);
        dog3 = new Dog("small",10);
        dog4 = new Dog("small",7);
        dog5 = new Dog("small",0);
        dog6 = new Dog("big",2);
        dog7 = new Dog("small",6);
        dog8 = new Dog("big",3);
        dog9 = new Dog("small",4);
        dog10 = new Dog("big",8);
        dog11 = new Dog("big",-1);
        dog12 = new Dog("big",11);
        dog13 = new Dog("small",1);

    }

    @Test
    public void testHashCode() {
        assertTrue(dog1.getSize().hashCode() == dog13.getSize().hashCode());
        assertFalse(dog1.getSize().hashCode() == dog2.getSize().hashCode());
    }

    @Test
    public void testMarkDog() {
        assertEquals("young",dog1.markDog());
        assertEquals("middle-age",dog2.markDog());
        assertEquals("old",dog3.markDog());
        assertEquals("old",dog4.markDog());
        assertEquals("young",dog5.markDog());
        assertEquals("young",dog6.markDog());
        assertEquals("middle-age",dog7.markDog());
        assertEquals("middle-age",dog8.markDog());
        assertEquals("middle-age",dog9.markDog());
        assertEquals("old",dog10.markDog());
        assertEquals("invalid age",dog11.markDog());
        assertEquals("old",dog12.markDog());


    }

    @Test
    public void testGetSize() {
        assertEquals("small",dog1.getSize());
        assertEquals("big",dog2.getSize());
        assertEquals("small",dog3.getSize());
        assertEquals("small",dog4.getSize());
    }

    @Test
    public void testGetAge() {
        assertEquals(1,dog1.getAge());
        assertEquals(5,dog2.getAge());
        assertEquals(10,dog3.getAge());
        assertEquals(7,dog4.getAge());
    }

    @Test
    public void testSetSize() {
        dog1.setSize("big");
        assertEquals("big",dog1.getSize());
    }

    @Test
    public void testSetAge() {
        dog1.setAge(11);
        assertEquals(11,dog1.getAge());
    }
}