### Why Constructors Don't Have a Return Type

In Java, constructors are special members of a class designed to initialize a new object. Here is a breakdown of why they don't have an explicit return type and how they differ from methods.

#### 1. Implicit Return Type
While we don't explicitly specify a return type (not even `void`), a constructor **implicitly returns the newly created object** of the class. This is the core reason why they are used with the `new` keyword.

#### 2. Distinction from Methods
The absence of a return type is the primary way the Java compiler distinguishes a constructor from a regular method. A method must always have a return type or be declared `void`.

#### 3. Method with the Same Name as Class
It is technically valid in Java to have a method with the same name as the class, but it **must** have a return type. If a return type is present, Java treats it as a method, not a constructor.

**Example:**
```java
public class Employee {
    // Constructor (No return type)
    public Employee() {
        System.out.println("Constructor called");
    }

    // Valid Method (Has return type Employee)
    // Discouraged as it causes confusion
    public Employee Employee() {
        System.out.println("Method called");
        return new Employee();
    }
}
```

#### 4. Comparison Table

| Feature | Constructor | Method |
| :--- | :--- | :--- |
| **Purpose** | Initialize a new object | Perform specific behavior/logic |
| **Return Type** | None (implicitly returns object) | Must have one (e.g., `int`, `void`) |
| **Name** | Must match class name exactly | Can be any valid identifier |
| **Invocation** | Called via `new` keyword | Called via object reference |
| **Inheritance** | Not inherited | Inherited by subclasses |

---
*Generated for storage and quick reference.*
