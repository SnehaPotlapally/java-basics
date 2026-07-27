package constructor;

public class ConstructorChainDemo {
    String name;
    int id;
    String department;

    // 1. Default Constructor
    public ConstructorChainDemo() {
        // Calling the parameterized constructor using this()
        // This must be the first statement
        this("Unknown Name");
        System.out.println("Default Constructor: Level 1 complete");
    }

    // 2. Constructor with 1 parameter
    public ConstructorChainDemo(String name) {
        // Calling the constructor with 2 parameters
        this(name, 0);
        System.out.println("Single Parameter Constructor: Level 2 complete");
    }

    // 3. Constructor with 2 parameters
    public ConstructorChainDemo(String name, int id) {
        // Calling the constructor with 3 parameters
        this(name, id, "General");
        System.out.println("Two Parameter Constructor: Level 3 complete");
    }

    // 4. The "Master" Constructor (Full parameters)
    public ConstructorChainDemo(String name, int id, String department) {
        // This is the end of the chain (no this() call here)
        this.name = name;
        this.id = id;
        this.department = department;
        System.out.println("Master Constructor: Initialization Finished");
    }

    public void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Dept: " + department);
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Chain from Default Constructor ---");
        ConstructorChainDemo obj = new ConstructorChainDemo();
        obj.display();

        System.out.println("\n--- Starting Chain from Parameterized Constructor ---");
        ConstructorChainDemo obj2 = new ConstructorChainDemo("Alice", 101, "Engineering");
        obj2.display();
    }
}
