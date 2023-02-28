import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();
        int minimalna, maximalna = (M+N-1)*100;
        if (M==0) maximalna+=100;
        if (M<N)
            minimalna = N*100;
        else
            minimalna=M*100;
        System.out.println(minimalna);
        System.out.println(maximalna);

    }

}
