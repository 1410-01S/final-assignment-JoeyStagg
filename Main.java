import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Locale;

// main method that everything runs in
public class Main {

    // creates a random object
    static Random generator;

    // array of names to assign to creatures **might change to take directly from
    // file**
    static ArrayList<String> names = new ArrayList<>();

    // array to randomly assign a diet
    static String[] diets = { "Herbavore", "Carnavore" };

    // array to randomly assign a food type
    static String[] foodTypes = { "Plant", "Bird" };

    // array to randomly assign a gender
    static String[] genders = { "Male", "Female" };

    // array to randomly assign a plant name
    static String[] plantNames = { "Berries", "Bushes" };

    // array to randomly assign a bird name
    static String[] birdNames = { "Robin", "Blue Jay" };

    // grabs a random item from an array
    static <T> T pickRandom(T[] array) {
        int rnd = generator.nextInt(array.length);
        return array[rnd];
    }

    // grabs a random item from an array list
    static <T> T pickRandom(ArrayList<T> array) {
        int rnd = generator.nextInt(array.size());
        return array.get(rnd);
    }

    // class that creates the world
    static class World {
        ArrayList<Carnivore> carnivores;
        ArrayList<Herbavore> herbavores;
        ArrayList<Bird> birds;
        ArrayList<Plant> plants;
        int numOfHerbavores = 0;
        int numOfCarnivores = 0;
        int numOfPlants = 0;
        int numOfBirds = 0;

        // constructor to create a world
        public World() {
            carnivores = new ArrayList<Carnivore>();
            herbavores = new ArrayList<Herbavore>();
            plants = new ArrayList<Plant>();
            birds = new ArrayList<Bird>();
        }

        // method to create creatures based on random chances
        public void createCreature() {
            int chance = generator.nextInt(5);
            // 20% chance carnivore spawns
            if (chance == 1 || chance == 2) {
                System.out.println("Carnivore Spawned");
                carnivores.add(new Carnivore());
                numOfCarnivores++;
            }
            // 20% chance herbavore spawns
            if (chance == 1) {
                System.out.println("Herbavore Spawned");
                herbavores.add(new Herbavore());
                numOfHerbavores++;
            }

        }

        // method to create food based on random chances
        public void spawnFood() {
            int chance = generator.nextInt(5);
            // 60% chance plant spawns
            if (chance == 1 || chance == 2 || chance == 3) {
                System.out.println("Plant Spawned");
                plants.add(new Plant());
                numOfPlants++;
            }
            // 40% chance bird spawns
            if (chance == 2 || chance == 1) {
                System.out.println("Bird Spawned");
                birds.add(new Bird());
                numOfBirds++;
            }
        }

    }

    // class that creates creatures
    static class Creature {
        String name = pickRandom(names);
        String diet = "diet";
        String gender = pickRandom(genders);

        // method that randomly decides if an animal eats
        public boolean eat() {
            int chance = generator.nextInt(2);
            // 50% chance to eat
            if (chance == 1) {
                System.out.println(this.name + " ate food.");
                return true;
            }
            return false;
        }

        // method to randomly decide if a creature dies
        public boolean die() {
            int chance = generator.nextInt(4);
            // 25% chance carnivore dies **carnivores can eat herbavores so they have a
            // higher chance to die randomly**
            if (chance == 1) {
                System.out.println(this.name + " died.");
                return true;
            }
            return false;
        }

    }

    // class creates carnivores
    static class Carnivore extends Creature {

        // constructor to make a carnivore
        public Carnivore() {
            diet = "Carnivore";
        }

        // method allows carnivores to reproduce
        public Carnivore cReproduce() {
            System.out.println(this.name + " reproduced");
            Carnivore temp = new Carnivore();
            return temp;

        }

    }

    // class creates herbavores
    static class Herbavore extends Creature {

        // constructor for herbavores
        public Herbavore() {
            diet = "Herbavore";
        }

        // method allows herbavores to reproduce
        public Herbavore hReproduce() {
            System.out.println(this.name + " reproduced");
            Herbavore temp = new Herbavore();
            return temp;
        }

    }

    // class to create food
    static class Food {
        String foodType = "foodType";
        String name = "name";

    }

    // class to create plants
    static class Plant extends Food {

        // constructor for plants
        public Plant() {
            name = pickRandom(plantNames);
            foodType = "Plant";
        }
    }

    // class to create birds
    static class Bird extends Food {

        // constructor to create birds
        public Bird() {
            name = pickRandom(birdNames);
            foodType = "Bird";
        }
    }

    // Main method
    public static void main(String[] args) throws FileNotFoundException {
        // scanner to allow user input
        Scanner in = new Scanner(System.in);

        // create random object within main
        generator = new Random();

        Scanner s = null;

        // populates names with names
        try {
            s = new Scanner(new BufferedReader(new FileReader("name.txt")));
            s.useLocale(Locale.US);
            s.useDelimiter("\",\"\\s*");

            while (s.hasNext()) {
                names.add(s.next());
            }
        } finally {
            s.close();
        }

        // create new world object
        World world = new World();

        // instructions on possible inputs
        System.out.println("Continue? y/n");

        // sets up loop
        boolean keepGoing = true;
        while (keepGoing) {

            // display number of carnivores, herbavores, plants, and birds
            System.out.println("Carnivores: " + world.numOfCarnivores);
            System.out.println("Herbavores: " + world.numOfHerbavores);
            System.out.println("Plants: " + world.numOfPlants);
            System.out.println("Birds: " + world.numOfBirds);
            System.out.println();

            // get user input
            String userInput = in.next();

            switch (userInput) {
                // y runs one loop over the world where creatures are made, food is spawned and
                // creatures have a chance to die, eat, reproduce
                case "y":
                    world.createCreature();
                    world.spawnFood();
                    // loops through so every herbavore has a chance to die, eat, reproduce
                    for (int i = 0; i < world.herbavores.size(); i++) {
                        if (world.numOfPlants > 0) { // only allows to eat if food is there
                            if (world.herbavores.get(i).eat() == true) {
                                world.plants.remove(0); // removes a plant
                                world.numOfPlants--; // decreases plant count
                            }
                        }
                        int choose = generator.nextInt(4);
                        if (world.herbavores.size() > 1) { // reproduce chance if there are 2 herbavores
                            if (choose == 1) { // 25% chance to reproduce
                                Herbavore newCreature = world.herbavores.get(i).hReproduce();
                                world.herbavores.add(newCreature); // new creature added
                                world.numOfHerbavores++; // number increased
                            }
                        }
                        // die is at end so the creature doesnt die and then try to eat or something
                        // while looping through
                        if (world.herbavores.get(i).die() == true) {
                            world.herbavores.remove(world.herbavores.get(i)); // removes herbavore from arraylist
                            world.numOfHerbavores--; // number decreased
                        }
                    }
                    // loops through so every carnivore has a chance to die, eat, reproduce
                    for (int i = 0; i < world.carnivores.size(); i++) {
                        if (world.numOfBirds > 0) { // allows to eat if bird
                            int choose = generator.nextInt(3);
                            if (choose == 0) { // 33% chance to eat
                                if (world.carnivores.get(i).eat() == true) {
                                    world.birds.remove(0); // removes a bird
                                    world.numOfBirds--; // number decreased
                                }
                            }

                        }
                        // only allows to eat herbavore if there are 10 and the number of carnivores is
                        // less than the herbavores
                        if (world.herbavores.size() > 9 && world.carnivores.size() < world.herbavores.size()) {
                            if (world.carnivores.get(i).eat() == true) {
                                if (world.herbavores.size() > 0) { // only eat if herbavore exists
                                    world.herbavores.remove(0); // removes a herbavore
                                    world.numOfHerbavores--; // number decreased
                                }
                            }

                        }
                        int choose = generator.nextInt(4);
                        if (world.carnivores.size() > 1) {
                            if (choose == 1) { // 25% chance to reproduce
                                Carnivore newCreature = world.carnivores.get(i).cReproduce();
                                world.carnivores.add(newCreature); // creates new carnivore
                                world.numOfCarnivores++; // number increased
                            }
                        }
                        if (world.carnivores.get(i).die() == true) {
                            world.carnivores.remove(world.carnivores.get(i)); // removes carnivore from world
                            world.numOfCarnivores--; // number decreased
                        }
                    }
                    break;
                case "n":
                    // if n, loop stops
                    keepGoing = false;
                    break;

            }
            System.out.println(); // spacer for output

        }

        // prints any carnivores in the world when loop stops
        for (Carnivore n : world.carnivores) {
            System.out.println(n.name + " " + n.diet + " " + n.gender);

        }
        // prints any herbavores in the world when loop stops
        for (Herbavore n : world.herbavores) {
            System.out.println(n.name + " " + n.diet + " " + n.gender);

        }
        // prints any plants in the world when loop stops
        for (Plant n : world.plants) {
            System.out.println(n.foodType + " " + n.name);
        }
        // prints any birds in the world when loop stops
        for (Bird n : world.birds) {
            System.out.println(n.foodType + " " + n.name);
        }
        in.close();
    }
}
