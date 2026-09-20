import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("       JAVA REFLECTION COMPREHENSIVE DEMONSTRATION        ");
        System.out.println("==========================================================\n");

        demonstrateClassObjects();
        demonstrateMethodInspectionAndInvocation();
        demonstrateFieldInspectionAndMutation();
        demonstrateConstructorAndBreakingSingleton();
    }

    /**
     * 1. How to get a Class object in Java (3 primary ways)
     */
    private static void demonstrateClassObjects() {
        System.out.println("----------------------------------------------------------");
        System.out.println("1. THREE WAYS TO OBTAIN A CLASS OBJECT");
        System.out.println("----------------------------------------------------------");

        // Way 1: Using .class literal (Compile-time type token)
        Class<Eagle> class1 = Eagle.class;
        System.out.println("Way 1 (.class)         : " + class1.getName());

        // Way 2: Using .getClass() on an existing instance (Runtime inspection)
        Eagle eagleInstance = new Eagle("Golden Eagle", 220);
        Class<? extends Eagle> class2 = eagleInstance.getClass();
        System.out.println("Way 2 (.getClass())    : " + class2.getName());

        // Way 3: Using Class.forName() with fully-qualified class name (Dynamic loading)
        try {
            Class<?> class3 = Class.forName("Eagle");
            System.out.println("Way 3 (Class.forName)  : " + class3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // All three refer to the exact same Class object in JVM memory
        System.out.println("Are all class tokens identical in memory? " + (class1 == class2));
        System.out.println();
    }

    /**
     * 2. Inspecting and Invoking Methods dynamically
     */
    private static void demonstrateMethodInspectionAndInvocation() {
        System.out.println("----------------------------------------------------------");
        System.out.println("2. INSPECTING & INVOKING METHODS VIA REFLECTION");
        System.out.println("----------------------------------------------------------");

        Class<Eagle> eagleClass = Eagle.class;

        // getMethods() returns all public methods (including inherited from Bird and Object)
        System.out.println("[getMethods()] - All public methods (including inherited):");
        for (Method m : eagleClass.getMethods()) {
            if (m.getDeclaringClass() != Object.class) { // filter out Object methods for brevity
                System.out.println(" - " + m.getName() + " (Declared by: " + m.getDeclaringClass().getSimpleName() + ")");
            }
        }

        // getDeclaredMethods() returns ALL methods declared specifically in Eagle (public & private)
        System.out.println("\n[getDeclaredMethods()] - Declared directly in Eagle:");
        for (Method m : eagleClass.getDeclaredMethods()) {
            System.out.println(m);
            System.out.println(m.getModifiers());
            System.out.println(" - " + Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName());
            System.out.println("++++");
        }

        // Dynamic Method Invocation:
        System.out.println("\n[Dynamic Method Invocation]:");
        try {
            Eagle eagle = new Eagle("Bald Eagle", 230);

            // A. Invoking a public method with no arguments
            Method flyMethod = eagleClass.getMethod("fly");
            System.out.print("Invoking fly(): ");
            flyMethod.invoke(eagle);

            // B. Invoking a public method with parameters
            Method huntMethod = eagleClass.getMethod("hunt", String.class);
            System.out.print("Invoking hunt(\"Salmon\"): ");
            huntMethod.invoke(eagle, "Salmon");

            // C. Invoking a PRIVATE method (nest) using setAccessible(true)
            Method nestMethod = eagleClass.getDeclaredMethod("nest");
            nestMethod.setAccessible(true); // Suppresses Java language access control checks
            System.out.print("Invoking private nest(): ");
            nestMethod.invoke(eagle);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * 3. Inspecting and Mutating Fields (including private fields)
     */
    private static void demonstrateFieldInspectionAndMutation() {
        System.out.println("----------------------------------------------------------");
        System.out.println("3. INSPECTING & MUTATING FIELDS (INCLUDING PRIVATE FIELDS)");
        System.out.println("----------------------------------------------------------");

        try {
            Eagle eagle = new Eagle("Crown Eagle", 180);
            Class<?> eagleClass = eagle.getClass();

            // A. Public field manipulation
            Field wingspanField = eagleClass.getField("wingspan");
            System.out.println("Initial public 'wingspan' value: " + wingspanField.get(eagle));
            wingspanField.set(eagle, 195);
            System.out.println("Updated public 'wingspan' value: " + wingspanField.get(eagle));

            // B. Private field access and mutation
            Field nameField = eagleClass.getDeclaredField("eagleName");
            nameField.setAccessible(true); // Bypass 'private' access modifier

            String oldName = (String) nameField.get(eagle);
            System.out.println("Initial private 'eagleName' value: " + oldName);

            // Mutate the private field
            nameField.set(eagle, "Harpy Eagle");
            System.out.println("Updated private 'eagleName' value: " + nameField.get(eagle));

            // Verify that the object's behavior reflects the mutated state
            System.out.print("Calling fly() after mutation: ");
            eagle.fly();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    /**
     * 4. Constructor Reflection and Breaking the Singleton Pattern
     */
    private static void demonstrateConstructorAndBreakingSingleton() {
        System.out.println("----------------------------------------------------------");
        System.out.println("4. CONSTRUCTOR REFLECTION & BREAKING THE SINGLETON PATTERN");
        System.out.println("----------------------------------------------------------");

        try {
            // Standard Singleton instantiation
            ClassicSingleton instance1 = ClassicSingleton.getInstance();
            ClassicSingleton instance2 = ClassicSingleton.getInstance();

            System.out.println("Instance 1 HashCode: " + instance1.hashCode());
            System.out.println("Instance 2 HashCode: " + instance2.hashCode());
            System.out.println("Are instance1 and instance2 the same? " + (instance1 == instance2));

            // Circumventing the Singleton via Constructor Reflection
            System.out.println("\n[Attempting to break Singleton via Reflection...]");
            Constructor<ClassicSingleton> constructor = ClassicSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true); // Suppress private constructor restriction

            // Instantiating a new object directly through reflection
            ClassicSingleton instance3 = constructor.newInstance();

            System.out.println("Instance 3 (Created via reflection) HashCode: " + instance3.hashCode());
            System.out.println("Are instance1 and instance3 the same? " + (instance1 == instance3));
            System.out.println("-> Singleton pattern is BROKEN: multiple instances exist in JVM!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
