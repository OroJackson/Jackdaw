package membre;

import java.text.*;
import java.util.*;

public class DatePerso extends GregorianCalendar{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void toDate(String dateT) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat ("dd:MM:yyyy");
		Date date = format.parse(dateT);
		setTime(date);
	}
	public String toStringDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
		return dateFormat.format(this.getTime());
	}
	
	public String toStringHeure(){
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		return dateFormat.format(this.getTime());
	}
	
	public void setHeureDepart(String heureT) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat ("hh:mm");
		Date heure = format.parse(heureT);
		setTime(heure);
	}
	
	public static void main(String[] args) throws ParseException{
		
		DatePerso d1=new DatePerso();
		d1.getTime();
		System.out.println(d1.toStringDate());
		System.out.println(d1.toStringHeure());
		d1.setHeureDepart("05:05");
		System.out.println(d1.toStringHeure());

		System.out.println("test");
		
	}
}
