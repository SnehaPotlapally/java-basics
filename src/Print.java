public class Print {
    // This field is of type Object, which means it can store ANY Java object
    Object value;

    // Setter method that accepts an Object
    public void setPrintValue(Object value) {
        System.out.println("[DEBUG_LOG] Storing value: " + value + " (Type: " + value.getClass().getSimpleName() + ")");
        this.value = value;
    }

    // Getter method that returns an Object
    public Object getPrintValue() {
        return value;
    }

    public static void main(String[] args) {
        // 1. Create an instance of our Print class
        Print printObj1 = new Print();

        // 2. Set the value to 1 (Primitive 1 is AUTOBOXED to an Integer Object)
        System.out.println("[DEBUG_LOG] Calling setPrintValue(1)...");
        printObj1.setPrintValue(1); 

        // 3. Get the value back as a generic Object reference
        Object printValue = printObj1.getPrintValue();
        System.out.println("[DEBUG_LOG] printValue reference type is Object. Value: " + printValue);

        // 4. Check the value using Typecasting and Unboxing
        // (int)printValue -> UNBOXES the Integer Object back to a primitive int
        if ((int)printValue == 1) {
            System.out.println("Success! The value is 1.");
        } else {
            System.out.println("The value is not 1.");
        }
        
        // 5. Demonstrating flexibility: The same class can hold a String!
        System.out.println("\n[DEBUG_LOG] Demonstrating flexibility...");
        printObj1.setPrintValue("Hello Java");
        Object newResult = printObj1.getPrintValue();
        
        // To use String methods, we must cast to String
        String upper = ((String)newResult).toUpperCase();
        System.out.println("Casted to String: " + upper);
    }
}
