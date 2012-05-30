/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.helper;

import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

/**
 *
 * @author Steven
 */
public class Helper {

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
   
   public static Calendar setCalendarTimeNull(Calendar c)
   {
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	
	    return c;
   }
   
    
}
