**Type Wrappers** 
— which bridge the gap between primitive types and objects.

---

### ✅ **Key Points**

| Primitive Type | Wrapper Class |
|----------------|---------------|
| `int`          | `Integer`     |
| `double`       | `Double`      |
| `char`         | `Character`   |
| `boolean`      | `Boolean`     |
| `float`        | `Float`       |
| `long`         | `Long`        |
| `short`        | `Short`       |
| `byte`         | `Byte`        |

---

### 💡 Wrapper Usage

- ✅ Use `valueOf(...)` to get wrapper objects (recommended way).
- ✅ Use `xxxValue()` (like `intValue()`, `booleanValue()`) to extract primitive values.
- ✅ All wrapper classes override `toString()` for easy printing.
- ❌ Constructors like `new Integer(5)` or `new Character('a')` are deprecated!

---

### 🧪 Java Example: Using Wrapper Classes

```java
public class WrapperDemo {
    public static void main(String[] args) {
        // Numeric Wrappers
        Integer i = Integer.valueOf(42);
        Double d = Double.valueOf("3.14");

        // Character and Boolean
        Character c = Character.valueOf('X');
        Boolean b = Boolean.valueOf("true");

        // Displaying primitive values
        System.out.println("Integer: " + i.intValue());
        System.out.println("Double: " + d.doubleValue());
        System.out.println("Character: " + c.charValue());
        System.out.println("Boolean: " + b.booleanValue());

        // Autoboxing / Unboxing
        int a = i;  // unboxing
        Integer j = 100; // autoboxing

        System.out.println("Autoboxed Integer j: " + j);
        System.out.println("Unboxed value a: " + a);

        // Using overridden toString()
        System.out.println("Integer as string: " + i.toString());
    }
}
```

---

### ⚙ Bonus: All Numeric Wrappers Inherit from `Number`

You can write polymorphic code like:

```java
Number num = Double.valueOf(5.5);
System.out.println("As int: " + num.intValue());
System.out.println("As float: " + num.floatValue());
```

---

**Java Swing GUI mini-project** 
that lets you input a primitive (like an `int`, `double`, or `char`), select the type, and see the **wrapped object**, its **primitive value**, and its **`toString()` result** — all in one simple window!

---

### 🧩 Features:
- Choose type: `Integer`, `Double`, `Character`, or `Boolean`
- Input a value and click "Wrap It"
- Displays: Wrapper object, primitive value, and string representation

---

### 💻 Java Swing Code: `WrapperDemoGUI.java`

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WrapperDemoGUI extends JFrame {
    private JComboBox<String> typeCombo;
    private JTextField inputField;
    private JTextArea resultArea;
    private JButton wrapButton;

    public WrapperDemoGUI() {
        setTitle("Java Wrapper Class Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout(10, 10));

        String[] types = {"Integer", "Double", "Character", "Boolean"};
        typeCombo = new JComboBox<>(types);
        inputField = new JTextField(10);
        wrapButton = new JButton("Wrap It!");
        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Type:"));
        topPanel.add(typeCombo);
        topPanel.add(new JLabel("Value:"));
        topPanel.add(inputField);
        topPanel.add(wrapButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        wrapButton.addActionListener(e -> wrapValue());

        setLocationRelativeTo(null); // center window
        setVisible(true);
    }

    private void wrapValue() {
        String selectedType = (String) typeCombo.getSelectedItem();
        String input = inputField.getText().trim();
        StringBuilder output = new StringBuilder();

        try {
            switch (selectedType) {
                case "Integer":
                    Integer intWrapper = Integer.valueOf(input);
                    output.append("Wrapper: ").append(intWrapper.getClass().getSimpleName()).append("\n");
                    output.append("Primitive: ").append(intWrapper.intValue()).append("\n");
                    output.append("toString(): ").append(intWrapper.toString());
                    break;

                case "Double":
                    Double doubleWrapper = Double.valueOf(input);
                    output.append("Wrapper: ").append(doubleWrapper.getClass().getSimpleName()).append("\n");
                    output.append("Primitive: ").append(doubleWrapper.doubleValue()).append("\n");
                    output.append("toString(): ").append(doubleWrapper.toString());
                    break;

                case "Character":
                    if (input.length() != 1)
                        throw new IllegalArgumentException("Enter exactly one character.");
                    Character charWrapper = Character.valueOf(input.charAt(0));
                    output.append("Wrapper: ").append(charWrapper.getClass().getSimpleName()).append("\n");
                    output.append("Primitive: ").append(charWrapper.charValue()).append("\n");
                    output.append("toString(): ").append(charWrapper.toString());
                    break;

                case "Boolean":
                    Boolean boolWrapper = Boolean.valueOf(input);
                    output.append("Wrapper: ").append(boolWrapper.getClass().getSimpleName()).append("\n");
                    output.append("Primitive: ").append(boolWrapper.booleanValue()).append("\n");
                    output.append("toString(): ").append(boolWrapper.toString());
                    break;
            }
        } catch (Exception ex) {
            output.append("Error: ").append(ex.getMessage());
        }

        resultArea.setText(output.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WrapperDemoGUI::new);
    }
}
```

---

