# Java Concepts

## Enums

Enums (Enumerations) in Java are a special data type that enables for a variable to be a set of predefined constants.

### Key Methods in Enums
- `name()`: Returns the name of this enum constant, exactly as declared in its enum declaration.
- `valueOf(String name)`: Returns the enum constant of the specified enum type with the specified name. The name must match exactly.
- `values()`: Returns an array containing the constants of this enum type, in the order they are declared.
- `ordinal()`: Returns the ordinal of this enumeration constant (its position in its enum declaration, where the initial constant is assigned an ordinal of zero).

### Example from `EnumSample.java`
```java
public enum EnumSample {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

## Control Flow

### Enhanced For-Loop (For-Each)

The enhanced for-loop (introduced in Java 5) provides a simpler way to iterate over **arrays** and **collections**.

**Syntax:**
```java
for (Type variable : source) {
    // Body of the loop
}
```

**What can be the `source`?**
The source doesn't always have to be an array. It can be:
1.  **Arrays**: Any array type (e.g., `int[]`, `String[]`, `EnumSample[]`).
2.  **Iterable Objects**: Any class that implements the `java.lang.Iterable` interface. This includes all Java Collections:
    - `List` (e.g., `ArrayList`, `LinkedList`)
    - `Set` (e.g., `HashSet`, `TreeSet`)
    - `Queue`, etc.

**How it works:**
1. **Type**: The data type of the elements in the source.
2. **variable**: A temporary variable that holds the current element in each iteration.
3. **source**: The array or `Iterable` object you are iterating over.

**Usage with Enums:**
Since `Enum.values()` returns an array, the enhanced for-loop is the standard way to process every constant in an enum.

**Example from `EnumSample.java`:**
```java
for (EnumSample sample : EnumSample.values()) {
    System.out.println("Ordinal of " + sample.name() + " is " + sample.ordinal());
}
```

**Advantages:**
- **Readability**: The code is cleaner and easier to read.
- **Safety**: Eliminates the risk of "Off-by-one" errors common with index-based loops (`i < length`).
- **Simplicity**: No need to manage a counter or index variable.

## Singleton Pattern

The Singleton pattern ensures that a class has only one instance and provides a global point of access to it.

### Common Implementation Methods

1.  **Eager Initialization**: Instance created at class loading. Simple but might waste memory.
2.  **Lazy Initialization**: Instance created only when needed. Not thread-safe.
3.  **Thread-Safe Singleton**: Uses `synchronized` on the `getInstance()` method. Slow due to locking overhead.
4.  **Double-Checked Locking**: Uses `volatile` and `synchronized` block for better performance. Complex.
5.  **Bill Pugh Singleton**: Uses a static inner class. Efficient and thread-safe without explicit synchronization.
6.  **Enum Singleton**: The most robust way.

### Why Enum Singleton is Better?

-   **Reflection Safety**: In other methods, a second instance can be created via Reflection (by changing constructor visibility). Java strictly prevents this for Enums.
-   **Serialization**: Enums handle serialization automatically, ensuring the singleton property even when deserialized.
-   **Thread Safety**: Enums are thread-safe by design (guaranteed by the JVM).
-   **Simplicity**: The most concise way to implement a Singleton.

**Example:**
```java
public enum Singleton {
    INSTANCE;
    public void doWork() { ... }
}
```

## Immutability in Java

An immutable class is a class whose instances cannot be modified after they are created. Examples in Java include `String`, `Integer`, and `LocalDate`.

### Why Use Immutable Classes?
1.  **Thread Safety**: Since the state cannot change, multiple threads can access the object simultaneously without synchronization.
2.  **Caching/Security**: Values like passwords or database URLs are safe from unexpected changes.
3.  **Key in Maps**: Immutable objects are perfect keys for `HashMap` or `HashSet`.

### Rules to Create an Immutable Class
1.  **Declare the class as `final`**: Prevents inheritance (which could allow overriding methods to change state).
2.  **Private and Final fields**: Fields cannot be accessed directly or changed after initialization.
3.  **No Setters**: Don't provide methods that modify the state.
4.  **Defensive Copying**: If a field is a mutable object (like `Date` or `ArrayList`), you must:
    -   Create a copy in the **Constructor** before saving it.
    -   Return a copy in the **Getter** instead of the original reference.

**Example:**
```java
public final class MyImmutable {
    private final List<String> data;
    public MyImmutable(List<String> input) {
        this.data = new ArrayList<>(input); // Copy!
    }
    public List<String> getData() {
        return new ArrayList<>(data); // Copy!
    }
}
```

## Constructors (Planned/Discussed)
Based on the project structure, there are references to constructor concepts such as:
- Constructor Chaining
- Shadowing
- `this` Reference
- Variable Defaults
- Final Constructors
