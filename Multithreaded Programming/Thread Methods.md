### 💤 `Thread.sleep()` – Pause a Thread

The `sleep()` method is used to **pause the current thread** for a specific amount of time. This is often useful to simulate delays or to wait for resources.

#### 🔹 Syntax:
```java
Thread.sleep(long milliseconds) // Pauses for ms
Thread.sleep(long milliseconds, int nanoseconds) // Fine control
```

#### ⚠️ Throws:
- `InterruptedException` — if another thread interrupts the sleeping thread.

---

### ✅ Example: Using `Thread.sleep()`

```java
public class SleepExample {
    public static void main(String[] args) {
        System.out.println("Main thread starting...");

        Thread thread = new Thread(() -> {
            try {
                System.out.println("Child thread sleeping for 2 seconds...");
                Thread.sleep(2000); // 2 seconds
                System.out.println("Child thread woke up!");
            } catch (InterruptedException e) {
                System.out.println("Child thread was interrupted!");
            }
        });

        thread.start();
    }
}
```

---

### 🧾 `setName()` & `getName()` – Naming Threads

These methods help you **set a custom name** for a thread and retrieve it.

#### 🔹 Syntax:
```java
thread.setName("MyThread");
String name = thread.getName();
```

---

### ✅ Example: Naming a Thread

```java
public class NamingExample {
    public static void main(String[] args) {
        Thread customThread = new Thread(() -> {
            System.out.println("Running thread: " + Thread.currentThread().getName());
        });

        customThread.setName("🚀 CustomWorker");
        customThread.start();
    }
}
```

---

### 💡 Real-World Usage of These Methods:

| Method         | Use Case Example                                      |
|----------------|--------------------------------------------------------|
| `sleep()`      | Delay retry for network, pause between animations     |
| `setName()`    | Name threads like "Logger", "Worker-1" for debugging  |
| `getName()`    | Log which thread performed a task                     |

---

## ✅ Example: Countdown Timer using `Thread.sleep()`

We'll create a countdown from 10 to 0 using a separate thread and a delay of 1 second between each number.

```java
public class CountdownTimer {

    public static void main(String[] args) {
        Thread countdownThread = new Thread(() -> {
            try {
                for (int i = 10; i >= 0; i--) {
                    System.out.println("Countdown: " + i);
                    Thread.sleep(1000); // Pause 1 second (1000 ms)
                }
                System.out.println("⏰ Time's up!");
            } catch (InterruptedException e) {
                System.out.println("Countdown was interrupted.");
            }
        });

        countdownThread.setName("⏳ CountdownThread");
        countdownThread.start();
    }
}
```

---

### 🔍 What’s Happening Here?

- We **create a thread** with a lambda expression (`Runnable`).
- The thread **counts down** from 10 to 0, printing the current number.
- After each number, it **sleeps** for 1 second.
- Once it finishes, it prints **"Time's up!"** 🎉

---
