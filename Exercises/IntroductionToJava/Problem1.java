package Problems;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Problem1 {
 
	
	public static void main(String[] args) {
		
		int nOfStrings;
		
		System.out.println("Enter the number of strings: ");
		Scanner s = new Scanner(System.in);
		
		nOfStrings = s.nextInt();

		Set<String> strSet = new TreeSet<>();
		for(int i = 0; i<nOfStrings; i++)
		{
			strSet.add(s.next());
		}
		
		System.out.println(strSet);
		
	}
}
