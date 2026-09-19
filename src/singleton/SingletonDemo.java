package singleton;

/**
 * 1. Eager Initialization
 * The instance is created at the time of class loading.
 * Pros: Simple, Thread-safe.
 * Cons: Instance is created even if not used (waste of memory if heavy).
 */
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() {}
    public static EagerSingleton getInstance() {
        return instance;
    }
}

/**
 * 2. Lazy Initialization
 * Instance created only when requested.
 * Pros: Saves memory if not used.
 * Cons: NOT Thread-safe. Two threads could create two instances simultaneously.
 */
class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton() {}
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * 3. Synchronized Method (Thread-Safe)
 * Pros: Thread-safe.
 * Cons: Performance hit due to 'synchronized' keyword on every call.
 */
class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    private ThreadSafeSingleton() {}
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}

/**
 * 4. Double-Checked Locking
 * Uses 'volatile' to ensure visibility across threads and double check for performance.
 * Pros: Better performance than synchronized method.
 * Cons: Slightly complex; older Java versions (pre-1.5) had issues with memory models.
 */
class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;
    private DoubleCheckedLockingSingleton() {}
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 5. Bill Pugh Singleton (Static Inner Class)
 * Uses the Java ClassLoader mechanism for thread safety and lazy loading.
 * Pros: Highly efficient, lazy, and thread-safe without synchronization.
 * Cons: Vulnerable to Reflection and Serialization attacks.
 */
class BillPughSingleton {
    private BillPughSingleton() {}
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

/**
 * 6. Enum Singleton
 * Pros: 
 * - Simplest to write.
 * - Thread-safe by default.
 * - Protection against Reflection (Java prevents reflective instantiation of Enums).
 * - Handles Serialization automatically.
 * Cons: Cannot extend another class (Enums extend Enum class by default).
 */
enum EnumSingleton {
    INSTANCE;
    
    // You can add methods here
    public void doSomething() {
        System.out.println("Enum Singleton is doing something!");
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        // Accessing Enum Singleton
        EnumSingleton instance = EnumSingleton.INSTANCE;
        instance.doSomething();
        
        System.out.println("Hashcode of instance: " + instance.hashCode());
        
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println("Hashcode of instance2: " + instance2.hashCode());
        
        System.out.println("Are they same? " + (instance == instance2));
    }
}
