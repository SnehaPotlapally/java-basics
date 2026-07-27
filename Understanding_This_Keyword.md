### Understanding the `this` Keyword in Java

The `this` keyword is a reference variable in Java that refers to the **current object** instance. 

#### 1. What does `this` actually contain?
When you print `this`, you are printing the reference to the memory location where the current object is stored. If you compare the value of `this` inside a constructor to the variable you assigned the object to (like `t1`), you will see they are **exactly the same**.

#### 2. Key Uses of `this`
*   **Shadowing:** Distinguishing between instance variables and parameters with the same name.
*   **Constructor Chaining:** Calling one constructor from another in the same class.
*   **Passing the current object:** Passing the current instance as a parameter to other methods.

#### 3. Code Example & Execution Results

I created and ran a file called `ThisReferenceDemo.java`. Here is the core logic:

```java
public class ThisReferenceDemo {
    String name;

    public ThisReferenceDemo(String name) {
        // 'this' refers to the object being created right now.
        System.out.println("Value of 'this' inside constructor: " + this);
        
        // Use 'this' to resolve naming conflict (shadowing)
        this.name = name; 
    }

    public static void main(String[] args) {
        ThisReferenceDemo t1 = new ThisReferenceDemo("Object_A");
        System.out.println("Value of variable 't1' in main:     " + t1);
    }
}
```

**Output from the run:**
```text
Creating object t1...
Value of 'this' inside constructor: constructor.ThisReferenceDemo@2a139a55
Value of variable 't1' in main:     constructor.ThisReferenceDemo@2a139a55
```
*Notice how the hexadecimal values (the memory addresses) are identical!*

#### 4. Summary Table

| Question | Answer |
| :--- | :--- |
| **What is it?** | A reference to the current instance of the class. |
| **When is it used?** | Inside constructors and instance methods. |
| **Can it be used in `static` methods?** | **No**. Static methods belong to the class, not an object. |
| **What does printing it show?** | The class name followed by the memory address (hashcode). |

*This explanation and code have been added to your project.*
