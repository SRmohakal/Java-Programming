### 🔹 Problem Statement:

You are provided with a file `TestA.java` which **you are not allowed to modify**. Your task is to write a class named `A` in a separate file called `A.java` so that when the `main` method of `TestA` is executed, the output is:

```
Test #01 Passed.
Test #02 Passed.
Test #03 Passed.
Test #04 Passed.
Test #05 Passed.
Test #06 Passed.
Test #07 Passed.
Test #08 Passed.
Test #09 Passed.
Test #10 Passed.
```

---

### 📄 Provided Interface & Test File

#### Interface and Nested Interface:
```java
interface B {
    int i = 10;

    int m1(int i);

    default String m2(String s) {
        return s.replace('a', 'd');
    }

    interface C {
        int i = 20;

        int m3(int i);
    }
}
```

---

#### Test File (Do NOT Modify)
```java
public class TestA {

    static int testCounter = 1;

    static void tester(boolean b) {
        String status = "Failed";
        if (b)
            status = "Passed";
        System.out.printf("Test #%02d %s.%n", testCounter++, status);
    }

    public static void main(String[] args) {
        A a = new A(5);
        tester((a.i + B.i + B.C.i) == 35);
        B b = a;
        tester(b.m1(15) == 30 && b.m1(5) == 20);
        a.i += 10;
        tester(b.m1(15) == 40 && b.m1(5) == 30);
        tester(b.m2("abaabd").equals("dbddbd"));
        tester(b.m2("baddabaa").equals("bddddbdd"));
        B.C c = (B.C) b;
        tester(c.m3(5) == 40 && c.m3(15) == 50);
        tester(a.m3(5, 10) == 50 && a.m3(15, 10) == 60);
        a.i += 10;
        tester(c.m3(15) == 60);
        tester(a.m3(15, 10) == 70);
        tester(new A(B.i).m3(10, 10) == 50);
    }
}
```

---

## ✅ Your Task

Create the `A.java` file such that:
- Class `A` implements interface `B`
- Class `A` also implements the nested interface `B.C`
- Contains an `int i` field
- All test cases pass

---

## ✅ Solution: A.java

```java
public class A implements B, B.C {
    public int i;

    public A(int i) {
        this.i = i;
    }

    @Override
    public int m1(int i) {
        return this.i + i + 10;
    }

    @Override
    public int m3(int i) {
        return this.i + i + B.C.i;
    }

    public int m3(int x, int y) {
        return this.i + x + y + 20;
    }
}
```

---
