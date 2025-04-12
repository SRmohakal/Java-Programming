### 📄 Question

You are given the following three files — `Shape.java`, `Draw.java`, and `MainApp.java`. You are **not allowed to change any of these files**.

Your task is to write a **Java class named `Circle`** in a file named `Circle.java` so that the output of the `MainApp` class matches the expected result below.

---

### ✅ Expected Output

```
Main Application Started
Circle Color = Red
Circle Area = 7853.999999999999
Circle with Green color, 33.0 radius and 3421.2023999999997 area drawn in the Canvas.
Main Application Ended
```

---

### 📂 Provided Files

#### ✅ `Shape.java`

```java
package App.Abstracts;

public abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double area();

    public final String getColor() {
        return this.color;
    }
}
```

#### ✅ `Draw.java`

```java
package App.Abstracts;

public interface Draw {
    void drawShape();
}
```

#### ✅ `MainApp.java`

```java
package App;

import App.Abstracts.Draw;
import App.Abstracts.Shape;
import App.Abstracts.Circle;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Main Application Started");

        Shape s = new Circle("Red", 50.0);
        System.out.println("Circle Color = " + s.getColor());
        System.out.println("Circle Area = " + s.area());

        Draw d = new Circle("Green", 33.0);
        d.drawShape();

        System.out.println("Main Application Ended");
    }
}
```

---

### 🧠 Your Task

Create the `Circle.java` file inside the `App.Abstracts` package. This class must:

- Extend `Shape`
- Implement `Draw`
- Have a constructor that takes `color` and `radius`
- Implement `area()` using the formula `π * radius * radius`
- Implement `drawShape()` to match the expected output

---

### ✅ Solution: `Circle.java`

```java
package App.Abstracts;

public class Circle extends Shape implements Draw {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void drawShape() {
        System.out.println("Circle with " + getColor() + " color, " + radius + " radius and " + area() + " area drawn in the Canvas.");
    }
}
```

---

### 📌 Notes:
- We use `Math.PI` for precision.
- `getColor()` is called from the `Shape` base class.
- Everything is exactly case-sensitive, as required.

---
### Build and Run process
---

### ✅ 1. Correct Folder Structure

Your file structure must match the package declarations.

Create the following folder structure:

```
App/
├── MainApp.java
└── Abstracts/
    ├── Shape.java
    ├── Draw.java
    └── Circle.java
```

### ✅ 3. Compile All Files

Open **PowerShell** or **CMD**, go to the directory containing the `App` folder:

```bash
cd "C:\Users\akash\Downloads\Semester-3 class pdf\java\Question solution"
```

Then run:

```bash
javac App\Abstracts\*.java App\MainApp.java
```

This will compile all the files.

---

### ✅ 4. Run the Application

After successful compilation, run it using:

```bash
java App.MainApp
```

Make sure you're still in the folder **above** `App` when you run this command.

---
