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

## Constructors (Planned/Discussed)
Based on the project structure, there are references to constructor concepts such as:
- Constructor Chaining
- Shadowing
- `this` Reference
- Variable Defaults
- Final Constructors
