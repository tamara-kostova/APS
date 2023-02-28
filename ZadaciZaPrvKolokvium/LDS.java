import java.util.Arrays;
import java.util.Scanner;
public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {
        int lds[] = new int[a.length];
        for (int i=0; i<a.length; i++)
            lds[i]=1;
        for (int i=1; i<a.length; i++){
            for (int j=0; j<i; j++)
                if (a[i]<a[j])
                    lds[i]=Math.max(lds[j]+1, lds[i]);
        }
        int max=Integer.MIN_VALUE;
        for (int i=0; i<lds.length; i++)
            if (lds[i]>max)
                max=lds[i];
        return max;
    }
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}
