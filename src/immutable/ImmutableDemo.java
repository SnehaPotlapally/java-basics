package immutable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Rules for Immutability:
 * 1. Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
 * 2. Make all fields final and private.
 * 3. Don't allow subclasses to override methods (Make the class 'final').
 * 4. Special Rule: If the instance fields include references to mutable objects, 
 *    don't allow those objects to be changed:
 *    - Don't provide methods that modify the mutable objects.
 *    - Don't share references to the mutable objects (Defensive Copying).
 */
final class ImmutableUser {
    private final String name; // Immutable (String)
    private final Date joiningDate; // Mutable (Date)
    private final List<String> permissions; // Mutable (List)

    public ImmutableUser(String name, Date joiningDate, List<String> permissions) {
        this.name = name;
        
        // Defensive Copying in Constructor
        // We don't store the original joiningDate reference because the caller could change it later.
        this.joiningDate = new Date(joiningDate.getTime());
        
        // Defensive Copying for List
        this.permissions = new ArrayList<>(permissions);
    }

    public String getName() {
        return name;
    }

    // Defensive Copying in Getter
    public Date getJoiningDate() {
        // Return a copy, NOT the original reference
        return new Date(joiningDate.getTime());
    }

    // Defensive Copying in Getter
    public List<String> getPermissions() {
        // Return a copy so the caller can't modify the internal list
        return new ArrayList<>(permissions);
    }

    @Override
    public String toString() {
        return "User: " + name + ", Joined: " + joiningDate + ", Permissions: " + permissions;
    }
}

public class ImmutableDemo {
    public static void main(String[] args) {
        String name = "Junie";
        Date date = new Date();
        List<String> perms = new ArrayList<>();
        perms.add("READ");

        ImmutableUser user = new ImmutableUser(name, date, perms);
        System.out.println("Original: " + user);

        // Attempting to break immutability via the original objects
        date.setTime(0); // Changing the external date
        perms.add("WRITE"); // Changing the external list
        
        System.out.println("After external change attempt: " + user);
        
        // Attempting to break immutability via getters
        List<String> userPerms = user.getPermissions();
        userPerms.add("ADMIN");
        
        System.out.println("After getter modification attempt: " + user);
    }
}
