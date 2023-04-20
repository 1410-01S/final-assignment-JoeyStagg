import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static Random generator;

    static String[] names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia",
            "Rodriguez", "Wilson", "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez", "Moore", "Martin",
            "Jackson", "Thompson", "White", "Lopez", "Lee", "Gonzalez", "Harris", "Clark", "Lewis", "Robinson",
            "Walker", "Perez", "Hall", "Young", "Allen", "Sanchez", "Wright", "King", "Scott", "Green", "Baker",
            "Adams", "Nelson", "Hill", "Ramirez", "Campbell", "Mitchell", "Roberts", "Carter", };

    static String[] diets = { "Herbavore", "Carnavore" };

    static String[] foodTypes = { "Plant", "Bird" };

    static String[] genders = { "Male", "Female" };

    static String[] plantNames = { "Berries", "Bushes" };

    static String[] birdNames = { "Robin", "Blue Jay" };

    static <T> T pickRandom(T[] array) {
        int rnd = generator.nextInt(array.length);
        return array[rnd];
    }

    static <T> T pickRandom(ArrayList<T> array) {
        int rnd = generator.nextInt(array.size());
        return array.get(rnd);
    }

    static class World {
        ArrayList<Carnivore> carnivores;
        ArrayList<Herbavore> herbavores;
        ArrayList<Bird> birds;
        ArrayList<Plant> plants;
        int numOfHerbavores = 0;
        int numOfCarnivores = 0;
        int numOfPlants = 0;
        int numOfBirds = 0;

        public World() {
            carnivores = new ArrayList<Carnivore>();
            herbavores = new ArrayList<Herbavore>();
            plants = new ArrayList<Plant>();
            birds = new ArrayList<Bird>();
        }

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

    static class Creature {
        String name = pickRandom(names);
        String diet = "diet";
        String gender = pickRandom(genders);

        public boolean eat() {
            int chance = generator.nextInt(2);
            if (chance == 1) {
                System.out.println(this.name + " ate food.");
                return true;
            }
            return false;
        }

        public boolean die() {
            int chance = generator.nextInt(5);
            if (this.diet == "Carnivore") {
                if (chance == 1 || chance == 2) {
                    System.out.println(this.name + " died.");
                    return true;
                }
            }
            if (chance == 1) {
                System.out.println(this.name + " died.");
                return true;
            }
            return false;
        }

    }

    static class Carnivore extends Creature {

        public Carnivore() {
            diet = "Carnivore";
        }

        public Carnivore cReproduce() {
            System.out.println(this.name + " reproduced");
            Carnivore temp = new Carnivore();
            return temp;

        }

    }

    static class Herbavore extends Creature {
        public Herbavore() {
            diet = "Herbavore";
        }

        public Herbavore hReproduce() {
            System.out.println(this.name + " reproduced");
            Herbavore temp = new Herbavore();
            return temp;
        }

    }

    static class Food {
        String foodType = "foodType";
        String name = "name";

    }

    static class Plant extends Food {
        public Plant() {
            name = pickRandom(plantNames);
            foodType = "Plant";
        }
    }

    static class Bird extends Food {
        public Bird() {
            name = pickRandom(birdNames);
            foodType = "Bird";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        generator = new Random();

        World world = new World();

        System.out.println("Continue? y/n");

        // try {

        boolean keepGoing = true;
        while (keepGoing) {

            System.out.println("Carnivores: " + world.numOfCarnivores);
            System.out.println("Herbavores: " + world.numOfHerbavores);
            System.out.println("Plants: " + world.numOfPlants);
            System.out.println("Birds: " + world.numOfBirds);
            System.out.println();
            String userInput = in.next();
            switch (userInput) {
                case "y":
                    world.createCreature();
                    world.spawnFood();
                    for (int i = 0; i < world.herbavores.size(); i++) {
                        if (world.numOfPlants > 0) {
                            if (world.herbavores.get(i).eat() == true) {
                                world.plants.remove(0);
                                world.numOfPlants--;
                            }
                        }
                        int choose = generator.nextInt(5);
                        if (world.herbavores.size() > 1) {
                            if (choose == 1) {
                                Herbavore newCreature = world.herbavores.get(i).hReproduce();
                                world.herbavores.add(newCreature);
                                world.numOfHerbavores++;
                            }
                        }
                        if (world.herbavores.get(i).die() == true) {
                            world.herbavores.remove(world.herbavores.get(i));
                            world.numOfHerbavores--;
                        }
                    }
                    for (int i = 0; i < world.carnivores.size(); i++) {
                        if (world.numOfBirds > 0) {
                            int choose = generator.nextInt(3);
                            if (choose == 0) {
                                if (world.carnivores.get(i).eat() == true) {
                                    world.birds.remove(0);
                                    world.numOfBirds--;
                                }
                            }
                            if (world.herbavores.size() > 9 && world.carnivores.size() < world.herbavores.size()) {
                                if (choose == 1 || choose == 2 || choose == 3) {
                                    if (world.carnivores.get(i).eat() == true) {
                                        if (world.herbavores.size() > 0) {
                                            world.herbavores.remove(0);
                                            world.numOfHerbavores--;
                                        }
                                    }
                                }
                            }
                        }
                        int choose = generator.nextInt(4);
                        if (world.carnivores.size() > 1) {
                            if (choose == 1) {
                                Carnivore newCreature = world.carnivores.get(i).cReproduce();
                                world.carnivores.add(newCreature);
                                world.numOfCarnivores++;
                            }
                        }
                        if (world.carnivores.get(i).die() == true) {
                            world.carnivores.remove(world.carnivores.get(i));
                            world.numOfCarnivores--;
                        }
                    }
                    break;
                case "n":
                    keepGoing = false;
                    break;

            }
            System.out.println();

        }

        for (Carnivore n : world.carnivores) {
            System.out.println(n.name + " " + n.diet + " " + n.gender);

        }
        for (Herbavore n : world.herbavores) {
            System.out.println(n.name + " " + n.diet + " " + n.gender);

        }
        for (Plant n : world.plants) {
            System.out.println(n.foodType + " " + n.name);
        }
        for (Bird n : world.birds) {
            System.out.println(n.foodType + " " + n.name);
        }
        in.close();
    }
}
