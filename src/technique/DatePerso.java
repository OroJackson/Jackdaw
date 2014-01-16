package technique;

import java.text.*;
import java.util.*;

public class DatePerso extends GregorianCalendar{

	public DatePerso(){}
	
	public DatePerso(String dateT) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat ("dd:MM:yyyy");
		Date date = format.parse(dateT);
		setTime(date);
	}
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public 	void toDate(String dateT) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat ("dd:MM:yyyy");
			Date date = format.parse(dateT);
			setTime(date);

	}
	public String toStringDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
		return dateFormat.format(this.getTime());
	}
	
	public String toStringHeure(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		return dateFormat.format(this.getTime());
	}
	
	public void setHeureDepart(String heureT) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat ("HH:mm");
		Date heure = format.parse(heureT);
		setTime(heure);
	}
	
	public static void main(String[] args) throws ParseException{
		
		DatePerso d1=new DatePerso();
		DatePerso d2=new DatePerso("07:03:1984");
		d1.getTime();
		System.out.println(d1.toStringDate());
		System.out.println(d1.toStringHeure());
		d1.setHeureDepart("05:05");
		System.out.println(d1.toStringHeure());
		System.out.println(d2.toStringDate());

		System.out.println("test");
		
	}
}
