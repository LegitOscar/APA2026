package assignment6_designpatterns.accessController;

public class Main {
    public static void main(String[] args) {
        SecureService realService = new SecureService();

        User admin = new User("Alice", "admin");
        User normal = new User("Bob", "user");

        ISecureService adminProxy = new AccessControllerProxy(realService, admin);
        ISecureService userProxy  = new AccessControllerProxy(realService, normal);

        adminProxy.deleteAllUsers();
        userProxy.deleteAllUsers();
        userProxy.viewProfile();
        userProxy.help();
    }
}
