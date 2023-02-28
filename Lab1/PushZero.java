package lab1;
import java.io.*;
import java.util.Scanner;

public class PushZero
{
	static void pushZerosToEnd(int arr[], int n)
	{
		int j=0;
		for (int i=0; i<n; i++)
			if (arr[i]!=0)
				arr[j++]=arr[i];
		while (j<n)
			arr[j++]=0;
	}

	public static void main (String[] args)
	{
		int arr[] = new int[100];
		int n;
		Scanner input=new Scanner(System.in);
		n=input.nextInt();
		for (int i=0; i<n; i++)
			arr[i]=input.nextInt();
		pushZerosToEnd(arr, n);
		System.out.println("Transformiranata niza e:");
		for (int i=0; i<n; i++)
			System.out.print(arr[i]+" ");
		input.close();
	}
}
