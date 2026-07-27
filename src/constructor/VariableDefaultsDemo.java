package constructor;

public class VariableDefaultsDemo {
    // Instance variables (allocated on the HEAP)
    // Java automatically initializes these to default values.
    int instanceInt;
    boolean instanceBool;
    String instanceRef;

    public void showDefaults() {
        System.out.println("--- Instance Variables (Automatic Defaults) ---");
        System.out.println("int: " + instanceInt);      // 0
        System.out.println("boolean: " + instanceBool); // false
        System.out.println("String: " + instanceRef);   // null
    }

    public void localVariableExample() {
        // Local variables (allocated on the STACK)
        // Java does NOT give these default values.
        int localInt;
        
        System.out.println("\n--- Local Variables ---");
        
        // UNCOMMENTING the line below will cause a COMPILE ERROR:
        // "variable localInt might not have been initialized"
        // System.out.println(localInt); 
        
        localInt = 50; // You must assign a value manually before using it
        System.out.println("localInt after manual assignment: " + localInt);
    }

    public static void main(String[] args) {
        VariableDefaultsDemo demo = new VariableDefaultsDemo();
        demo.showDefaults();
        demo.localVariableExample();
    }
}
