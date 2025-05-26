package model;

// Represent a dog with size and age.
public class Dog {
    private String size;
    private Integer age;
    private String mark;

    //EFFECTS: construct a dog with the given size and age with no marks at the beginning
    public Dog(String s, int a) {
        this.size = s;
        this.age = a;
        this.mark = null;
    }

    //MODIFIES: this
    //EFFECTS: mark the dog based on its age and return it:
    //         young: 0~2
    //         middle-age: 3~6
    //         old: >6
    public String markDog() {
        if (this.age < 0) {
            return "invalid age";
        } else if (this.age <= 2) {
            return "young";
        } else if (this.age <= 6) {
            return "middle-age";
        } else {
            return "old";
        }
    }

    //EFFECTS: return the size of the dog
    public String getSize() {
        return this.size;
    }

    //EFFECTS: return the age of the dog
    public int getAge() {
        return this.age;
    }

    //MODIFIES: this
    //EFFECTS: set up the dog size
    public void setSize(String size) {
        this.size = size;
    }

    //MODIFIES: this
    //EFFECTS: set up the dog age
    public void setAge(int age) {
        this.age = age;
    }
}
