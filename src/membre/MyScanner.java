package membre;
import java.util.Scanner;

public class MyScanner implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyScanner(){
		
	}
	
	public String scanString(){
		Scanner sc=new Scanner (System.in);
		String rep=sc.nextLine();
		sc.close();
		return rep;
		
	}
	public int scanInt(){
		Scanner sc=new Scanner (System.in);
		int n=sc.nextInt();
		sc.nextLine();
		sc.close();
		return n;

	}

}
