### ✅ **Question:**

You are given a Java file named `AQ2.java`, and **you are not allowed to change any code** in that file. Your task is to:

- Create a class `PA` inside a file named `PA.java` under the package `pkA.p1`.
- Create a class `PB` inside a file named `PB.java` under the package `pkA.p1.p2`.
- Create an interface `PC` inside a file named `PC.java` under the package `pkA.p3`.

These classes and interfaces must be written in such a way that running the `main` method inside the `AQ2` class produces the following output:

```
Start
ABC==ABC
10
15
25
Welcome Java
2
6
6
XYZXYZ
25
50
```

You are also given a helper class `S` in `AQ2.java` that implements `PC.IPC2`.

---

### ✅ **AQ2.java** (Given File — Do NOT Modify):

```java
package pkA;

class S implements pkA.p3.PC.IPC2 {}

public class AQ2 {
    public static void main(String[] args) {
        System.out.println("Start");

        pkA.p1.PA pa = new pkA.p1.PA();
        System.out.println(pa.m("ABC"));
        System.out.println(pa.m4(5));
        System.out.println(pa.m4(10));

        pkA.p3.PC.IPC ipc = pa;
        System.out.println(ipc.m4(20));

        System.out.println(pkA.p1.p2.PB.m2("Java"));

        pkA.p1.p2.PB pb = new pkA.p1.p2.PB();
        System.out.println(pb.m(4, 2));
        System.out.println(pb.m(9, 3));

        pkA.p3.PC pc = pb;
        System.out.println(pc.m(7, 1));

        System.out.println(pkA.p3.PC.m3("XYZ"));

        S s = new S();
        System.out.println(s.m2(5));

        pkA.p3.PC.IPC2 ipc2 = s;
        System.out.println(ipc2.m2(10));
    }
}
```

---

### ✅ Solution Files:

#### 1. `PC.java` — package `pkA.p3`:
```java
package pkA.p3;

public interface PC {
    int m(int a, int b); // for PB
    static String m3(String s) {
        return s + s;
    }

    interface IPC {
        int m4(int x); // for PA
    }

    interface IPC2 {
        default int m2(int x) {
            return x * 5;
        }
    }
}
```

---

#### 2. `PA.java` — package `pkA.p1`:
```java
package pkA.p1;

import pkA.p3.PC;

public class PA implements PC.IPC {
    public String m(String s) {
        return s + "==" + s;
    }

    public int m4(int x) {
        return x + 5;
    }
}
```

---

#### 3. `PB.java` — package `pkA.p1.p2`:
```java
package pkA.p1.p2;

import pkA.p3.PC;

public class PB implements PC {
    public static String m2(String s) {
        return "Welcome " + s;
    }

    public int m(int a, int b) {
        return a - b;
    }
}
```

---
Great question! Here's a complete step-by-step guide to **run this multi-package Java project in VS Code**:

---

### ✅ **Step 1: Set Up Folder Structure**

Create a new folder for your project, for example:  
📁 `JavaMultiPackageProject`

Inside it, create the following folder structure:

```
JavaMultiPackageProject/
├── pkA/
│   └── AQ2.java
├── pkA/p1/
│   └── PA.java
├── pkA/p1/p2/
│   └── PB.java
├── pkA/p3/
│   └── PC.java
```

So your files go in:

- `AQ2.java` → `pkA/AQ2.java`
- `PA.java` → `pkA/p1/PA.java`
- `PB.java` → `pkA/p1/p2/PB.java`
- `PC.java` → `pkA/p3/PC.java`

---

### ✅ **Step 2: Open the Folder in VS Code**

1. Open VS Code.
2. Go to **File > Open Folder...** and choose `JavaMultiPackageProject`.

---

### ✅ **Step 3: Install Java Extension Pack (if not already installed)**

If you haven’t already, install the **Java Extension Pack** from Microsoft:

- Go to Extensions (`Ctrl+Shift+X`)
- Search for: `Java Extension Pack`
- Install it.

---

### ✅ **Step 4: Compile the Code**

Open a terminal in VS Code (`Ctrl+``) and compile everything:

```bash
javac pkA/AQ2.java
```

This will automatically compile the other dependent classes in the sub-packages as long as your package declarations are correct.

---

### ✅ **Step 5: Run the Code**

Still in the terminal:

```bash
java pkA.AQ2
```
---
