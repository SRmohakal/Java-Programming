## 🔰 What Is an Enumeration?

An `enum` is a **type-safe way** to represent a group of predefined constants.

### 📌 Basic Syntax
```java
enum Apple {
    Jonathan, GoldenDel, RedDel, Winesap, Cortland
}
```

- Each value (`Jonathan`, `GoldenDel`, etc.) is a **constant** of type `Apple`.
- All enum constants are **public static final** and **self-typed** (type is `Apple` here).

---

## 🎯 Using Enum Variables

```java
Apple ap;

ap = Apple.RedDel;

if (ap == Apple.GoldenDel) {
    System.out.println("Golden Delicious is selected.");
}
```

✅ You can use `==` for comparison because enums are singletons (no duplicates created).

---

## 🔄 Enums in `switch` Statements

```java
switch(ap) {
    case Jonathan:
        System.out.println("Jonathan selected.");
        break;
    case Winesap:
        System.out.println("Winesap selected.");
        break;
    default:
        System.out.println("Other apple.");
}
```

✅ Enums work beautifully in switch-case, providing **clear and safe control flow**.

---

## 💡 Bonus: Add Fields and Methods to Enums

Yes — enums can also have **fields, constructors, and methods**!

### 🍎 Apple Enum with Price
```java
enum Apple {
    Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland(8);

    private int price;

    Apple(int p) { price = p; }

    int getPrice() { return price; }
}
```

### Usage:
```java
Apple ap = Apple.Winesap;
System.out.println(ap + " costs " + ap.getPrice());
```

---

## 🧠 Enum Summary

| Feature | Details |
|--------|---------|
| Type-safe | Prevents invalid values |
| Singleton constants | No duplicates |
| Switch support | Yes, clean usage |
| Can have methods/constructors | Advanced enum use |
| Iterable | Use `values()` to loop |

```java
for (Apple a : Apple.values()) {
    System.out.println(a + " costs " + a.getPrice());
}
```

---
**mini Java program** that demonstrates:

- Creating an `enum` with **fields and a constructor**  
- Accessing enum constants and using them in a **switch statement**  
- Iterating over all enum values with `values()`  

---

### 🍏 Java Program: Apple Variety Enum with Price and Description

```java
// EnumDemo.java

enum Apple {
    Jonathan(10, "Tart and juicy"),
    GoldenDel(9, "Sweet and mellow"),
    RedDel(12, "Crisp and sweet"),
    Winesap(15, "Spicy and rich"),
    Cortland(8, "Mild and soft");

    private int price;
    private String description;

    // Constructor
    Apple(int price, String description) {
        this.price = price;
        this.description = description;
    }

    int getPrice() {
        return price;
    }

    String getDescription() {
        return description;
    }
}

public class EnumDemo {
    public static void main(String[] args) {
        Apple ap = Apple.Winesap;

        // Use in switch-case
        switch (ap) {
            case Jonathan:
                System.out.println("Selected: Jonathan");
                break;
            case Winesap:
                System.out.println("Selected: Winesap");
                break;
            default:
                System.out.println("Selected: Some other apple");
        }

        // Print details of the selected apple
        System.out.println(ap + " costs $" + ap.getPrice() + " - " + ap.getDescription());

        System.out.println("\nAll apple varieties:");
        // Loop through all apples
        for (Apple a : Apple.values()) {
            System.out.println(a + ": $" + a.getPrice() + " - " + a.getDescription());
        }
    }
}
```

---

### 🧪 Output Example:
```
Selected: Winesap
Winesap costs $15 - Spicy and rich

All apple varieties:
Jonathan: $10 - Tart and juicy
GoldenDel: $9 - Sweet and mellow
RedDel: $12 - Crisp and sweet
Winesap: $15 - Spicy and rich
Cortland: $8 - Mild and soft
```

---
### ✅ **1. `values()` Method**

- Returns an **array of all enum constants**, in the order they are declared.
- Useful when you want to **loop through** all enum values.

#### Example:

```java
for (Apple a : Apple.values()) {
    System.out.println(a + ": $" + a.getPrice());
}
```

🔹 Output:
```
Jonathan: $10
GoldenDel: $9
RedDel: $12
Winesap: $15
Cortland: $8
```

---

### ✅ **2. `valueOf(String name)` Method**

- Converts a **String name** into the **matching enum constant**.
- Throws `IllegalArgumentException` if the name **does not exactly match** (case-sensitive).
- Must match the enum constant **exactly** as declared.

#### Example:

```java
Apple selected = Apple.valueOf("RedDel");
System.out.println("Selected Apple: " + selected + " costs $" + selected.getPrice());
```

🔹 Output:
```
Selected Apple: RedDel costs $12
```

#### ❗ Incorrect Case:
```java
Apple selected = Apple.valueOf("redDel"); // Will throw an exception!
```

---

### 🧪 Quick Demo: Both in Action

```java
public class EnumMethodDemo {
    public static void main(String[] args) {
        // Using values()
        System.out.println("All Apple Varieties:");
        for (Apple a : Apple.values()) {
            System.out.println(a + ": $" + a.getPrice());
        }

        // Using valueOf()
        String userChoice = "Winesap";
        Apple chosen = Apple.valueOf(userChoice);
        System.out.println("\nYou selected: " + chosen + " which costs $" + chosen.getPrice());
    }
}
```

---
Java enums are far more powerful than simple named constants—they are **full-fledged class types**. Here’s a breakdown of how enums behave like classes:

---

### 🔹 **1. Enum Constants Are Objects**
- Each constant in an enum is actually an **object** of the enum **type**.
- When you define a constructor, it is **called once per constant** at the time of enum creation.

---

### 🔹 **2. Enums Can Have:**
- **Constructors** (private only)
- **Instance variables**
- **Instance methods**
- **Static methods**
- **Implemented interfaces**

---

### 🔸 Example: Enum with Constructor and Fields

```java
enum Apple {
    Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland(8);

    private int price; // Instance variable

    // Constructor
    Apple(int p) {
        price = p;
    }

    // Method
    int getPrice() {
        return price;
    }
}
```

🧠 **Key Notes:**
- The constructor **must be private** (or package-private). You **cannot use `public` or `protected`** in an enum constructor.
- The constructor is **automatically called** when each constant is created (e.g., `Jonathan(10)` calls `Apple(int)`).

---

### 🔸 Using the Enum in a Class

```java
public class EnumClassDemo {
    public static void main(String[] args) {
        Apple ap;

        ap = Apple.Winesap;
        System.out.println("Winesap costs $" + ap.getPrice());

        // Loop through all Apple constants
        for (Apple a : Apple.values()) {
            System.out.println(a + " costs $" + a.getPrice());
        }
    }
}
```

---

### 🔹 Enums Can Implement Interfaces

```java
interface Info {
    String getInfo();
}

enum Device implements Info {
    PHONE("Mobile device"),
    LAPTOP("Portable computer");

    private String description;

    Device(String desc) {
        this.description = desc;
    }

    public String getInfo() {
        return description;
    }
}
```

---
### 🔒 **Restrictions on `enum` in Java**

1. **Enums Cannot Extend Other Classes**
   - All enums implicitly extend `java.lang.Enum`.
   - Since Java does not support multiple inheritance for classes, enums **cannot extend any other class**.
   ```java
   enum MyEnum extends SomeClass {  // ❌ Not allowed
   }
   ```

2. **Enums Cannot Be Superclasses**
   - Enums are **final by design**, so they **cannot be extended**.
   - You can’t create a class or enum that extends an enum:
   ```java
   enum BaseEnum { }
   class SubEnum extends BaseEnum {  // ❌ Not allowed
   }
   ```

---

### ✅ What You *Can* Do with Enums:
- Implement interfaces.
- Define constructors and methods.
- Have instance variables.
- Use enums in `switch` statements.
- Override methods (like `toString()`).
- Use them as singletons or to encapsulate behavior.

---
**Example:** Here's a **complete Java Swing GUI example** where an `enum`:

- Has a **constructor** and **fields**
- Defines **custom methods**
- Implements an **interface**
- Is used in a `JComboBox` to change background color in a `JPanel`

---

### 🎨 Enum + Swing GUI Example: `ColorThemeSelector`

```java
// File: ui/ColorThemeSelector.java
package ui;

import javax.swing.*;
import java.awt.*;

// Define an interface
interface ThemeInfo {
    String getThemeName();
    Color getPrimaryColor();
}

// Enum implementing interface
enum Theme implements ThemeInfo {
    LIGHT("Light Mode", Color.WHITE),
    DARK("Dark Mode", Color.DARK_GRAY),
    OCEAN("Ocean Blue", new Color(0, 128, 192)),
    SUNSET("Sunset Orange", new Color(255, 102, 51));

    private final String name;
    private final Color primaryColor;

    Theme(String name, Color color) {
        this.name = name;
        this.primaryColor = color;
    }

    @Override
    public String getThemeName() {
        return name;
    }

    @Override
    public Color getPrimaryColor() {
        return primaryColor;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class ColorThemeSelector extends JFrame {
    private final JPanel displayPanel;
    private final JComboBox<Theme> themeComboBox;

    public ColorThemeSelector() {
        setTitle("Theme Selector using Enum");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Enum in JComboBox
        themeComboBox = new JComboBox<>(Theme.values());
        themeComboBox.addActionListener(e -> updateTheme());

        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(400, 150));
        displayPanel.setBackground(((Theme) themeComboBox.getSelectedItem()).getPrimaryColor());

        add(themeComboBox, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    private void updateTheme() {
        Theme selectedTheme = (Theme) themeComboBox.getSelectedItem();
        displayPanel.setBackground(selectedTheme.getPrimaryColor());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ColorThemeSelector().setVisible(true);
        });
    }
}
```

---

### ✅ Features Demonstrated:
- `enum Theme` with fields (`name`, `color`), constructor, method overrides
- Implements `ThemeInfo` interface
- `Theme.values()` used in `JComboBox`
- Real-time color change on panel background

---
## 🔄 Java Enumerations Inherit from `java.lang.Enum`

Even though **enums can't explicitly extend a class**, they **implicitly extend** `java.lang.Enum`. This gives them access to several useful methods:

---

### 🧭 `ordinal()` Method

- Returns the **position** (zero-based index) of the enum constant in its declaration.
- Example:

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY
}

System.out.println(Day.MONDAY.ordinal()); // Output: 0
System.out.println(Day.WEDNESDAY.ordinal()); // Output: 2
```

---

### ⚖️ `compareTo()` Method

- Compares **ordinal values** of two enum constants **of the same type**.
- Returns:
  - `0` if equal
  - `< 0` if invoking constant < argument
  - `> 0` if invoking constant > argument

```java
Day d1 = Day.MONDAY;
Day d2 = Day.WEDNESDAY;
System.out.println(d1.compareTo(d2)); // Output: -2
```

---

### ✅ `equals()` Method

- Overrides `Object.equals()`.
- Returns `true` **only** if:
  - Both are **same constant** in the **same enum**.

```java
Day d1 = Day.MONDAY;
Day d2 = Day.MONDAY;
System.out.println(d1.equals(d2)); // true
```

> Even if another enum has the same constant name or ordinal, `equals()` will return false if the types differ.

---

### 🔁 `==` Operator (Preferred)

- You can use `==` to compare enum constants **reliably**, because enums are singletons (interned like `String` literals).

```java
System.out.println(d1 == d2); // true (same reference)
```

---

### 🌟 Summary Table

| Method       | Purpose                                     |
|--------------|---------------------------------------------|
| `ordinal()`  | Returns enum constant's position            |
| `compareTo()`| Compares two constants of the same enum     |
| `equals()`   | Checks equality (same enum + same constant) |
| `==`         | Checks reference equality (always preferred)|

---
**Example:** Here's a simple **Java Swing GUI demo** that uses an enum with a `JComboBox`, showing how `ordinal()`, `compareTo()`, and `equals()` work. When you select a day from the dropdown, the GUI compares it to `MONDAY` and displays all the info in a `JTextArea`.

---

### ✅ Java Swing GUI Example with Enum Operations

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}

public class EnumDemoGUI extends JFrame {
    private JComboBox<Day> dayComboBox;
    private JTextArea outputArea;

    public EnumDemoGUI() {
        setTitle("Enum Demo with JComboBox");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 250);

        dayComboBox = new JComboBox<>(Day.values());
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(dayComboBox, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        dayComboBox.addActionListener(e -> showDayInfo((Day) dayComboBox.getSelectedItem()));

        setVisible(true);
    }

    private void showDayInfo(Day selectedDay) {
        Day referenceDay = Day.MONDAY;
        StringBuilder sb = new StringBuilder();

        sb.append("Selected Day: ").append(selectedDay).append("\n");
        sb.append("Ordinal: ").append(selectedDay.ordinal()).append("\n");
        sb.append("CompareTo MONDAY: ").append(selectedDay.compareTo(referenceDay)).append("\n");
        sb.append("Equals MONDAY: ").append(selectedDay.equals(referenceDay)).append("\n");
        sb.append("== MONDAY: ").append(selectedDay == referenceDay).append("\n");

        outputArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EnumDemoGUI::new);
    }
}
```

---

### 🧪 What This Shows:
- The selected enum constant's `ordinal()`.
- Result of `compareTo(Day.MONDAY)`.
- Result of `equals(Day.MONDAY)`.
- Result of `== Day.MONDAY`.

