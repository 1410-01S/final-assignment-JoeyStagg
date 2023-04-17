import java.util.Random;
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
        ArrayList<Creature> creatures;
        ArrayList<Food> foods;

        public World() {
            creatures = new ArrayList<Creature>();
            foods = new ArrayList<Food>();
        }

        public void createCreature() {
            for (int i = 0; i < 10; i++) {
                creatures.add(new Creature());
            }
        }

        public void spawnFood() {
            for (int i = 0; i < 10; i++) {
                foods.add(new Food());
            }
        }

    }

    static class Creature {
        String name = "name";
        String diet = "diet";
        String gender = "gender";

        public Creature() {
            name = pickRandom(names);
            diet = pickRandom(diets);
            gender = pickRandom(genders);
        }

        // public reproduce() {

        // }

        // public eat() {

        // }

        // static class Herbavore extends Creature {}

        // public findPlant() {

        // }

        // static class Carnivore extends Creature {

        // }

        // public findHerbavore() {

        // }

    }

    static class Food {
        String foodType = "foodType";
        String name = "name";

        public Food() {
            foodType = pickRandom(foodTypes);
            if (foodType == "Plant") {
                name = pickRandom(plantNames);
            } else {
                name = pickRandom(birdNames);
            }

        }
    }

    // static class Plant extends Food {
    // public Plant() {
    // name = pickRandom(plantNames);
    // }
    // }

    public static void main(String[] args) {

        generator = new Random();

        World world = new World();
        world.createCreature();
        world.spawnFood();

        for (Creature n : world.creatures) {
            System.out.println(n.name + " " + n.diet + " " + n.gender);

        }
        for (Food n : world.foods) {
            System.out.println(n.foodType + " " + n.name);
        }

    }
}
