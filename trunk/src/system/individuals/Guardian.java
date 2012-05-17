/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.individuals;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author DAMIAN
 */
public class Guardian extends UserAccount {
    Set<Child> children;
    
    public Guardian()
    {
        super();
        children = new HashSet<Child>();
    }

    public Guardian(String name, String phone) {
        super();
        super.setName(name);
        super.setPhoneNo(phone);
        children = new HashSet<Child>();
    }
    
    public void addChild(Child child)
    {
    	children.add(child);
    }
    
    
}
