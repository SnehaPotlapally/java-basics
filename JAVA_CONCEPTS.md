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

## Constructors (Planned/Discussed)
Based on the project structure, there are references to constructor concepts such as:
- Constructor Chaining
- Shadowing
- `this` Reference
- Variable Defaults
- Final Constructors
