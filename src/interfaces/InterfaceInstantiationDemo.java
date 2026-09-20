package interfaces;

/**
 * InterfaceInstantiationDemo provides a detailed, step-by-step demonstration of:
 * 1. Why Java interfaces CANNOT be directly instantiated.
 * 2. How Concrete Classes, Anonymous Inner Classes, and Lambda Expressions work.
 * 3. What the compiler actually generates behind the scenes for Anonymous Inner Classes.
 * 4. Runtime reflection proof examining the generated classes.
 */
public class InterfaceInstantiationDemo {

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("  1. WHY DIRECT INSTANTIATION OF AN INTERFACE IS NOT ALLOWED    ");
        System.out.println("================================================================");
        /*
         * Attempting to instantiate an interface directly results in a COMPILE ERROR:
         *
         *   InterfaceDemo demo = new InterfaceDemo(); // ❌ COMPILE ERROR!
         *   // Error: 'InterfaceDemo' is abstract; cannot be instantiated
         *
         * Reason: Interfaces lack constructors and concrete method bodies (for abstract methods),
         * so the JVM cannot create an object that has no complete implementation.
         */
        System.out.println("• 'new InterfaceDemo()' is forbidden by the compiler because interfaces");
        System.out.println("  are abstract contracts and do not have constructors or complete state.\n");

        System.out.println("================================================================");
        System.out.println("  2. APPROACH 1: CONCRETE IMPLEMENTING CLASS                    ");
        System.out.println("================================================================");
        /*
         * Standard approach: Define a named class that implements the interface.
         * Best for reusable, system-wide behavior.
         */
        InterfaceDemo concreteInstance = new BasicImplementation();
        concreteInstance.performAction();
        concreteInstance.logAction("Invoked via Concrete Class");
        System.out.println("  -> Actual Class: " + concreteInstance.getClass().getName());
        System.out.println();

        System.out.println("================================================================");
        System.out.println("  3. APPROACH 2: ANONYMOUS INNER CLASS (BEHIND THE SCENES)      ");
        System.out.println("================================================================");
        /*
         * When you write: new InterfaceDemo() { ... }
         *
         * Crucial Concept:
         * You are NOT instantiating the interface directly!
         * The opening brace '{' starts the definition of an unnamed (anonymous) class.
         *
         * What the Java Compiler does behind the scenes:
         * 1. Creates a hidden synthetic class (e.g., InterfaceInstantiationDemo$1):
         *      class InterfaceInstantiationDemo$1 implements InterfaceDemo {
         *          @Override
         *          public void performAction() { ... }
         *      }
         * 2. Instantiates that hidden class:
         *      InterfaceDemo anonymousInstance = new InterfaceInstantiationDemo$1();
         */
        InterfaceDemo anonymousInstance = new InterfaceDemo() {
            @Override
            public void performAction() {
                System.out.println("• [Anonymous Class] Executing task-specific custom logic.");
            }
        };

        anonymousInstance.performAction();
        anonymousInstance.logAction("Invoked via Anonymous Inner Class");

        // Runtime Proof
        System.out.println("\n[Runtime Inspection of Anonymous Class]");
        System.out.println("  • Object Class Name       : " + anonymousInstance.getClass().getName());
        System.out.println("  • Is it an Interface?     : " + anonymousInstance.getClass().isInterface());
        System.out.println("  • Is it an Anonymous Class: " + anonymousInstance.getClass().isAnonymousClass());
        System.out.println("  • instanceof InterfaceDemo: " + (anonymousInstance instanceof InterfaceDemo));
        System.out.println();

        System.out.println("================================================================");
        System.out.println("  4. APPROACH 3: LAMBDA EXPRESSION (FUNCTIONAL INTERFACES)       ");
        System.out.println("================================================================");
        /*
         * Since InterfaceDemo has only one abstract method (performAction),
         * it is a Functional Interface (Single Abstract Method - SAM).
         *
         * Lambda expressions eliminate boilerplate while preserving functionality.
         */
        InterfaceDemo lambdaInstance = () -> System.out.println("• [Lambda] Executing concise functional logic.");

        lambdaInstance.performAction();
        lambdaInstance.logAction("Invoked via Lambda");

        // Runtime Proof
        System.out.println("\n[Runtime Inspection of Lambda Expression]");
        System.out.println("  • Object Class Name       : " + lambdaInstance.getClass().getName());
        System.out.println("  • Is it an Interface?     : " + lambdaInstance.getClass().isInterface());
        System.out.println("  • instanceof InterfaceDemo: " + (lambdaInstance instanceof InterfaceDemo));
        System.out.println();
    }
}
