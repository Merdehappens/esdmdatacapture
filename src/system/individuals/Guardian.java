
package system.individuals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;


public class Guardian extends UserAccount {
	private List<Child> children;
    
    public Guardian()
    {
        super();
        children = new ArrayList<Child>();
    }

    public Guardian(String name, String phone) {
        super();
        super.setName(name);
        super.setPhoneNo(phone);
        children = new ArrayList<Child>();
        super.setAccess("g");
    }
    
    /*
     * Adds a child object to the hashSet inside the guardian object
     */
    
    public void addChild(Child child)
    {
    	children.add(child);
    }

	public List<Child> getChildren() {
		return children;
	}
    
    
}
