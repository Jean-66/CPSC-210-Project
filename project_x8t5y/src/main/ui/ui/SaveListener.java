package ui;

import model.DogFoodList;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represent the panel to display of saving the dog food list to the memory spot
public class SaveListener implements ActionListener {
    private static final String JSON_STORE = "./data/dogfoodlist.json";
    private JsonWriter jsonWriter;
    private JButton button;
    private DogFoodList dogFoodList;



    // EFFECTS: saves the dataset to file
    public SaveListener(JButton button, DogFoodList dogFoodList) {
        this.button = button;
        this.dogFoodList = dogFoodList;
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(dogFoodList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
