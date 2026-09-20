package interfaces;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * FunctionalInterfacesDemo demonstrates the core Java functional interfaces:
 * 1. Predicate<T>    : boolean test(T t)   -> Evaluates conditions / filters
 * 2. Consumer<T>     : void accept(T t)     -> Executes actions / side-effects
 * 3. Supplier<T>     : T get()              -> Supplies / generates values lazily
 * 4. Function<T, R>  : R apply(T t)         -> Transforms input of type T to output of type R
 *
 * Each section includes:
 * - Pure Java standard usage
 * - Method composition / chaining (and, or, negate, andThen, compose)
 * - Practical Spring Boot architecture simulations (JPA specs, Kafka consumers, Optional lazy loading, DTO mapping)
 */
public class FunctionalInterfacesDemo {

    public static void main(String[] args) {
        demonstratePredicate();
        demonstrateConsumer();
        demonstrateSupplier();
        demonstrateFunction();
        demonstrateSpringWorkflowIntegration();
    }

    // =========================================================================
    // 1. PREDICATE<T> -> boolean test(T t)
    // =========================================================================
    public static void demonstratePredicate() {
        System.out.println("================================================================");
        System.out.println("  1. PREDICATE<T> : boolean test(T t)                           ");
        System.out.println("================================================================");
        System.out.println("Role: Takes an input and returns true/false. Ideal for filters & validations.\n");

        // Simple Predicates
        Predicate<Integer> isAdult = age -> age >= 18;
        Predicate<String> isValidEmail = email -> email != null && email.contains("@");

        System.out.println("[Simple Checks]");
        System.out.println("• Is age 21 adult? " + isAdult.test(21)); // true
        System.out.println("• Is age 15 adult? " + isAdult.test(15)); // false
        System.out.println("• Is 'john@example.com' valid? " + isValidEmail.test("john@example.com")); // true
        System.out.println("• Is 'invalid-email' valid? " + isValidEmail.test("invalid-email"));       // false

        // Combining Predicates: and(), or(), negate()
        Predicate<Integer> isSenior = age -> age >= 60;
        Predicate<Integer> eligibleForSeniorDiscount = isAdult.and(isSenior);
        Predicate<Integer> isMinor = isAdult.negate();

        System.out.println("\n[Composed Predicates (and / negate)]");
        System.out.println("• Is 65 eligible for senior discount? " + eligibleForSeniorDiscount.test(65)); // true
        System.out.println("• Is 30 eligible for senior discount? " + eligibleForSeniorDiscount.test(30)); // false
        System.out.println("• Is 15 a minor? " + isMinor.test(15));                                         // true

        // Spring Boot Real-World Context: Dynamic filtering / JPA Specification style
        List<User> users = List.of(
                new User(1L, "Alice", "alice@example.com", 25, "ACTIVE", "ADMIN"),
                new User(2L, "Bob", "bob@example.com", 17, "INACTIVE", "USER"),
                new User(3L, "Charlie", "charlie@domain.com", 35, "ACTIVE", "USER")
        );

        Predicate<User> isActiveUser = user -> "ACTIVE".equalsIgnoreCase(user.status());
        Predicate<User> isAdmin = user -> "ADMIN".equalsIgnoreCase(user.role());
        Predicate<User> activeAdminSpec = isActiveUser.and(isAdmin);

        System.out.println("\n[Spring Boot Simulated Use Case: JPA Specification / Filtering]");
        users.stream()
                .filter(activeAdminSpec)
                .forEach(user -> System.out.println("  • Found Active Admin: " + user.name()));
        System.out.println();
    }

    // =========================================================================
    // 2. CONSUMER<T> -> void accept(T t)
    // =========================================================================
    public static void demonstrateConsumer() {
        System.out.println("================================================================");
        System.out.println("  2. CONSUMER<T> : void accept(T t)                             ");
        System.out.println("================================================================");
        System.out.println("Role: Takes an input, performs an action/side-effect, returns void.\n");

        // Basic Consumer
        Consumer<String> consoleLogger = msg -> System.out.println("[LOG] " + msg);
        consoleLogger.accept("Application started successfully.");

        // Chaining Consumers with andThen()
        Consumer<String> auditLogger = msg -> System.out.println("[AUDIT DB] Storing audit record: " + msg);
        Consumer<String> alertService = msg -> System.out.println("[NOTIFICATION] Sending SMS for event: " + msg);

        Consumer<String> eventPipeline = consoleLogger
                .andThen(auditLogger)
                .andThen(alertService);

        System.out.println("\n[Consumer Pipeline Chaining (andThen)]");
        eventPipeline.accept("Order #9876 created by user");

        // Spring Boot Real-World Context: Spring Cloud Stream / Kafka Listener Bean
        System.out.println("\n[Spring Boot Simulated Use Case: Kafka Event Consumer]");
        Consumer<OrderEvent> orderEventConsumer = event -> {
            System.out.println("  -> @Bean Consumer<OrderEvent> received from Kafka topic: " + event.orderId());
            System.out.println("  -> Updating inventory and sending receipt for total: $" + event.totalAmount());
        };
        orderEventConsumer.accept(new OrderEvent("ORD-1001", 149.99, "PAID"));
        System.out.println();
    }

    // =========================================================================
    // 3. SUPPLIER<T> -> T get()
    // =========================================================================
    public static void demonstrateSupplier() {
        System.out.println("================================================================");
        System.out.println("  3. SUPPLIER<T> : T get()                                      ");
        System.out.println("================================================================");
        System.out.println("Role: Takes no input, produces/supplies a value lazily on demand.\n");

        // Basic Supplier
        Supplier<String> tokenGenerator = () -> "AUTH-TOKEN-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("[Basic Supplier]");
        System.out.println("• Generated Token 1: " + tokenGenerator.get());
        System.out.println("• Generated Token 2: " + tokenGenerator.get());

        // Spring Boot Real-World Context 1: Lazy Exception Throwing (Optional.orElseThrow)
        System.out.println("\n[Spring Boot Simulated Use Case 1: Lazy Exception Creation]");
        Optional<User> emptyUserOptional = Optional.empty();

        try {
            // The exception is ONLY instantiated if the value is missing (Lazy evaluation saves CPU/memory)
            emptyUserOptional.orElseThrow(() -> new ResourceNotFoundException("User with ID 404 not found in database"));
        } catch (ResourceNotFoundException ex) {
            System.out.println("  • Caught lazily supplied exception: " + ex.getMessage());
        }

        // Spring Boot Real-World Context 2: Spring Cloud Stream Polling Producer Bean
        System.out.println("\n[Spring Boot Simulated Use Case 2: Event Producer Supplier]");
        Supplier<Map<String, Object>> metricsSupplier = () -> Map.of(
                "timestamp", System.currentTimeMillis(),
                "memoryUsageMb", Runtime.getRuntime().totalMemory() / (1024 * 1024),
                "status", "UP"
        );
        System.out.println("  • Spring polling Supplier output: " + metricsSupplier.get());
        System.out.println();
    }

    // =========================================================================
    // 4. FUNCTION<T, R> -> R apply(T t)
    // =========================================================================
    public static void demonstrateFunction() {
        System.out.println("================================================================");
        System.out.println("  4. FUNCTION<T, R> : R apply(T t)                              ");
        System.out.println("================================================================");
        System.out.println("Role: Takes an input of type T and transforms/maps it into type R.\n");

        // Basic Function: String -> Integer
        Function<String, Integer> stringLength = str -> str != null ? str.length() : 0;
        System.out.println("[Basic Function]");
        System.out.println("• Length of 'SpringBoot': " + stringLength.apply("SpringBoot"));

        // Function Chaining: andThen() and compose()
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> addTen = x -> x + 10;

        // andThen: first multiplyByTwo, then addTen: (5 * 2) + 10 = 20
        Function<Integer, Integer> multiplyThenAdd = multiplyByTwo.andThen(addTen);
        // compose: first addTen, then multiplyByTwo: (5 + 10) * 2 = 30
        Function<Integer, Integer> addThenMultiply = multiplyByTwo.compose(addTen);

        System.out.println("\n[Function Chaining]");
        System.out.println("• multiplyByTwo.andThen(addTen) for 5 : " + multiplyThenAdd.apply(5)); // 20
        System.out.println("• multiplyByTwo.compose(addTen) for 5 : " + addThenMultiply.apply(5)); // 30

        // Spring Boot Real-World Context: Entity to DTO Mapper
        System.out.println("\n[Spring Boot Simulated Use Case: Entity to DTO Mapper]");
        Function<User, UserDto> userMapper = user -> new UserDto(
                user.id(),
                user.name(),
                user.email(),
                user.role()
        );

        User entity = new User(101L, "David Miller", "david@company.com", 29, "ACTIVE", "DEVELOPER");
        UserDto dto = userMapper.apply(entity);
        System.out.println("  • Converted Entity -> DTO: " + dto);
        System.out.println();
    }

    // =========================================================================
    // 5. SPRING BOOT END-TO-END PIPELINE SIMULATION
    // =========================================================================
    public static void demonstrateSpringWorkflowIntegration() {
        System.out.println("================================================================");
        System.out.println("  5. END-TO-END SPRING BOOT SERVICE LAYER INTEGRATION           ");
        System.out.println("================================================================");
        System.out.println("Combining Predicate, Consumer, Supplier, and Function in a typical Spring service:\n");

        List<User> database = List.of(
                new User(1L, "Alice", "alice@example.com", 28, "ACTIVE", "ADMIN"),
                new User(2L, "Bob", "bob@example.com", 16, "ACTIVE", "USER"),
                new User(3L, "Charlie", "charlie@disabled.com", 32, "INACTIVE", "USER")
        );

        // 1. Predicate: Filter active adult users
        Predicate<User> isActiveAdult = u -> "ACTIVE".equalsIgnoreCase(u.status()) && u.age() >= 18;

        // 2. Function: Map User entity to UserDto
        Function<User, UserDto> toDto = u -> new UserDto(u.id(), u.name(), u.email(), u.role());

        // 3. Consumer: Log each exported DTO
        Consumer<UserDto> notifyExport = dto -> System.out.println("  -> Exported User: " + dto.name() + " (" + dto.email() + ")");

        // 4. Supplier: Fallback when no user found
        Supplier<UserDto> defaultGuestSupplier = () -> new UserDto(0L, "Anonymous Guest", "guest@system.local", "GUEST");

        System.out.println("[Executing Service Pipeline]");
        List<UserDto> exportedDtos = database.stream()
                .filter(isActiveAdult)      // Predicate
                .map(toDto)                 // Function
                .peek(notifyExport)         // Consumer
                .toList();

        System.out.println("\n• Total Active Adults Exported: " + exportedDtos.size());

        // Finding a non-existent user with Supplier fallback
        UserDto fallbackUser = database.stream()
                .filter(u -> u.id().equals(999L))
                .map(toDto)
                .findFirst()
                .orElseGet(defaultGuestSupplier); // Supplier

        System.out.println("• Search for ID 999 with Supplier fallback: " + fallbackUser);
        System.out.println("================================================================");
    }

    // =========================================================================
    // Supporting Domain Models & Custom Exception
    // =========================================================================
    public record User(Long id, String name, String email, int age, String status, String role) {}
    public record UserDto(Long id, String name, String email, String role) {}
    public record OrderEvent(String orderId, double totalAmount, String status) {}

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
