package constructor;

public class ThisReferenceDemo {
    String name;

    public ThisReferenceDemo(String name) {
        // 'this' refers to the current object instance.
        // We use it here to distinguish between the instance variable 'name' 
        // and the constructor parameter 'name'.
        
        System.out.println("[DEBUG_LOG] Value of 'this': " + this);
        
        // Printing hashcode to show it's the same object
        System.out.println("[DEBUG_LOG] Hashcode of 'this': " + this.hashCode());
        
        this.name = name;
    }

    public void display() {
        System.out.println("Object Name: " + this.name);
    }

    public static void main(String[] args) {
        System.out.println("Creating object t1...");
        ThisReferenceDemo t1 = new ThisReferenceDemo("Object_A");
        System.out.println("Reference t1 points to: " + t1);
        System.out.println("Hashcode of t1: " + t1.hashCode());
        t1.display();

        System.out.println("\n----------------------------\n");

        System.out.println("Creating object t2...");
        ThisReferenceDemo t2 = new ThisReferenceDemo("Object_B");
        System.out.println("Reference t2 points to: " + t2);
        System.out.println("Hashcode of t2: " + t2.hashCode());
        t2.display();
    }
}
