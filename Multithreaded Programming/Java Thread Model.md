## 🧵 Java Thread Model

### 🛠️ 1. **Single-Threaded Systems (Polling)**

> ❌ Not very efficient  
Imagine a cashier (main thread) checking every customer (event) in a line **one at a time** by polling:  
- "Need help?" No.  
- "You?" No.  
- "You?" Yes. Serve.  
👉 All others must wait—even if they’re ready.

This is like **polling** in an **event loop** — wastes CPU and time.

---

### ✅ 2. **Java’s Multithreading Model**

> ✅ Better CPU usage  
Now imagine **multiple cashiers** (threads) at the counter:  
- One handles payments  
- One handles bagging  
- One helps with customer queries  
Even if one is **waiting for a payment confirmation**, others keep working!

> 🧠 This is what Java enables:  
✔ When **one thread blocks**, others can continue  
✔ Avoids "one thread dominates" problem  
✔ Makes better use of CPU and system resources

---

## ⚙️ 3. **Thread Behavior in Single-Core vs Multi-Core**

| Type        | Behavior                                                                 |
|-------------|--------------------------------------------------------------------------|
| **Single-core** | Time-sharing between threads (only one runs at a time, others wait)         |
| **Multi-core**  | Threads can **truly run in parallel** (e.g., thread A on core 1, thread B on core 2) |

```java
Thread t1 = new Thread(() -> {
    while (true) System.out.println("Thread A working");
});

Thread t2 = new Thread(() -> {
    while (true) System.out.println("Thread B working");
});

t1.start();
t2.start();
```

> On a **dual-core CPU**, both threads can run **simultaneously**.  
On a **single-core**, they take turns quickly (time-sliced by the OS).

---

## 🔄 4. **Thread Life Cycle (Java States)**

| State         | Description                                                                 |
|---------------|-----------------------------------------------------------------------------|
| `New`         | Thread created but not started                                              |
| `Runnable`    | Ready to run, waiting for CPU time                                          |
| `Running`     | Actively executing                                                          |
| `Blocked`     | Waiting for a resource (e.g., I/O, lock)                                    |
| `Waiting`     | Waiting indefinitely for another thread to signal                           |
| `Timed Waiting` | Waiting for a specified time (e.g., `Thread.sleep(1000)`)                   |
| `Terminated`  | Completed execution or stopped                                              |

---

## ✅ Java Thread Life Cycle Example

This example simulates:
- A thread that sleeps (`Timed Waiting`)
- A thread waiting for another to finish (`Waiting`)
- A thread blocked on a shared resource (`Blocked`)
- All major **thread states in action**

---

### 📄 `ThreadLifecycleDemo.java`

```java
public class ThreadLifecycleDemo {

    static final Object LOCK = new Object();

    public static void main(String[] args) {

        // Thread 1: Will sleep for a while (Timed Waiting)
        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("SleepingThread: Going to sleep...");
                Thread.sleep(3000);
                System.out.println("SleepingThread: Woke up!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "SleepingThread");

        // Thread 2: Will wait on LOCK (Waiting)
        Thread waitingThread = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println("WaitingThread: Waiting for notification...");
                    LOCK.wait(); // Will enter Waiting state
                    System.out.println("WaitingThread: Notified!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WaitingThread");

        // Thread 3: Will block trying to access LOCK (Blocked)
        Thread blockingThread = new Thread(() -> {
            synchronized (LOCK) {
                System.out.println("BlockingThread: Acquired the lock!");
            }
        }, "BlockingThread");

        System.out.println("All threads created (State: NEW)");

        sleepingThread.start();  // Moves to RUNNABLE -> TIMED_WAITING
        waitingThread.start();   // Moves to RUNNABLE -> WAITING
        try { Thread.sleep(500); } catch (Exception ignored) {} // Let others lock

        blockingThread.start();  // Will likely enter BLOCKED if LOCK is held

        System.out.println("SleepingThread State: " + sleepingThread.getState());
        System.out.println("WaitingThread State: " + waitingThread.getState());
        System.out.println("BlockingThread State: " + blockingThread.getState());

        // Notify waitingThread after 5 seconds
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                synchronized (LOCK) {
                    LOCK.notify();
                    System.out.println("Notified waitingThread from notifierThread!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "NotifierThread").start();
    }
}
```

---

### 🧪 What This Shows

- `sleepingThread` → Enters `TIMED_WAITING`
- `waitingThread` → Enters `WAITING` on `LOCK`
- `blockingThread` → May enter `BLOCKED` if `waitingThread` is holding `LOCK`
- A separate `NotifierThread` will notify the `waitingThread` after 5 seconds
- You’ll see all these transitions printed in real time

---

### ✅ Sample Output

```
All threads created (State: NEW)
SleepingThread: Going to sleep...
WaitingThread: Waiting for notification...
SleepingThread State: TIMED_WAITING
WaitingThread State: WAITING
BlockingThread State: BLOCKED
Notified waitingThread from notifierThread!
WaitingThread: Notified!
SleepingThread: Woke up!
```

---
