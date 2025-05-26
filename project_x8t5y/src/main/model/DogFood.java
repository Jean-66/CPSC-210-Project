package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a brand of dog food with name, particle size, price, and impact
public class DogFood implements Writable {
    private String name;
    private String particle;
    private Integer price;
    private String impact;


    //EFFECTS: construct a brand of dog food with the given name, particle size,
    //         price, and impact.
    public DogFood(String n, String pa, int pr, String i) {
        this.name = n;
        this.particle = pa;
        this.price = pr;
        this.impact = i;
    }

    //EFFECTS: return the name of this brand
    public String getName() {
        return this.name;
    }

    //EFFECTS: return the particle of this brand
    public String getParticle() {
        return this.particle;
    }

    //EFFECTS: return the price of this brand
    public int getPrice() {
        return this.price;
    }

    //EFFECTS: return the impact of this brand
    public String getImpact() {
        return this.impact;
    }


    //REQUIRES: the impact must be one of: bone growth, smooth hair, boost immunity
    //EFFECTS: mark the given food brand based on its impact:
    //         young dog: bone growth
    //         middle-age dog: smooth hair
    //         young dog: boost immunity
    //         otherwise: Invalid Food!!
    public String markBrand() {
        if ("bone growth".equals(this.impact)) {
            return "young";
        } else if ("smooth hair".equals(this.impact)) {
            return  "middle-age";
        } else if ("boost immunity".equals(this.impact)) {
            return "old";
        } else {
            return "Invalid Food!!";
        }
    }

    //EFFECTS: return true if this brand of dogFood is suitable for the given dog
    //         based on the following criteria:
    //         small dog: small size food
    //         large dog: large size food
    public Boolean checkDog(Dog dog) {
        if ((dog.markDog() == markBrand()) && (dog.getSize().equals((this.particle)))) {
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: set the given string to the dog food's name
    public void setName(String s) {
        this.name = s;
    }

    // MODIFIES: this
    // EFFECTS: set the given string to the dog food's particle
    public void setParticle(String s) {
        this.particle = s;
    }


    // MODIFIES: this
    // EFFECTS: set the given integer to the dog food's price
    public void setPrice(int s) {
        this.price = s;
    }


    // MODIFIES: this
    // EFFECTS: set the given string to the dog food's impact
    public void setImpact(String s) {
        this.impact = s;
    }

    public String toString() {
        return name + " " + particle + " " + price + " " + impact;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("particle",particle);
        json.put("price",price);
        json.put("impact",impact);
        return json;
    }

}
