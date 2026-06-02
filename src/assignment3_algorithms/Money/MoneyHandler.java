package assignment3_algorithms.Money;

public class MoneyHandler {

    public static void main(String[] args) {

        // standard bills
        System.out.println("Udbetaling med 50, 100, 500, 1000, 2000 og 5000 kr-sedler:");
        int[] bills = {5000, 2000, 1000, 500, 100, 50};
        greedyChange(bills, 550);
        greedyChange(bills, 3700);
        greedyChange(bills, 750);
        greedyChange(bills, 2250);
        greedyChange(bills, 150);

        // 600 kr introduceres
        System.out.println("600 kr-seddel introduceres:");
        int[] moreBills = {5000, 2000, 1000, 600, 500, 100, 50};
        greedyChange(moreBills, 1200);
        greedyChange(moreBills, 1300);
        greedyChange(moreBills, 1800);

    }

    public static void greedyChange(int[] bills, int amount) {
        System.out.println("Beløb: " + amount);
        for (int bill : bills) {
            int noOfBills = amount / bill;
            if (noOfBills > 0) {
                System.out.println(noOfBills + " x " + bill + " kr");
                amount -= noOfBills * bill;
            }
        }
        if (amount > 0) {
            System.out.println("Rest tilbage: " + amount + " kr");
        }
    }


}
