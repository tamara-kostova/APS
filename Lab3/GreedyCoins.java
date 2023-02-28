import java.util.Arrays;
import java.util.Scanner;

public class GreedyCoins {
    public static int minNumCoins(int coins[], int sum) {
        Arrays.sort(coins);
        int i=coins.length-1, paricki=0;
        while (sum>=0&&i>=0){
            while (sum>=coins[i]){
                sum-=coins[i];
                paricki++;
            }
            i--;
        }
        return paricki;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();

        System.out.println(minNumCoins(coins, sum));
    }
}