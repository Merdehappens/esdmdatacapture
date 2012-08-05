
package system.individuals;

import java.util.HashSet;
import java.util.Set;


public class Guardian extends UserAccount {
	private Set<Child> children;
    
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
    
    /*
     * Adds a child object to the hashSet inside the guardian object
     */
    
    public void addChild(Child child)
    {
    	children.add(child);
    }
    
    
}
