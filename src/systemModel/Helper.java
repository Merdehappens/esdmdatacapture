/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

import java.util.Collection;

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

    
}
