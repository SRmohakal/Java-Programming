### ✅Question:

You are provided the `AQ3.java` file below. **You are NOT allowed to change any code inside it**.

```java
// AQ3.java
public class AQ3 {
    public static void main(String[] args) {
        for (int n=0; n<6; n++) {
            System.out.println("Exceptions Case: " + n);        
            try { 
                if (n == 0) { System.out.println("Starting: "+n);
                    Exc.a(); System.out.println("Ending: "+n); }
                if (n == 1) { System.out.println("Starting: "+n);
                    Exc.b(); System.out.println("Ending: "+n); }
                try { 
                    if (n == 2) { System.out.println("Starting: "+n);
                        Exc.c(); System.out.println("Ending: "+n); }
                    else if (n == 3) { System.out.println("Starting: "+n);
                        Exc.d(); System.out.println("Ending: "+n); }
                    else { System.out.println("Starting: "+n);
                        throw new NewExc(n); }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Exception is "+ex);
                    if(ex.getCause()!=null) System.out.println("Cause: "+ ex.getCause());
                }
            } catch (ArithmeticException | NullPointerException ex) {
                System.out.println("Exception = "+ex);
                if(ex.getCause()!=null) System.out.println("Cause: "+ ex.getCause());
            } catch (NewExc ex) { 
                System.out.println(ex);
                if(ex.getCause()!=null) System.out.println("Cause: "+ ex.getCause());
            } catch (Exception ex) { 
                System.out.println("Any Exception");
                if(ex.getCause()!=null) System.out.println("Cause: "+ ex.getCause());
            } 
        }
        System.out.println("Exam Ended");        
    }
}
```

You must now write:
1. A class named `Exc` in a separate file `Exc.java`
2. A class named `NewExc` in `NewExc.java` that extends `Exception` or a subclass

If you run `AQ3.java`, it must produce the following output:

---

### ✅ Required Output:

```
Exceptions Case: 0
Starting: 0
Exception = java.lang.ArithmeticException
Exceptions Case: 1
Starting: 1
Exception = java.lang.NullPointerException
Cause: java.lang.ArithmeticException
Exceptions Case: 2
Starting: 2
Exception is java.lang.ArrayIndexOutOfBoundsException: Index Out!
Exceptions Case: 3
Starting: 3
New Exception: New Exception: 0
Exceptions Case: 4
Starting: 4
New Exception: New Exception: 4
Exceptions Case: 5
Starting: 5
New Exception: New Exception: 5
Exam Ended
```

---

### ✅ Solution

#### `Exc.java`
```java
public class Exc {
    public static void a() {
        throw new ArithmeticException();
    }

    public static void b() {
        NullPointerException ex = new NullPointerException();
        ex.initCause(new ArithmeticException());
        throw ex;
    }

    public static void c() {
        throw new ArrayIndexOutOfBoundsException("Index Out!");
    }

    public static void d() throws NewExc {
        throw new NewExc(0);
    }
}
```

#### `NewExc.java`
```java
public class NewExc extends Exception {
    int val;

    public NewExc(int val) {
        super("New Exception: " + val);
        this.val = val;
    }

    @Override
    public String toString() {
        return "New Exception: " + super.getMessage();
    }
}
```

---
✅ 2. Create a Java project folder

Create a folder for your project, e.g., ExceptionDemo.

Inside it, create 3 files:
```
ExceptionDemo/
├── AQ3.java
├── Exc.java
└── NewExc.java
```
---
✅ 5. Compile and Run

You can either use the Terminal or the Java Extension Run Button.

Option 1: Run from terminal

In VS Code:

1. Open Terminal → New Terminal

2. Compile all Java files:
```
javac *.java
```
Run the main class:
```
java AQ3
```
---
