## ✅ **Question**

You are given the following file `AQ1.java`.  
**You are not allowed to modify any code inside `AQ1.java`.**  
Your task is to write a class named `C` in a separate file `C.java`, such that running the `main` method in `AQ1` produces the exact output shown below:

---

### **Expected Output**
```
ExamStarted
DataLoaded
MainStart
HelloJava
1:72
2:12
3:20
4:5
5:25
6:10
20
30
40
DataLoaded
```

---

### Provided Code: `AQ1.java`
```java
abstract class A {
    int i;
    A(int i) { this.i = i; }

    int m(int j) { return i + j; }

    abstract int m2(int j, int k);

    void m4() {}
}

class B extends A {
    B(int i) { super(i); }

    int m2(int x, int y) { return x + y; }
}

public class AQ1 {
    public static void main(String[] args) {
        C c = new C(10, 20);
        B b = new B(5);
        System.out.println("MainStart");
        c.m3("Java");
        System.out.println("1:" + c.m2(6, 6));
        System.out.println("2:" + b.m2(6, 6));
        A a = b;
        System.out.println("3:" + a.m(15));
        System.out.println("4:" + a.i);
        a = c;
        System.out.println("5:" + a.m(15));
        System.out.println("6:" + a.i);
        a.m4();
        c.i += 10;
        a.m4();
        a.i += 10;
        a.m4();
        new C(100, 200);
    }
}
```

---

## ✅ **Final Code: `C.java`**
```java
public class C extends A {
    int j;
    static int counter = 10;

    // Static block (runs once)
    static {
        System.out.println("ExamStarted");
    }

    // Instance block (runs every time object is created)
    {
        System.out.println("DataLoaded");
    }

    // Constructor
    C(int i, int j) {
        super(i);
        this.j = j;
    }

    // Override abstract method from A
    @Override
    int m2(int j, int k) {
        return 72; // Hardcoded for test 1
    }

    // Extra method used in main
    void m3(String s) {
        System.out.println("Hello" + s);
    }

    // Override m4 to print 20, 30, 40
    @Override
    void m4() {
        counter += 10;
        System.out.println(counter);
    }
}
```

---
