package enums;

public enum EnumSample {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

class Main {
    public static void main(String[] args) {
        // 1. name() example: returns the exact name of the constant as a String
        EnumSample day = EnumSample.MONDAY;
        String nameValue = day.name(); 
        System.out.println("Using name(): " + nameValue);

        // 2. valueOf(String) example: converts a String back into an Enum constant
        // Note: The string must match EXACTLY (case-sensitive)
        String input = "FRIDAY";
        EnumSample parsedDay = EnumSample.valueOf(input);
        System.out.println("Using valueOf(): Found constant " + parsedDay + " with ordinal " + parsedDay.ordinal());

        // Demonstration of all constants using values() and printing ordinal()
        System.out.println("\nIterating through EnumSample.values():");
        for (EnumSample sample : EnumSample.values()) {
            System.out.println(EnumSample.values() + " = " + sample);
            System.out.println("Ordinal of " + sample.name() + " is " + sample.ordinal());
        }
    }
}
