### What is Constructor Chaining?

**Constructor Chaining** is the process of calling one constructor from another constructor within the same class or from a parent class. In Java, this is achieved using the `this()` keyword (for the same class) or `super()` (for parent classes).

---

#### 1. Why use Constructor Chaining?
*   **Avoid Code Duplication:** Instead of writing the same initialization logic in five different constructors, you write it once in a "Master Constructor" and have others call it.
*   **Maintainability:** If you need to change how an object starts up, you only have to change one constructor.
*   **Clarity:** It makes it clear how different constructors relate to each other.

#### 2. Key Rules
1.  **First Statement:** The call to `this()` or `super()` **must** be the very first line in the constructor.
2.  **No Recursion:** You cannot have two constructors call each other (e.g., A calls B, and B calls A). This would cause an infinite loop.
3.  **One call only:** You can only call one other constructor inside a single constructor.

---

#### 3. Code Example Breakdown
I created `ConstructorChainDemo.java` which demonstrates a 4-level chain:

1.  **`ConstructorChainDemo()`** calls...
2.  **`ConstructorChainDemo(name)`** which calls...
3.  **`ConstructorChainDemo(name, id)`** which calls...
4.  **`ConstructorChainDemo(name, id, dept)`** (The Master Constructor).

**Execution Result:**
```text
Master Constructor: Initialization Finished
Two Parameter Constructor: Level 3 complete
Single Parameter Constructor: Level 2 complete
Default Constructor: Level 1 complete
```
*Note: The code finishes executing from the inside out. The "Master" finishes first because it was the last one called in the stack.*

---

#### 4. Summary Table

| Feature | `this()` | `super()` |
| :--- | :--- | :--- |
| **Target** | Constructor in the **same class**. | Constructor in the **parent class**. |
| **Purpose** | Link constructors within one class. | Initialize parent class members. |
| **Position** | Must be the 1st line. | Must be the 1st line. |

*This documentation and the demo code have been added to your project.*
