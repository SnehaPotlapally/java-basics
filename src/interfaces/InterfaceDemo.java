package interfaces;

/**
 * InterfaceDemo demonstrates the various types of methods allowed in Java interfaces (Java 8+ and 9+).
 */
@FunctionalInterface
public interface InterfaceDemo {

    // 1. Abstract Method (The traditional way / contract)
    // Every class implementing this interface MUST provide an implementation.
    void performAction();

    // 2. Default Method (Introduced in Java 8)
    // Why: To add new functionality to existing interfaces without breaking the classes that already implement them.
    // When: When you want to provide an optional method with a base implementation.
    default void logAction(String message) {
        String fullMessage = formatMessage(message); // Using a private method to avoid duplication
        System.out.println("LOG [Default]: " + fullMessage);
    }

    // 3. Static Method (Introduced in Java 8)
    // Why: For utility methods that belong to the interface itself, not to the instances of implementing classes.
    // When: When the logic is related to the interface but doesn't need any state from an object.
    static void printSystemInfo() {
        printHeader(); // Using a private static method
        System.out.println("Interface System Info: Java Environment");
    }

    // 4. Private Method (Introduced in Java 9)
    // Why: To share code between multiple default methods without exposing it to the outside world.
    // Purpose: Encapsulation and code reuse within the interface.
    private String formatMessage(String msg) {
        return "Action at " + System.currentTimeMillis() + ": " + msg;
    }

    // 5. Private Static Method (Introduced in Java 9)
    // Why: To share code between multiple static methods.
    // Purpose: Similar to private methods, but specifically for static contexts.
    private static void printHeader() {
        System.out.println("--- Interface Utility ---");
    }
}

/**
 * A concrete implementation of the interface.
 */
class BasicImplementation implements InterfaceDemo {
    @Override
    public void performAction() {
        System.out.println("BasicImplementation: Performing a core action.");
    }
}

/**
 * Another implementation that chooses to override the default behavior.
 */
class AdvancedImplementation implements InterfaceDemo {
    @Override
    public void performAction() {
        System.out.println("AdvancedImplementation: Performing a complex action.");
    }

    @Override
    public void logAction(String message) {
        System.out.println("LOG [Overridden]: Advanced log - " + message);
    }
}

/**
 * Main class to demonstrate interface method features.
 */
class Main {
    public static void main(String[] args) {
        System.out.println("=== Interface Method Demonstration ===\n");

        InterfaceDemo basic = new BasicImplementation();
        InterfaceDemo advanced = new AdvancedImplementation();

        // Testing Abstract Methods
        basic.performAction();
        advanced.performAction();

        System.out.println();

        // Testing Default Methods
        basic.logAction("Task Started");    // Uses default implementation
        advanced.logAction("Task Started"); // Uses overridden implementation

        System.out.println();

        // Testing Static Methods
        // Note: Static methods are called on the Interface name, not instances.
        InterfaceDemo.printSystemInfo();
    }
}
