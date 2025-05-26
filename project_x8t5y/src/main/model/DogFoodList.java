package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// Represent the list of dog foods. (one is the dataset, another one is the list that suitable for the given dog)
public class DogFoodList implements Writable {

    private LinkedList<DogFood> suitable;
    private DogFood brand1;
    private DogFood brand2;
    private DogFood brand3;
    private DogFood brand4;
    private DogFood brand5;
    private DogFood brand6;
    private DogFood brand7;
    private DogFood brand8;
    private DogFood brand9;
    private DogFood brand10;
    private LinkedList<DogFood> dataset;

    //EFFECTS: Initializes all the dog food brands in the dogFoodList and
    //         initializes each newly created suitable dog food list as an empty queue.
    public DogFoodList() {
        dataset = new LinkedList<>();
        brand1 = new DogFood("a","small",100,"bone growth");
        brand2 = new DogFood("b","big",100,"bone growth");
        brand3 = new DogFood("c","small",100,"smooth hair");
        brand4 = new DogFood("d","big",100,"smooth hair");
        brand5 = new DogFood("e","small",100,"boost immunity");
        brand6 = new DogFood("f","big",100,"boost immunity");
        brand7 = new DogFood("g","small",1000,"bone growth");
        brand8 = new DogFood("h","big",1000,"bone growth");
        brand9 = new DogFood("i","big",1000,"smooth hair");
        brand10 = new DogFood("j","small",1000,"boost immunity");
        dataset.add(brand1);
        dataset.add(brand2);
        dataset.add(brand3);
        dataset.add(brand4);
        dataset.add(brand5);
        dataset.add(brand6);
        dataset.add(brand7);
        dataset.add(brand8);
        dataset.add(brand9);
        dataset.add(brand10);
        suitable = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: add the given DogFood to the end of the dogFoodList if the dogFood is suitable
    public LinkedList<DogFood> addSuitable(Dog dog) {
        for (DogFood dogFood : dataset) {
            if (dogFood.checkDog(dog)) {
                suitable.add(dogFood);
            }
        }
        for (DogFood dogFood : suitable) {
            dogFood.toString();
        }
        EventLog.getInstance().logEvent(new Event("There are "
                +
                suitable.size()
                +
                " brands of dog food suitable for your dog."));
        return suitable;
    }

    //MODIFIES: this
    //EFFECTS: add a new brand to the dataset
    public void addFood(DogFood d) {
        dataset.add(d);
        EventLog.getInstance().logEvent(new Event("Dog food named "
                +
                d.getName() + " has been successfully added!"));
    }

    //EFFECTS: return the dataset
    public LinkedList<DogFood> getDataset() {
        for (DogFood dogFood : dataset) {
            dogFood.toString();
        }
        return dataset;
    }

    //EFFECTS: return the dataset
    public void setDataset(LinkedList<DogFood> list) {
        dataset = list;
    }

    //EFFECTS: return the number of brands suitable for the dog
    public Integer sizeSuitable() {
        return suitable.size();
    }

    //EFFECTS: return the number of all the brands
    public Integer sizeDataSet() {
        return dataset.size();
    }

    //EFFECTS: return the brands at the index
    public DogFood getIndex(int i) {
        return suitable.get(i);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dogFood",thingiesToJson());
        return json;
    }

    // EFFECTS: return things in this dataset as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (DogFood dogFood : dataset) {
            jsonArray.put(dogFood.toJson());
        }

        return jsonArray;
    }


}
