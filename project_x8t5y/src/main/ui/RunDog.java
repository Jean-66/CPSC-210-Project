package ui;

import model.Dog;
import model.DogFood;
import model.DogFoodList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.IOException;

// Run the program with the user's input
public class RunDog {
    private static final String JSON_STORE = "./data/dogfoodlist.json";
    private Dog dog;
    private DogFoodList dogFoodList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the caller application
    public RunDog() throws FileNotFoundException {
        dog = new Dog(null,0);
        dogFoodList = new DogFoodList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        callDog();
    }


    // MODIFIES: this
    // EFFECTS: processes user input.
    private void callDog() {
        Boolean keepGoing = true;

        while (keepGoing) {
            displayMenu();
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            command = command.toUpperCase();

            if (command.equals("F")) {
                System.out.println("Thank you!");
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodBye!");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equalsIgnoreCase("A")) {
            customize();
        } else if (command.equalsIgnoreCase("B")) {
            adding();
            System.out.println("Adding successful.");
        } else if (command.equalsIgnoreCase("C")) {
            printDataSet();
        } else if (command.equalsIgnoreCase("D")) {
            saveDataset();
        } else if (command.equalsIgnoreCase("E")) {
            viewDataset();
        } else {
            System.out.println("Invalid input...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes dog
    private void initdog() {
        displayQuestion1();
        Scanner input1 = new Scanner(System.in);
        String inputSize = input1.nextLine();
        setDogSize(inputSize);

        try {
            displayQuestion2();
            Scanner input2 = new Scanner(System.in);
            int inputAge = input2.nextInt();
            dog.setAge(inputAge);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Age...");
            System.exit(0);
        }
    }

    // EFFECTS: displays the menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\nA -> Dog Food Customization");
        System.out.println("\nB -> Dog Food Adding");
        System.out.println("\nC -> View Dog Food Dataset");
        System.out.println("\nD -> Save Dataset to File");
        System.out.println("\nE -> load Dataset to File");
        System.out.println("\nF -> Quit");
    }


    // EFFECTS: displays the question ask for the dog size
    private void displayQuestion1() {
        System.out.println("\nWhat is the dog's size?");
        System.out.println("\ns -> small");
        System.out.println("\nb -> big");
    }

    // EFFECTS: displays the question ask for the dog age
    private void displayQuestion2() {
        System.out.println("\nEnter dog's age: ");
    }

    // MODIFIES: this
    // EFFECTS: set up the dog's size based on the user's input
    private void setDogSize(String size) {
        if (size.equalsIgnoreCase("s")) {
            dog.setSize("small");
        } else if (size.equalsIgnoreCase("b")) {
            dog.setSize("big");
        } else {
            System.out.println("Invalid size...");
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: set up the particle size for the dog food
    private String outputParticle(String s) {
        if (s.equalsIgnoreCase("s")) {
            return "small";
        } else if (s.equalsIgnoreCase("b")) {
            return "big";
        } else {
            System.out.println("Invalid Particle Size...");
            return "null";
        }
    }

    // MODIFIES: this
    // EFFECTS: set up the price for the dog food
    private int outputPrice() {
        try {
            Scanner price = new Scanner(System.in);
            int inputPrice = price.nextInt();
            return inputPrice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid Price!");
            return 0;
        }
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

    // MODIFIES: this
    // EFFECTS: tailors a list of dog food for the given dog
    private void customize() {
        initdog();
        DogFoodList suitableList = new DogFoodList();
        LinkedList<DogFood> suitable = suitableList.addSuitable(dog);
        System.out.println("There are " + suitable.size() + " brands of dog food suitable for your dog.");
        System.out.println("Here is the list of dog food suitable for your dog:\n");
        System.out.println(suitable);
    }

    // MODIFIES: this
    // EFFECTS: adding a dog food to the dataset
    private void adding() {
        System.out.println("What is the brand's name?\n");
        Scanner name = new Scanner(System.in);
        String inputName = name.nextLine();

        System.out.println("What is the dog food's particle?\n");
        System.out.println("s -> small\n");
        System.out.println("b -> big\n");
        Scanner particle = new Scanner(System.in);
        String inputParticle = particle.nextLine();

        System.out.println("How much is this dog food? $\n");
        int inputPrice = outputPrice();

        System.out.println("What is the impact of this dog food?\n");
        System.out.println("1 -> bone growth\n");
        System.out.println("2 -> smooth hair\n");
        System.out.println("3 -> boost immunity\n");
        Scanner impact = new Scanner(System.in);
        String inputImpact = impact.nextLine();

        DogFood dogFood = new DogFood(inputName,outputParticle(inputParticle), inputPrice,outputImpact(inputImpact));

        dogFoodList.addFood(dogFood);
    }

    // MODIFIES: this
    // EFFECTS: displays the dataset
    private void viewDataset() {
        try {
            dogFoodList = jsonReader.read();
            System.out.println("There are " + dogFoodList.getDataset().size() + " dog food brands in the dataset:\n");
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the dog foods in the dataset
    private void printDataSet() {

        System.out.println(dogFoodList.getDataset());
    }

    // EFFECTS: saves the dataset to file
    private void saveDataset() {
        try {
            jsonWriter.open();
            jsonWriter.write(dogFoodList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
