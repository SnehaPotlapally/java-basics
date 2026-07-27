package constructor;

public class ShadowingDemo {
    // Instance variable
    int id = 100;

    // 1. Example where 'this.id' is MANDATORY (Shadowing)
    public void setIdShadowed(int id) {
        // Here, 'id' refers to the parameter, NOT the instance variable.
        // If we do: id = id; 
        // It just assigns the parameter value back to the parameter.
        
        System.out.println("[BEFORE] Instance id: " + this.id);
        System.out.println("[BEFORE] Parameter id: " + id);
        
        this.id = id; // MANDATORY use of 'this' to reach the instance variable
        
        System.out.println("[AFTER] Instance id is now: " + this.id);
    }

    // 2. Example where 'this.id' is OPTIONAL (Different names)
    public void setIdExplicit(int newId) {
        // Since 'newId' and 'id' are different names, there is no shadowing.
        // 'id' and 'this.id' will both refer to the instance variable.
        
        id = newId; // This works fine without 'this.'
        System.out.println("Instance id updated via setIdExplicit to: " + id);
    }

    public void display() {
        // Here, 'id' and 'this.id' are identical because no local variable 'id' exists.
        System.out.println("Displaying id: " + id + " (same as this.id: " + this.id + ")");
    }

    public static void main(String[] args) {
        ShadowingDemo demo = new ShadowingDemo();

        System.out.println("--- Scenario 1: Shadowing ---");
        // We pass 500, trying to update the instance id (100)
        demo.setIdShadowed(500);

        System.out.println("\n--- Scenario 2: No Shadowing ---");
        demo.setIdExplicit(999);
        
        System.out.println("\n--- Final State ---");
        demo.display();
    }
}
