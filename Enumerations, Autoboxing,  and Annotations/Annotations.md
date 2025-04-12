## 📝 **Java Annotations**

### ✅ **What Are Annotations?**
- Special **metadata** you embed in code using `@`.
- They **do not change how the program runs**.
- Useful for **tools**, **compilers**, **frameworks**, and **code generators**.
- Also referred to as **metadata**, but the proper term is **annotation**.

---

### 🧱 **Annotation Basics**
- Declared using the `@interface` keyword:
  ```java
  @interface MyAnnotation {
      String str();
      int val();
  }
  ```
- Key points:
  - **No method bodies**, just method signatures.
  - They behave like **fields**, not like normal methods.
  - You don’t use `extends` — all annotations **implicitly extend** `java.lang.annotation.Annotation`.

---

### 🧩 **Where Can Annotations Be Used?**
- Annotations can be added to:
  - Classes
  - Methods
  - Fields
  - Parameters
  - Enum constants
  - Even **other annotations**

---

### 📌 **Usage Example**
```java
@MyAnnotation(str = "Hello", val = 42)
public void exampleMethod() {
    // ...
}
```

---
### Declaring an Annotation with RUNTIME Retention
```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Info {
    String author();
    int version();
}
```

---

## 🔍 **Using Reflection to Access Annotation at Runtime**

### Step 1: Use the annotation on a method
```java
class Demo {
    @Info(author = "Alex", version = 2)
    public void show() {
        System.out.println("Demo method");
    }
}
```

---

### Step 2: Use Reflection to Access It

```java
import java.lang.reflect.Method;

public class AnnotationReader {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Demo.class;

        Method method = cls.getMethod("show");

        if (method.isAnnotationPresent(Info.class)) {
            Info annotation = method.getAnnotation(Info.class);
            System.out.println("Author: " + annotation.author());
            System.out.println("Version: " + annotation.version());
        } else {
            System.out.println("No annotation found.");
        }
    }
}
```

---

## ✅ Output
```
Author: Alex
Version: 2
```

---

## 🎯 Summary
- Use `@Retention(RUNTIME)` if you want your annotation to survive past compile time and be usable at runtime.
- Use `getAnnotation()` with reflection to inspect the annotation.
- This is useful for tools like **JUnit, Spring, Hibernate**, or **your own custom processors**.

---
### 🔍 **Syntax Recap**
```java
Annotation[] getAnnotations()
```
- Returns **all annotations** present on the element **and retained at runtime**.
- Works on: `Class`, `Method`, `Field`, `Constructor`, etc.

---

### ✅ Example: List All Method Annotations

Let's extend our earlier `Demo` class:

```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// Custom annotation with RUNTIME retention
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Info {
    String author();
    int version();
}

// Another custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String message();
}

class Demo {
    @Info(author = "Alex", version = 1)
    @Todo(message = "Optimize this method")
    public void show() {
        System.out.println("Show method");
    }

    public void plainMethod() {
        System.out.println("No annotations here");
    }
}
```

---

### 🧠 Using `getAnnotations()`

```java
public class AllAnnotationsReader {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Demo.class;

        for (Method method : cls.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());

            Annotation[] annotations = method.getAnnotations();
            if (annotations.length == 0) {
                System.out.println("  No annotations.");
            } else {
                for (Annotation annotation : annotations) {
                    System.out.println("  Annotation: " + annotation.annotationType().getSimpleName());
                    System.out.println("  -> " + annotation.toString());
                }
            }
        }
    }
}
```

---

### 💡 Output
```
Method: show
  Annotation: Info
  -> @Info(author=Alex, version=1)
  Annotation: Todo
  -> @Todo(message=Optimize this method)
Method: plainMethod
  No annotations.
```

---

## 🧰 Summary
- Use `getAnnotations()` to grab **all annotations** on a method/class/field.
- Great for **generic tools** that work with annotations without knowing them beforehand.
- You can still use `instanceof` or `getAnnotation(Class)` to process specific ones after.

---
