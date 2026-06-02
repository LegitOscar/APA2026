package assignment1_complexity.complexity_signe;

public class BigOExamples {
    public static void main(String[] args) {
        int n = 100; // Juster n for at se effekten
        int[] testArray = new int[n];
        for (int i = 0; i < n; i++) testArray[i] = i;


        System.out.println("O(1) - Konstant tid:");
        constantTime(n);

        System.out.println("\nO(log n) - Logaritmisk tid:");
        logTime(n);

        System.out.println("\nO(n) - Lineær tid:");
        linearTime(n);

        System.out.println("\nO(n^2) - Kvadratisk tid:");
        quadraticTime(n);
    }

    // O(1) - Konstant tid
    public static void constantTime(int n) {
        System.out.println("Jeg printer altid én gang, uanset n.");
    }

    // O(log n) - Logaritmisk tid (Binær nedtælling)
    public static void logTime(int n) {
        for (int i = n; i > 1; i /= 2) {
            System.out.println("Jeg kører log n gange, n er nu: " + i);
        }
    }

    // O(n) - Lineær tid
    public static void linearTime(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Itererer: " + i);
        }
    }

    // O(n^2) - Kvadratisk tid
    public static void quadraticTime(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Kvadratisk iteration: " + i + "," + j);
            }
        }
    }
}