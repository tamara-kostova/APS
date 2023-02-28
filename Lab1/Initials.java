package lab1;
import java.util.Scanner;

public class Initials {
	
	static void printInitials(String name)
	{
		int i=0;
		System.out.print(Character.toUpperCase(name.charAt(i)));
		while (i<name.length()) {
			if (name.charAt(i)==' ') {
				System.out.print(Character.toUpperCase(name.charAt(i+1)));
			}
			i++;
		}
	}

	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		int n=input.nextInt();
		String name;
		input.nextLine();
		
		for(int i=0; i<n; i++){
		   name = input.nextLine(); 
		   printInitials(name); 
		   System.out.println();
		}
		input.close();
	}
}

