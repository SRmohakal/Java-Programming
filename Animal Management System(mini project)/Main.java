public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Whiskers");

        Zoo zoo = new Zoo();
        zoo.hearAnimal(dog);
        zoo.hearAnimal(cat);
    }
}
