package membre;

import java.text.*;
import java.util.*;

public class DatePerso extends GregorianCalendar{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString(){
		DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
		return dateFormat.format(this.getTime());
	}
	
	public static void main(String[] args){
		
		DatePerso d1=new DatePerso();
		d1.getTime();
		System.out.println(d1);
	}
}
