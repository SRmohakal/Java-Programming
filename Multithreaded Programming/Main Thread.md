## 🧵 What is the **Main Thread**?

When you run any Java application, the **main thread** is the one that starts executing your `main(String[] args)` method.

It's:
- The **first thread** your program uses.
- The thread from which **all other threads (child threads)** are usually started.
- Often responsible for **shutdown actions**, like cleanup or reporting results.

---

## 📌 Accessing and Controlling the Main Thread

You can **access** the main thread using:

```java
Thread mainThread = Thread.currentThread();
```

This gives you a reference to the current running thread — which is the main thread **if called inside `main()`**.

---

## ✏️ Example: Main Thread in Action

```java
public class MainThreadDemo {
    public static void main(String[] args) {
        // Get reference to main thread
        Thread mainThread = Thread.currentThread();

        System.out.println("Current Thread: " + mainThread.getName());
        System.out.println("Priority: " + mainThread.getPriority());
        System.out.println("Is Alive: " + mainThread.isAlive());

        // Create a child thread
        Thread child = new Thread(() -> {
            System.out.println("Child thread is running...");
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Child thread finished.");
        });

        child.start();

        // Main thread waits for child to finish
        try {
            child.join(); // Important: main waits until child is done
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread is finishing...");
    }
}
```

---

### 🔍 Output Explanation

```bash
Current Thread: main
Priority: 5
Is Alive: true
Child thread is running...
Child thread finished.
Main thread is finishing...
```

- You access and print details about the main thread.
- You create a **child thread**, which runs some task.
- The main thread **waits** for the child to finish using `join()`.
- Only after the child finishes, the main thread completes.

---

## ✅ Summary: Why the Main Thread Matters

| Feature              | Purpose                                                                 |
|----------------------|-------------------------------------------------------------------------|
| `Thread.currentThread()` | Get the currently executing thread (main, or any other).           |
| Main Thread Role     | Starts first, often finishes last. Spawns and monitors other threads.   |
| Can Control It       | Yes — change name, priority, or make it sleep, like any thread.         |

---
