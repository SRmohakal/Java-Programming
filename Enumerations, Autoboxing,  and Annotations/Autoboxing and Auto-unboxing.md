### 🔁 What is Autoboxing?

> Automatically **converting a primitive** into its **corresponding wrapper object** when needed.

```java
Integer i = 10;  // int 10 is automatically boxed into an Integer
```

---

### 🔓 What is Auto-unboxing?

> Automatically **converting a wrapper object** back into its **primitive form** when needed.

```java
int x = i; // Integer i is automatically unboxed to int
```

---

### ⚙️ Autoboxing/Unboxing in Methods

```java
public class Demo {
    static void showSquare(Double d) {
        System.out.println("Square: " + (d * d));
    }

    public static void main(String[] args) {
        showSquare(4.5); // 4.5 (primitive double) is autoboxed into Double
    }
}
```

---

### ➕ Used in Expressions Too

```java
Integer a = 5;
Integer b = 10;
Integer c = a + b;  // auto-unboxed to int, summed, then autoboxed to Integer
```

---

### ⚠️ Word of Warning

While autoboxing is **convenient**, it’s **not free**:

- **Memory:** Wrapper objects take more memory than primitives.
- **Performance:** Autoboxing/unboxing causes extra object creation and computation.

```java
// Bad idea for large loops:
List<Integer> list = new ArrayList<>();
for (int i = 0; i < 1000000; i++) {
    list.add(i);  // each int gets autoboxed into Integer
}
```

> 🧠 **Tip:** Use primitives (`int`, `double`, etc.) in **performance-critical** or **large-scale** logic.

---
### 💡 Project Concept: Autoboxing/Unboxing Visualizer

**Features:**
1. **Input Panel:**
   - Text field for user to enter a value.
   - Dropdown to choose the primitive type (`int`, `double`, etc.).

2. **Action Buttons:**
   - `Box`: Converts the primitive into its wrapper object.
   - `Unbox`: Extracts the primitive value from the wrapper.

3. **Output Panel:**
   - Shows the operation performed.
   - Color-coded:
     - 🟩 Green for primitive values
     - 🟦 Blue for wrapper objects
     - 🟧 Orange when unboxing
   - Shows memory and performance tip when using wrapper objects.

4. **History Log Panel:**
   - Logs all conversions with timestamps and type info.

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

public class AutoboxingUnboxingGUI {
    private JFrame frame;
    private JComboBox<String> typeSelector;
    private JTextField inputField;
    private JTextArea outputArea;

    private Object boxedValue = null;

    public AutoboxingUnboxingGUI() {
        frame = new JFrame("Autoboxing & Unboxing Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Top Input Panel
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(10);
        typeSelector = new JComboBox<>(new String[]{"int", "double", "boolean"});
        JButton boxButton = new JButton("Box");
        JButton unboxButton = new JButton("Unbox");

        inputPanel.add(new JLabel("Enter Value:"));
        inputPanel.add(inputField);
        inputPanel.add(typeSelector);
        inputPanel.add(boxButton);
        inputPanel.add(unboxButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);

        // Button Listeners
        boxButton.addActionListener(e -> handleBox());
        unboxButton.addActionListener(e -> handleUnbox());
    }

    private void handleBox() {
        String input = inputField.getText();
        String type = (String) typeSelector.getSelectedItem();

        try {
            switch (type) {
                case "int":
                    boxedValue = Integer.valueOf(Integer.parseInt(input));
                    log("[BOXED] int -> Integer: " + boxedValue, Color.BLUE);
                    break;
                case "double":
                    boxedValue = Double.valueOf(Double.parseDouble(input));
                    log("[BOXED] double -> Double: " + boxedValue, Color.BLUE);
                    break;
                case "boolean":
                    boxedValue = Boolean.valueOf(Boolean.parseBoolean(input));
                    log("[BOXED] boolean -> Boolean: " + boxedValue, Color.BLUE);
                    break;
            }
        } catch (NumberFormatException ex) {
            log("[ERROR] Invalid input for selected type.", Color.RED);
        }
    }

    private void handleUnbox() {
        if (boxedValue == null) {
            log("[ERROR] No boxed value to unbox.", Color.RED);
            return;
        }
        if (boxedValue instanceof Integer) {
            int val = (Integer) boxedValue;
            log("[UNBOXED] Integer -> int: " + val, Color.ORANGE);
        } else if (boxedValue instanceof Double) {
            double val = (Double) boxedValue;
            log("[UNBOXED] Double -> double: " + val, Color.ORANGE);
        } else if (boxedValue instanceof Boolean) {
            boolean val = (Boolean) boxedValue;
            log("[UNBOXED] Boolean -> boolean: " + val, Color.ORANGE);
        } else {
            log("[ERROR] Unknown boxed type.", Color.RED);
        }
    }

    private void log(String message, Color color) {
        outputArea.setForeground(color);
        outputArea.append(LocalTime.now() + " - " + message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AutoboxingUnboxingGUI::new);
    }
}
```
---
