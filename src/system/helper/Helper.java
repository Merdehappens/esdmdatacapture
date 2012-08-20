package system.helper;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


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
   
   public static String simpleDateFormat(Calendar c) 
   {
	   if(c != null)
	   {
		   return c.get(GregorianCalendar.DATE) + "/" + (c.get(GregorianCalendar.MONTH) + 1) + "/" + c.get(GregorianCalendar.YEAR);
	   }
	   return null;
   }
   
   
   public static void exportCSV(File f, JTable table) throws Exception
   {
	  // FileWriter fw = new FileWriter("C:\\Users\\Damian\\Desktop\\test.csv");
	   PrintWriter pw = new PrintWriter(f);
	   
	   DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
	   
	   int colCount = tblModel.getColumnCount();
	   
	   for(int i = 0; i < colCount; i++)
	   {
		   pw.write(tblModel.getColumnName(i) + '\t');
	   }
	   pw.write('\n');
	   
	   int rowCount = tblModel.getRowCount();
	   
	   for(int i = 0; i < rowCount; i++)
	   {
		   for(int x = 0; x < colCount; x++)
		   {
			   pw.write("\"" + tblModel.getValueAt(i, x) + "\"\t");
		   }
		   pw.write('\n');
	   }
	   pw.close();
	   

   }
   
   public static File chooseFile()
   {
	   JFileChooser fc = new JFileChooser();
	   
	   int returnVal = fc.showSaveDialog(null);
	   if (returnVal == JFileChooser.APPROVE_OPTION)
	   {
		   File file = fc.getSelectedFile();
		   return file;
	   }
	   else
	   {
		   return null;
	   }
   }
   
   public static boolean checkPasswordLength(String s)
   {
	   if(s.length() >= 6)
	   {
		   return true;
	   }
	   return false;
   }

   
}
