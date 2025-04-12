**Question:**

You are given the following files:

- `MainApp.java`
- `Fish.java`
- `Action.java`

These files **must not be modified**.  
Your task is to write a class named **`Animal`** in the file `Animal.java`, such that when you run the `MainApp` class, the output is exactly:

```
Eating Tuna...
Sleeping for 120 min...
70000.0
Swimming
```

### File Details (Do Not Change):

---

#### `MainApp.java`

```java
package org;
import org.animals.Fish;
import org.typs.Action;

public class MainApp {

    public static void main(String[] args) {
        Fish shark = new Fish(350.0, "Swimming");
        shark.eat("Tuna");
        shark.sleep(120);
        shark.printPrice(200);
        Action action = shark;
        action.move();
    }
}
```

---

#### `Fish.java`

```java
package org.animals;
import org.typs.Animal;

public class Fish extends Animal {

    public Fish(double weight, String movementType) {
        super(weight, movementType);
    }

    public void printPrice(double pricePerkg) {
        System.out.println(getWeight() * pricePerkg);
    }
}
```

---

#### `Action.java`

```java
package org.typs;

public interface Action {
    public void move();
}
```

---

## ✅ Solution – `Animal.java`

Here is the correct code for `Animal.java`:

```java
// Animal.java
package org.typs;

public abstract class Animal implements Action {
    private double weight;
    private String movementType;

    public Animal(double weight, String movementType) {
        this.weight = weight;
        this.movementType = movementType;
    }

    public double getWeight() {
        return weight;
    }

    public void eat(String food) {
        System.out.println("Eating " + food + "...");
    }

    public void sleep(int minutes) {
        System.out.println("Sleeping for " + minutes + " min...");
    }

    @Override
    public void move() {
        System.out.println(movementType);
    }
}
```

---

## ✅ Folder & Package Setup

Ensure your folder structure reflects the package structure exactly:

```
project_root/
├── org/
│   ├── MainApp.java
│   └── animals/
│       └── Fish.java
└── org/
    └── typs/
        ├── Action.java
        └── Animal.java
```

---

## ✅ Compile & Run

From your `project_root` directory:

```bash
javac org/typs/*.java org/animals/*.java org/MainApp.java
java org.MainApp
```

---
