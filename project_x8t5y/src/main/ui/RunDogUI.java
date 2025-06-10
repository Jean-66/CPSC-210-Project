package ui;

import model.ConsolePrinter;
import model.DogFood;
import model.DogFoodList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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

    private static JFrame mainFrame;

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
//        JLabel label = new JLabel();
//        label.setIcon(new ImageIcon(RunDogUI.class.getResource("IMG_1325.png")));

        ImageIcon pawIcon = new ImageIcon(RunDogUI.class.getResource("IMG_1010.JPG"));

//
        loadButton = new JButton(loadString);
//
        setBackground(loadButton, pawIcon);


        ActionListener loadListener = new LoadListener(loadButton,dogFoodList,listModel);
        loadButton.addActionListener(loadListener);
        loadButton.setEnabled(true);


    }

    // MODIFIES: this
    // EFFECTS: create and show the save button
    private void saveButton() {
        ImageIcon pawIcon = new ImageIcon(RunDogUI.class.getResource("IMG_1011.JPG"));

        saveButton = new JButton(saveString);
        setBackground(saveButton, pawIcon);

        ActionListener saveListener = new SaveListener(saveButton,dogFoodList);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);


    }

    // MODIFIES: this
    // EFFECTS: create and show the add button
    private void addButton() {
        ImageIcon pawIcon = new ImageIcon(RunDogUI.class.getResource("IMG_1012.JPG"));

        addButton = new JButton(addString);

        setBackground(addButton, pawIcon);


        AddListener addListener = new AddListener(addButton,listModel,dogFoodList);
        addButton.addActionListener(addListener);
        addButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: create and show the tailor button
    private void tailorButton() {
        ImageIcon pawIcon = new ImageIcon(RunDogUI.class.getResource("IMG_1013.JPG"));
//        Image image = pawIcon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
//        pawIcon = new ImageIcon(image);

        tailorButton = new JButton(tailorString, pawIcon);

        setBackground(tailorButton, pawIcon);
        TailorListener tailorListener = new TailorListener(tailorButton,dogFoodList,listModel);
        tailorButton.addActionListener(tailorListener);
        tailorButton.setEnabled(true);
    }

    private void setBackground(JButton bt, ImageIcon icon) {
        ImageIcon pawIcon = new ImageIcon(RunDogUI.class.getResource("IMG_1325.png"));
        Image image = pawIcon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        pawIcon = new ImageIcon(image);

        Image image2 = icon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image2);

        bt.setPreferredSize(new Dimension(55, 55));
//
        bt.setIcon(pawIcon);
        bt.setHorizontalTextPosition(JButton.CENTER);
        bt.setVerticalTextPosition(JButton.CENTER);
        bt.setFont(new Font("Arial", Font.BOLD, 14));
        bt.setForeground(Color.WHITE);

        bt.setBorderPainted(false);
        bt.setContentAreaFilled(false);
        bt.setFocusPainted(false);
        bt.setOpaque(true);

        // Add hover effect
        ImageIcon finalPawIcon = pawIcon;
        ImageIcon enterIcon = icon;
        String originalText = bt.getText();
        bt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt.setIcon(enterIcon);
                bt.setText("");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt.setIcon(finalPawIcon);
                bt.setText(originalText);
            }
        });


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
        mainFrame = new JFrame("Fill the Bellies");
//        JFrame frame2 = new JFrame("Image");
//        frame2.setSize(6, 6);
//        JLabel label = new JLabel();
//        label.setIcon(new ImageIcon(RunDogUI.class.getResource("may.jpg")));
//        frame2.add(label);

        JPanel welcomePanel = createWelcomePanel();
//        JFrame frame2 = new JFrame("Image");
//        frame2.add(welcomePanel);
//        welcomePanel.setSize(400, 700);


        //print the event log
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConsolePrinter.printLog();
        }));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane
        JComponent newContentPane = new RunDogUI();
        newContentPane.setOpaque(true);
        mainFrame.setContentPane(newContentPane);



        //Display the window.
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(welcomePanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null); // Center on screen
        mainFrame.setVisible(true);



        Runtime.getRuntime().addShutdownHook(new Thread(ConsolePrinter::printLog));
//        frame2.pack();
//        frame2.setVisible(true);
    }

    private static JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw background image
                ImageIcon bgIcon = new ImageIcon(RunDogUI.class.getResource("may.jpg"));
                if (bgIcon.getImage() != null) {
                    g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
//        welcomePanel.setPreferredSize(new Dimension(1000, 700));

        // Create start button
        JButton startButton = new JButton("Click to Start");
        styleStartButton(startButton);

        // Add action listener to show main application
        startButton.addActionListener(e -> showMainApplication());

        // Add button to panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Make transparent
        buttonPanel.add(startButton);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set preferred size for welcome panel
        welcomePanel.setPreferredSize(new Dimension(800, 600));
        return welcomePanel;
        }

    private static void styleStartButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237)); // Lighter blue
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Original blue
            }
        });
    }

    private static void showMainApplication() {
        // Remove welcome panel and show main application
        JComponent newContentPane = new RunDogUI();
        newContentPane.setOpaque(true);
        mainFrame.setContentPane(newContentPane);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null); // Re-center
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


