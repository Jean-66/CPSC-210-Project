package ui;

import model.DogFood;
import model.DogFoodList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Represent the panel to display of adding a new dog food to the dataset
public class AddListener implements ActionListener {
    private JButton button;
    private DogFoodList dogFoodList;
    private DefaultListModel<DogFood> toBeInserted;

    public AddListener(JButton button, DefaultListModel<DogFood> toBeInsertedList, DogFoodList dogFoodList) {
        this.button = button;
        this.toBeInserted = toBeInsertedList;
        this.dogFoodList = dogFoodList;
    }


    // MODIFIES: this
    // EFFECTS: create the dog food and add it to the dataset
    public void actionPerformed(ActionEvent e) {
        JFrame newFrame = new JFrame("Create Dog Food");
        newFrame.setSize(500, 300);
        String name = JOptionPane.showInputDialog("Dog Food's Name: ");
        String particle = JOptionPane.showInputDialog("Dog Food's particle size: (small or big)");
        String price = JOptionPane.showInputDialog("Dog Food's price ($): ");
        String impact = JOptionPane.showInputDialog("\"Dog Food's impact: \n"
                + "1 -> bone growth\n"
                + "2 -> smooth hair\n"
                + "3 -> boost immunity");
        DogFood food = new DogFood(name, particle, Integer.parseInt(price), outputImpact(impact));
        toBeInserted.addElement(food);
        dogFoodList.addFood(food);
    }


    // MODIFIES: this
    // EFFECTS: set up the impact for the dog food
    private String outputImpact(String s) {
        if (s.equals("1")) {
            return "bone growth";
        } else if (s.equals("2")) {
            return "smooth hair";
        } else if (s.equals("3")) {
            return "boost immunity";
        } else {
            System.out.println("Invalid Impact...");
            return "null";
        }
    }



}
