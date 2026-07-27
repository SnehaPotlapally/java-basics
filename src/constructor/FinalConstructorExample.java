package constructor;

public class FinalConstructorExample {
    // Attempting to declare a final constructor
    // This will cause a compilation error: 'modifier final not allowed here'
    /*
    public final FinalConstructorExample() {
        System.out.println("This will not compile!");
    }
    */

    public FinalConstructorExample() {
        System.out.println("Normal constructor works fine.");
    }

    public static void main(String[] args) {
        new FinalConstructorExample();
    }
}
