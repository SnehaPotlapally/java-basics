package constructor;

public class ConstructorDemo {
    public static void main(String[] args) {
        // Using Default Constructor
        Student s1 = new Student();
        s1.display();

        System.out.println();

        // Using Parameterized Constructor
        Student s2 = new Student("Alice", 101);
        s2.display();
    }
}
