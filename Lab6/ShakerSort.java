import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n)
    {
        for (int i=0; i<n; i++) {
            int flag=0;
            for (int j = n-1; j > 0; j--){
                if (a[j-1]>a[j]){
                    flag=1;
                    int tmp=a[j-1];
                    a[j-1]=a[j];
                    a[j]=tmp;
                }
            }
            for (int k=0; k<n; k++)
                System.out.print(a[k]+" ");
            System.out.println();
            for (int j = i; j < n - 1; j++){
                if (a[j+1]<a[j]){
                    flag=1;
                    int tmp=a[j+1];
                    a[j+1]=a[j];
                    a[j]=tmp;
                }
            }

            for (int k=0; k<n; k++)
                System.out.print(a[k]+" ");
            System.out.println();
            if (flag==0){
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}