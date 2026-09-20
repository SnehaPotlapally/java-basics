public class ClassicSingleton {
    private static ClassicSingleton instance;

    // Private constructor prevents direct external instantiation
    private ClassicSingleton() {
        System.out.println("ClassicSingleton: Private constructor invoked!");
    }

    public static synchronized ClassicSingleton getInstance() {
        if (instance == null) {
            instance = new ClassicSingleton();
        }
        return instance;
    }
}
