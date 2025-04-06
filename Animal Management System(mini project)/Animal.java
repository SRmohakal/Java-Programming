abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    // Encapsulation
    public String getName() {
        return name;
    }

    // Abstract method (Abstraction)
    abstract void makeSound();
}
