package ui;

import model.ConsolePrinter;
import model.DogFood;
import model.DogFoodList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;


// Run the program with the user's input and output the image
public class RunDogUI extends JPanel implements ListSelectionListener {
    protected JList list;
    protected DefaultListModel listModel;

    private static final String tailorString = "Tailor";
    private static final String addString = "Add";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private static final String clearString = "clear";
    private JButton tailorButton;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private DogFoodList dogFoodList;

    public RunDogUI() {
        super(new BorderLayout());
        dogFoodList = new DogFoodList();
        listModel = new DefaultListModel();

        for (DogFood dogFood : dogFoodList.getDataset()) {
            if (!listModel.contains(dogFood)) {
                listModel.addElement(dogFood);
            }
        }

        createList();
        JScrollPane listScrollPane = new JScrollPane(list);

        tailorButton();

        addButton();

        saveButton();

        loadButton();



        createPane(listScrollPane, addButton);
    }


    // MODIFIES: this
    // EFFECTS: create and show the load button
    private void loadButton() {
        loadButton = new JButton(loadString);
        ActionListener loadListener = new LoadListener(loadButton,dogFoodList,listModel);
        loadButton.addActionListener(loadListener);
        loadButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: create and show the save button
    private void saveButton() {
        saveButton = new JButton(saveString);
        ActionListener saveListener = new SaveListener(saveButton,dogFoodList);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: create and show the add button
    private void addButton() {
        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton,listModel,dogFoodList);
        addButton.addActionListener(addListener);
        addButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: create and show the tailor button
    private void tailorButton() {
        tailorButton = new JButton(tailorString);
        TailorListener tailorListener = new TailorListener(tailorButton,dogFoodList,listModel);
        tailorButton.addActionListener(tailorListener);
        tailorButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: Create the list and put it in a scroll pane
    private void createList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
    }

    // EFFECTS: Create a panel that uses BoxLayout.
    private void createPane(JScrollPane listScrollPane, JButton addButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(tailorButton);
        buttonPane.add(addButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                tailorButton.setEnabled(false);
                addButton.setEnabled(false);
                saveButton.setEnabled(false);
                loadButton.setEnabled(false);
            } else {
                tailorButton.setEnabled(true);
                addButton.setEnabled(true);
                saveButton.setEnabled(true);
                loadButton.setEnabled(true);
            }
        }
    }

    // EFFECTS: create and show the GUI
    // reference: https://medium.com/@michael71314/java-lesson-22-inserting-images-onto-the-jframe-a0a0b6540cca#:~:text=The%20frame.,the%20image%20on%20the%20JFrame.
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Fill the Bellies");
        JFrame frame2 = new JFrame("Image");
        frame2.setSize(6, 6);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(RunDogUI.class.getResource("may.jpg")));
        frame2.add(label);


        //print the event log
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConsolePrinter.printLog();
        }));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane
        JComponent newContentPane = new RunDogUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame2.pack();
        frame2.setVisible(true);
    }




    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


