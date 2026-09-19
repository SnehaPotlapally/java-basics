public class GenericMethodDemo {

    /**
     * This is a generic method. 
     * The type parameters <K, V> are defined BEFORE the return type (void).
     * This makes only this method generic, not the whole class.
     */
    public <K, V> void printValue(Pair<K, V> pair1, Pair<K, V> pair2) {
        System.out.println("Checking pairs...");
        System.out.println("Pair 1: " + pair1);
        System.out.println("Pair 2: " + pair2);

        // Accessing methods from the generic type
        if (pair1.getKey().equals(pair2.getKey())) {
            System.out.println("Success: Both pairs have the same key: " + pair1.getKey());
        } else {
            System.out.println("Notice: The keys are different.");
        }
    }

    public static void main(String[] args) {
        GenericMethodDemo demo = new GenericMethodDemo();

        // Example 1: Using Integer keys and String values
        Pair<Integer, String> p1 = new Pair<>(101, "Apple");
        Pair<Integer, String> p2 = new Pair<>(101, "Banana");
        
        System.out.println("--- Test Case 1 (Integer keys) ---");
        demo.printValue(p1, p2);

        // Example 2: Using String keys and Integer values
        Pair<String, Integer> p3 = new Pair<>("ID_A", 50);
        Pair<String, Integer> p4 = new Pair<>("ID_B", 60);

        System.out.println("\n--- Test Case 2 (String keys) ---");
        demo.printValue(p3, p4);
        
        // Example 3: Different types for K and V
        Pair<Double, Boolean> p5 = new Pair<>(3.14, true);
        Pair<Double, Boolean> p6 = new Pair<>(3.14, false);

        System.out.println("\n--- Test Case 3 (Double keys) ---");
        demo.printValue(p5, p6);
    }
}
