### Why Instance Variables have Defaults but Local Variables Don't?

In Java, there is a clear distinction between variables defined at the class level (Instance) and variables defined inside methods (Local).

---

#### 1. Instance Variables (The "Heap" Memory)
*   **Where they live:** They are stored on the **Heap** as part of an object.
*   **Why defaults?** When you use `new MyClass()`, Java allocates a chunk of memory for the whole object. To ensure the object is in a "clean" and predictable state, Java zeros out that memory. 
*   **Safety:** It prevents the object from containing "garbage data" left over from previous memory usage.
*   **Defaults:** `0` for numbers, `false` for booleans, and `null` for objects/references.

#### 2. Local Variables (The "Stack" Memory)
*   **Where they live:** They are stored on the **Stack**.
*   **Why NO defaults?**
    1.  **Performance:** Methods are called thousands of times per second. Automatically zeroing out every local variable would slow down the program.
    2.  **Logic Safety:** Local variables are usually used for temporary logic. If Java gave them a default (like `0`), you might accidentally use that `0` without realizing you forgot to calculate the real value. By forcing you to initialize it, the compiler helps you catch bugs early.
*   **Rule:** You can declare them without a value, but you **cannot read** them until you assign a value.

---

#### Comparison Table

| Feature | Instance Variables | Local Variables |
| :--- | :--- | :--- |
| **Location** | Heap (Inside Object) | Stack (Inside Method) |
| **Default Value** | Yes (0, false, null) | No (None) |
| **Compiler Check** | None (Uses default) | Error if used before assignment |
| **Lifecycle** | Exists as long as the object | Exists only while method runs |

---

#### How to see the Error
If you try to run this code:
```java
public void myMethod() {
    int x; 
    System.out.println(x); // ERROR!
}
```
The Java compiler will stop you with: `variable x might not have been initialized`.
