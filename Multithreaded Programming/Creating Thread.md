## ✅ Creating a Thread by Implementing `Runnable`

### 🧩 Step-by-Step Breakdown

1. **Create a class that implements `Runnable`**
   - This class **must override** the `run()` method.
   - This is where the thread’s task is defined.

2. **Pass the object to a `Thread` constructor**
   - Use `Thread(Runnable obj, String threadName)`.

3. **Start the thread** using `.start()`
   - This invokes `run()` **asynchronously**, i.e., on a new thread.

---

## 🔨 Example: Creating a Thread with Runnable

```java
// 1. Create a class that implements Runnable
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(1000); // 1 second pause between counts
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
        }
    }
}

// 2. Main class to run the thread
public class RunnableExample {
    public static void main(String[] args) {
        MyRunnable task = new MyRunnable(); // Create Runnable task

        // 3. Create a Thread object from the Runnable instance
        Thread thread = new Thread(task, "WorkerThread");

        // 4. Start the thread
        thread.start();

        System.out.println("Main thread ends here.");
    }
}
```

---

## 🧠 Key Concepts Illustrated

| Concept | Explanation |
|--------|-------------|
| `Runnable` | Functional interface with a single method: `run()` |
| `Thread(Runnable, String)` | Constructor that binds a `Runnable` task to a thread |
| `start()` | Launches the thread and internally calls `run()` |
| `sleep(ms)` | Causes the current thread to pause (simulate a delay) |

---

## ✅ Creating a Thread by Extending `Thread`

### 🧩 Step-by-Step

1. **Create a class that extends `Thread`**.
2. **Override the `run()` method** — this is the code that will run in the new thread.
3. **Create an instance of that class**.
4. **Call `.start()`** to begin execution.

---

## 🔨 Example: Creating a Thread by Extending `Thread`

```java
// 1. Create a class extending Thread
class MyThread extends Thread {
    public MyThread(String name) {
        super(name); // Set thread name
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - Count: " + i);
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
            }
        }
    }
}

// 2. Main class to test the thread
public class ThreadExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread-A");
        MyThread t2 = new MyThread("Thread-B");

        t1.start(); // Start Thread-A
        t2.start(); // Start Thread-B

        // Use isAlive and join
        try {
            t1.join(); // Wait for Thread-A to finish
            t2.join(); // Wait for Thread-B to finish
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Main thread ends.");
    }
}
```

---

## 🧠 Highlights

| Method | Purpose |
|--------|---------|
| `start()` | Starts the thread (calls `run()` asynchronously) |
| `getName()` / `setName()` | Set or get the thread’s name |
| `isAlive()` | Check if the thread is still running |
| `join()` | Wait for the thread to finish execution |

---
