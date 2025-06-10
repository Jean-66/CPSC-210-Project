package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

// construct the dog with the given input and tailor a list of dog foods suitable for this dog.
public class TailorListener implements ActionListener {
    protected DefaultListModel listModel;
    private JButton button;
    private DogFoodList suitable;

    public TailorListener(JButton button, DogFoodList dogFoodList, DefaultListModel<DogFood> listModel) {
        this.button = button;
        this.suitable = dogFoodList;
        this.listModel = listModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame newFrame = new JFrame("Tailored Dog Food");
        newFrame.setSize(500, 300);
        String size = JOptionPane.showInputDialog("What is the dog's size? (small or big) ");
        String age = JOptionPane.showInputDialog("Enter dog's age: ");
        Dog dog = new Dog(size,Integer.parseInt(age));
        LinkedList<DogFood> suitableList = suitable.addSuitable(dog);

        listModel.clear();
        for (DogFood dogFood : suitableList) {
            listModel.addElement(dogFood);
        }




    }
}
