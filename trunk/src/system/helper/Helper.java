package system.helper;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Random;


public class Helper {

	/**
	 * Parse in Collection and a Key to search that collection with.
	 * Searchs through collection and Returns the first item with that specific key.
	 * 
	 */
	
   public static <E extends SimpleKey> E search(Collection<E> collection, String key)
   {
      for(E e: collection)
      {
         if( e.getId().equals(key) )
         {
            return e;
         }
      }
      return null;
   }
   
   
   /**
    * Takes in an int and creates a random AlphaNumerical string of the length specified in the length int.
    */
   
   public static String generateRandomString(int length)
   {
	   String chars = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   int charLength = chars.length();
	   Random r = new Random();
	   
	   StringBuilder sb = new StringBuilder( length );
	   
	   for(int i = 0; i < length; i++)
	   {
		   char c = chars.charAt(r.nextInt(charLength));
		   sb.append(c);
	   }
	   
	   
	   return sb.toString();
	   
   }
   
   /*
    * Parses in a calendar Object. sets time to null and then returns Calendar object
    */
   
   public static Calendar setCalendarTimeNull(Calendar c)
   {
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	
	    return c;
   }

   /*
    * Returns a string representation of a calendar object in DD/MM/YY format
    */
   
public static String simpleDateFormat(Calendar c) {
	if(c != null)
	{
		return c.get(GregorianCalendar.DATE) + "/" + (c.get(GregorianCalendar.MONTH) + 1) + "/" + c.get(GregorianCalendar.YEAR);
	}
	return null;
}

    
}
