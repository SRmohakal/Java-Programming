### 💡 **Multitasking in Modern Operating Systems**

**Multitasking** is the ability of an operating system to perform **more than one task at a time**. This is a fundamental feature in all modern operating systems, including Windows, macOS, and Linux. In Java, multitasking is closely tied to **multithreading**, as the Java Virtual Machine (JVM) is built with multitasking in mind.

There are **two primary types** of multitasking:

---

### 🔁 **1. Process-Based Multitasking**

- A **process** is an **independent program** in execution.
- In process-based multitasking, the operating system executes **multiple programs** at once.

#### ✅ Example:
- You are:
  - Running a **Java compiler** to compile your project.
  - Listening to music on a **media player**.
  - Browsing a website in **Google Chrome**.

Each of these is a separate **process**, and your OS manages them so they appear to run **simultaneously**.

#### ⚙️ Characteristics:
| Feature                          | Description |
|----------------------------------|-------------|
| **Execution unit**               | Entire programs (processes) |
| **Memory usage**                 | Each process has its own memory space |
| **Communication**                | Inter-process communication (IPC) is slower and more complex |
| **Overhead**                     | Higher, since context switching between processes is expensive |
| **Independence**                 | Processes are independent; if one crashes, others remain unaffected |

---

### 🧵 **2. Thread-Based Multitasking**

- A **thread** is a **lightweight subprocess** or a **smallest unit of execution** within a process.
- In thread-based multitasking, a **single program** performs multiple tasks at the same time using **multiple threads**.

#### ✅ Example:
A **text editor** like Notepad++:
- One thread handles **user input**
- Another thread handles **spell-check**
- Another thread might be **auto-saving** in the background

All of these threads are part of the **same program** (process), but work concurrently.

#### ⚙️ Characteristics:
| Feature                          | Description |
|----------------------------------|-------------|
| **Execution unit**               | Threads within a single program |
| **Memory usage**                 | Threads share the same memory and resources |
| **Communication**                | Thread communication is fast and efficient |
| **Overhead**                     | Low, because switching between threads is cheaper than switching between processes |
| **Independence**                 | Threads are interdependent, but more efficient for tasks needing frequent interaction |

---

## 🧪 **Example 1: Thread-Based Multitasking**

This simulates a **text editor** that:
- Performs **auto-saving** in the background
- **Formats text**
- Responds to **user input**

Each of these runs as a separate **thread** within the same Java process.

```java
// ThreadExample.java
class AutoSave extends Thread {
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println("Auto-saving document...");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }
    }
}

class TextFormatter extends Thread {
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println("Formatting text...");
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
        }
    }
}

class UserInputHandler extends Thread {
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println("Waiting for user input...");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        AutoSave autoSave = new AutoSave();
        TextFormatter formatter = new TextFormatter();
        UserInputHandler inputHandler = new UserInputHandler();

        autoSave.start();
        formatter.start();
        inputHandler.start();
    }
}
```

> 🧵 **Each thread runs independently**, and the output shows concurrent behavior.

---

## 🧪 **Example 2: Process-Based Multitasking (launching multiple Java programs)**

Let’s say you have **two independent programs**: a Calculator and a Timer. You can launch them both **from your system** or **another Java program** using `Runtime.getRuntime().exec()` or `ProcessBuilder`.

### ✏️ Calculator.java
```java
public class Calculator {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 1; i <= 5; i++) {
            System.out.println("Calculator running...");
            Thread.sleep(2000);
        }
    }
}
```

### ⏱ TimerApp.java
```java
public class TimerApp {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 1; i <= 5; i++) {
            System.out.println("Timer ticking... " + i);
            Thread.sleep(1000);
        }
    }
}
```

### 🚀 Launcher.java (to run both as separate processes)
```java
import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("cmd /c start cmd /k java Calculator");
        Runtime.getRuntime().exec("cmd /c start cmd /k java TimerApp");
        System.out.println("Launched Calculator and TimerApp in separate processes.");
    }
}

//OR
import java.io.IOException;
import java.util.Arrays;

public class Launcher {
    public static void main(String[] args) {
        try {
            // Launch Calculator in new terminal
            new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", "java Calculator").start();

            // Launch TimerApp in new terminal
            new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", "java TimerApp").start();

            System.out.println("Launched Calculator and TimerApp in separate processes.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
✅ Here’s **how to run the Process-Based Multitasking example** in **VS Code**.

---

### 🧰 **1. Folder Structure**

Create a project folder like this:

```
ProcessMultitaskingDemo/
├── Calculator.java
├── TimerApp.java
└── Launcher.java
```

---

### ⚙️ **3. Compile All Files**

Open the **VS Code terminal** in the folder and run:

```bash
javac Calculator.java TimerApp.java Launcher.java
```

You should now have these `.class` files:

```
Calculator.class
TimerApp.class
Launcher.class
```

---

### ▶️ **4. Run the Launcher**


```bash
java Launcher
```
  Launched Calculator and TimerApp in separate processes.
  ```

### 🪟 Why `cmd /c start cmd /k`?

This is a **Windows-specific** trick to:
- Open a **new command window**
- Keep it **open after the process ends**
- Run your Java class inside it

## 🔚 Summary:

| Type              | Java Class      | Simulates                      |
|-------------------|------------------|--------------------------------|
| Thread-based      | `ThreadExample`  | Multiple tasks in one program |
| Process-based     | `Launcher`       | Multiple programs              |
