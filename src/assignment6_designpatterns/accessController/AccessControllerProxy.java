package assignment6_designpatterns.accessController;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccessControllerProxy implements ISecureService {

    private final ISecureService realService;
    private final User user;
    private static final String LOG_FILE = "access_log.txt";

    public AccessControllerProxy(ISecureService realService, User user) {
        this.realService = realService;
        this.user = user;
    }

    private void invoke(String methodName) {
        try {
            Method method = realService.getClass().getMethod(methodName);
            Role roleAnnotation = method.getAnnotation(Role.class);
            Log logAnnotation = method.getAnnotation(Log.class);

            boolean accessGranted;

            if (roleAnnotation == null) {
                System.out.println("Metoden '" + methodName + "' kræver ingen rolle – kaldes.");
                method.invoke(realService);
                accessGranted = true;
            } else {
                String requiredRole = roleAnnotation.value();
                if (user.getRole().equals(requiredRole)) {
                    System.out.println("Adgang givet til '" + methodName + "' for bruger '" + user.getName() + "'");
                    method.invoke(realService);
                    accessGranted = true;
                } else {
                    System.out.println("Adgang nægtet til '" + methodName + "' for bruger '" + user.getName() + "'");
                    accessGranted = false;
                }
            }

            if (logAnnotation != null) {
                writeLog(user, methodName, accessGranted);
            }

        } catch (NoSuchMethodException e) {
            System.out.println("Metoden '" + methodName + "' findes ikke.");
        } catch (Exception e) {
            System.out.println("Fejl under metodekald: " + e.getMessage());
        }
    }

    @Override
    public void deleteAllUsers() { invoke("deleteAllUsers"); }

    @Override
    public void viewProfile() { invoke("viewProfile"); }

    @Override
    public void help() { invoke("help"); }

    private void writeLog(User user, String methodName, boolean accessGranted) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String status = accessGranted ? "TILLADT" : "NÆGTET";
        String logEntry = "[" + timestamp + "] Bruger: " + user.getName()
                + " | Metode: " + methodName + " | Status: " + status;
        System.out.println("LOG: " + logEntry);
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(logEntry + "\n");
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til logfil: " + e.getMessage());
        }
    }
}
