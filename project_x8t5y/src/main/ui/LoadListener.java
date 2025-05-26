package ui;

import model.DogFood;
import model.DogFoodList;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

// represent the panel to display the loading of the saving data from the memory spot
public class LoadListener implements ActionListener {

    private static final String JSON_STORE = "./data/dogfoodlist.json";
    private JsonReader jsonReader;
    private JButton button;
    private DogFoodList dogFoodList;
    private DefaultListModel<DogFood> listModel;

    // EFFECTS: saves the dataset to file
    public LoadListener(JButton button, DogFoodList toBeLoaded, DefaultListModel<DogFood> listModel) {
        this.button = button;
        this.dogFoodList = toBeLoaded;
        this.listModel = listModel;
        jsonReader = new JsonReader(JSON_STORE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            LinkedList<DogFood> dogFoods = jsonReader.read().getDataset();
            dogFoodList.setDataset(dogFoods);
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException ex) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        listModel.clear();
        for (DogFood dogFood : dogFoodList.getDataset()) {
            if (!listModel.contains(dogFood)) {
                listModel.addElement(dogFood);
            }
        }
    }

}
