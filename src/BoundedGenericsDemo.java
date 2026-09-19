import java.util.ArrayList;
import java.util.List;

// Simple Entity Hierarchy
class BaseEntity {
    public void logId() { System.out.println("Logging Entity ID..."); }
}

class User extends BaseEntity {}
class Admin extends User {}

public class BoundedGenericsDemo {

    /**
     * UPPER BOUNDED WILDCARD (? extends T)
     * "Read-only" mode. We can accept a list of Users, or any subclass (like Admin).
     */
    public void processUsers(List<? extends User> users) {
        System.out.println("Processing users (Upper Bound)...");
        for (User u : users) {
            u.logId(); // Safe to read as User
        }
        // users.add(new User()); // ERROR: Cannot add to an Upper Bound list!
    }

    /**
     * LOWER BOUNDED WILDCARD (? super T)
     * "Write-only" mode. We can accept a list of Users, or any superclass (like BaseEntity or Object).
     */
    public void addUsers(List<? super User> userList) {
        System.out.println("Adding users (Lower Bound)...");
        userList.add(new User());  // Safe to add a User
        userList.add(new Admin()); // Safe to add a subclass
    }

    public static void main(String[] args) {
        BoundedGenericsDemo demo = new BoundedGenericsDemo();

        // 1. Upper Bound Example: List of Admins can be treated as List of Users
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin());
        demo.processUsers(admins);

        // 2. Lower Bound Example: List of BaseEntities can receive a User
        List<BaseEntity> entities = new ArrayList<>();
        demo.addUsers(entities);
        System.out.println("Entities list size: " + entities.size());
    }
}
