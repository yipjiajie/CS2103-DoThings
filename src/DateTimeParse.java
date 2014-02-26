import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class DateTimeParse {
	private static ArrayList<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat> (Arrays.asList( 
			new SimpleDateFormat("dd/MM/yyyy"), 
			new SimpleDateFormat("dd-MM-yyyy"),
			new SimpleDateFormat("dd.MM.yyyy"),
			new SimpleDateFormat("dd/MM"),
			new SimpleDateFormat("dd-MM"),
			new SimpleDateFormat("dd.MM")
			));
	
	
	
	public static void parseDate(String input){
		Date date = null;
		for (SimpleDateFormat format: dateFormats){
			try{
				format.setLenient(false);
				date = format.parse(input);
			} catch (ParseException e) {
				
			}
			
			if (date != null){
				break;
			}
		}
		
		if(date == null) {
			System.out.println("error");
		} else {
			System.out.println(date.toString());
		}
	}
	
	public static boolean isDate(String input){
		Date date = null;
		for (SimpleDateFormat format: dateFormats){
			try{
				format.setLenient(false);
				date = format.parse(input);
			} catch (ParseException e) {
				// some sort of parsing error
			}
			
			if (date != null){
				break;
			}
		}
		
		if(date == null) {
			return false;
		} else {
			return true;
		}
	}
}
