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

    static class World {
        ArrayList<Creature> creatures;
        ArrayList<Food> foods;

        public World() {
            creatures = createCreature();
            foods = spawnFood();
        }

        public ArrayList<Creature> createCreature() {
            ArrayList<Creature> temp = new ArrayList<Creature>(); // Messing around with this
            for (int i = 0; i < 10; i++) {
                temp.add(new Creature());
            }
            return temp;
        }

        public void spawnFood() {

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

        Creature creature = new Creature();
        Food food = new Food();

        World world = new World();

        for (Creature n : world.creatures) {
            System.out.println(creature.name + " " + creature.diet);

        }

        System.out.println(creature.name + " " + creature.diet + " " + creature.gender);
        System.out.println(food.foodType + " " + food.name);

    }
}
