package extra.builder;

public class Main {
    public static void main(String[] args) {

        // User 1 - all fields set
        User fullUser = new User.Builder("Alice", "alice@email.com")
                .address("Nørrebrogade 42")
                .phone("12345678")
                .country("DK")
                .newsletter(true)
                .build();

        // User 2 - only required fields
        User minimalUser = new User.Builder("Bob", "bob@email.com")
                .build();

        System.out.println("Full user:    " + fullUser);
        System.out.println("Minimal user: " + minimalUser);
    }
}