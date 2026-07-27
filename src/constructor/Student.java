package constructor;

public class Student {
    String name;
    int rollNo;

    // Default Constructor
    public Student() {
        System.out.println("Default Constructor called");
        this.name = "Unknown";
        this.rollNo = 0;
    }

    // Parameterized Constructor
    public Student(String name, int rollNo) {
        System.out.println("Parameterized Constructor called");
        this.name = name;
        this.rollNo = rollNo;
    }

    public void display() {
        System.out.println("Name: " + name + ", Roll No: " + rollNo);
    }
}
