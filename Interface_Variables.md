### Why Interface Variables are `public static final` by Default

In Java, every variable declared inside an interface is implicitly **`public static final`**, even if you don't type those keywords. Here is the reasoning behind this design choice.

---

#### 1. Why `public`?
*   **Reason:** The purpose of an interface is to define a contract that is accessible to other classes. 
*   **Logic:** If a variable were `private` or `protected`, the classes implementing the interface wouldn't be able to access them, which defeats the purpose of defining them in the "public contract" (the interface).

#### 2. Why `static`?
*   **Reason:** Interfaces cannot be instantiated (you cannot create an object of an interface).
*   **Logic:** Since there is no "object" (instance) of an interface, a variable cannot belong to an instance. Therefore, it must belong to the **class/interface level**, which is what `static` does. You access them via `InterfaceName.VARIABLE_NAME`.

#### 3. Why `final`?
*   **Reason:** Interfaces are meant to define **behavior** (methods), not **state** (data that changes).
*   **Logic:** Java designers wanted to prevent interfaces from becoming "data holders" that maintain a changing state. By making them `final`, they become **constants**. This ensures that the implementing classes share the same unchangeable values.

---

#### Example Breakdown
Even if you write this:
```java
public interface DatabaseConfig {
    int PORT = 3306; // Looks like a normal variable
}
```
The Java Compiler actually sees this:
```java
public interface DatabaseConfig {
    public static final int PORT = 3306;
}
```

#### What happens if you try to change it?
If a class tries to change the value, it will fail to compile:
```java
public class MyServer implements DatabaseConfig {
    void start() {
        // ERROR: Cannot assign a value to final variable 'PORT'
        // PORT = 8080; 
    }
}
```

---

### Summary Table

| Keyword | Why it is the default |
| :--- | :--- |
| **public** | So any class implementing the interface can see/use the constant. |
| **static** | Because interfaces have no objects; constants must exist at the interface level. |
| **final** | To ensure the interface defines a contract, not a changing state. |

*Added to your Java notes repository.*
