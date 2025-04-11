## 🔒 What is a Deadlock?

A **deadlock** happens when:
1. **Thread A** holds **Lock 1** and waits for **Lock 2**.
2. **Thread B** holds **Lock 2** and waits for **Lock 1**.
  
Both are stuck, waiting on each other **forever**. That's the essence of a deadlock.

---

## 🔁 Real-World Analogy

Imagine two people:
- Person A holds **Fork 1** and wants **Fork 2**.
- Person B holds **Fork 2** and wants **Fork 1**.
Neither releases what they have. No one eats. 🍴🍴

---

## 🧪 Code Example of a Deadlock

```java
class SharedResource {
    synchronized void methodA(SharedResource other) {
        System.out.println(Thread.currentThread().getName() + " entered methodA");
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        System.out.println(Thread.currentThread().getName() + " trying to call methodB on other");
        other.methodB(this); // Tries to lock other
    }

    synchronized void methodB(SharedResource other) {
        System.out.println(Thread.currentThread().getName() + " entered methodB");
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        System.out.println(Thread.currentThread().getName() + " trying to call methodA on other");
        other.methodA(this); // Tries to lock other
    }
}
```

---

### 🧵 Two Threads That Cause Deadlock

```java
public class DeadlockDemo {
    public static void main(String[] args) {
        SharedResource obj1 = new SharedResource();
        SharedResource obj2 = new SharedResource();

        Thread t1 = new Thread(() -> obj1.methodA(obj2), "Thread-1");
        Thread t2 = new Thread(() -> obj2.methodB(obj1), "Thread-2");

        t1.start();
        t2.start();
    }
}
```

### 💥 What Happens?

- `Thread-1` locks `obj1` and waits for `obj2`.
- `Thread-2` locks `obj2` and waits for `obj1`.
- Both threads are **stuck forever**. ❌

---

## 🛡️ How to Prevent Deadlock

Here are a few techniques:

### ✅ 1. **Always Lock in the Same Order**
Ensure all threads acquire locks in the same order.

```java
// Always lock obj1 first, then obj2
synchronized(obj1) {
    synchronized(obj2) {
        // safe work
    }
}
```

### ✅ 2. **Use `tryLock()` with Timeout** (in `java.util.concurrent.locks.Lock`)

This allows a thread to give up trying if it waits too long.

```java
if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
    if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
        // Do work
    } else {
        lock1.unlock();
    }
}
```

### ✅ 3. **Avoid Nested Locks**  
Keep your code as simple and flat as possible.

### ✅ 4. **Use a Deadlock Detector**  
Tools like VisualVM or thread dumps can help spot deadlocks in running apps.

---
