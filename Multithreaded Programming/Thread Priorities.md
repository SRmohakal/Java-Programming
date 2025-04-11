## ⚙️ **Thread Priorities in Java**

Each thread in Java has a **priority**, which helps the **thread scheduler** decide **which thread to run next** when multiple threads are ready to execute.

---

### 🧮 **Priority Range**
Java threads can have priorities ranging from:
- `Thread.MIN_PRIORITY` = **1**
- `Thread.NORM_PRIORITY` = **5** (default)
- `Thread.MAX_PRIORITY` = **10**

---

### 🧵 How to Set a Thread's Priority

```java
Thread t1 = new Thread(() -> {
    System.out.println("Thread 1 is running");
});
t1.setPriority(Thread.MAX_PRIORITY); // 10
```

You can also pass an `int` between 1 and 10 directly:

```java
t1.setPriority(8);
```

---

### 🧠 Does Higher Priority Mean Faster?

> **No.** A higher-priority thread doesn't necessarily run *faster* — it just has a *better chance* of being chosen by the CPU **when multiple threads are competing**.

However, the actual scheduling is **OS-dependent**:
- On some systems, thread priorities are **respected strictly**.
- On others (like some JVMs on Windows/Linux), it's more of a **suggestion**.

---

### 📊 **Context Switching** (Thread Switching Rules)

There are two main ways a thread gives up the CPU:

1. **Voluntarily**:
   - The thread *yields*, *sleeps*, or gets *blocked* (e.g. waiting for I/O).
   - The scheduler then picks the **highest-priority thread** that’s ready.

2. **Preemptively**:
   - A higher-priority thread becomes ready.
   - It **immediately interrupts** the lower-priority thread and starts running.

---

### 🧪 Example: Setting Priorities

```java
public class PriorityDemo {
    public static void main(String[] args) {
        Thread low = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Low priority thread");
            }
        });
        low.setPriority(Thread.MIN_PRIORITY);

        Thread high = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("High priority thread");
            }
        });
        high.setPriority(Thread.MAX_PRIORITY);

        low.start();
        high.start();
    }
}
```

### 🧾 Expected Output (may vary):
```
High priority thread
High priority thread
Low priority thread
High priority thread
Low priority thread
...
```

Depending on the JVM/OS, you might see **high-priority thread dominating**, or **interleaving** output.

---

### 🔄 Summary

| Feature | High Priority | Low Priority |
|--------|---------------|--------------|
| CPU time preference | ✅ Higher | 🚫 Lower |
| Execution speed | 🟰 Same (depends on task) | 🟰 |
| Can be preempted by another thread? | 🚫 Only by higher priority | ✅ Yes |
| Voluntarily yield or sleep? | ✅ Yes | ✅ Yes |

---
