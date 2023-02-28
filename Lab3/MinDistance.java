import java.util.Scanner;
import java.lang.Math;

public class MinDistance {

    public static float minimalDistance(float points[][]) {
        double mindist = Math.sqrt(Math.pow((double)(points[1][0]-points[0][0]),2)+Math.pow((double)(points[1][1]-points[0][1]),2));
        for (int i=0; i<points.length; i++){
            for (int j=i+1; j< points.length; j++){
                double newdistance=Math.sqrt(Math.pow((double)(points[j][0]-points[i][0]),2)+Math.pow((double)(points[j][1]-points[i][1]),2));
                if (newdistance<mindist)
                    mindist=newdistance;
            }
        }
        return (float) mindist;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}