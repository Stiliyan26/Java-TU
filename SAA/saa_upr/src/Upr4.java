import java.util.Scanner;

public class Upr4 {
    static Scanner scanner = new Scanner(System.in);
    static int[] coins = { 50, 20, 10, 5, 2, 1 };

    static int i = 0;

    public static void main(String[] args) {
        System.out.print("Enter sum: ");
        int sum = scanner.nextInt();

        coinsFunc(sum);
    }

    public static void coinsFunc(int sum) {
        if (i != coins.length && sum != 0) {
            int n = sum / coins[i];

            if (n > 0) {
                System.out.println(n + " times " + coins[i]);
                coinsFunc(sum - n * coins[i++]);
            } else {
                i++;
                coinsFunc(sum);
            }
        }
    }
}
