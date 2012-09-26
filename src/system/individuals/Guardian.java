
package system.individuals;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Guardian extends UserAccount {
	@ManyToMany
    @JoinTable(name="GuardianChild",
		joinColumns={@JoinColumn(name="username")},
		inverseJoinColumns={@JoinColumn(name="ChildID")})
	private List<Child> children;
    
	/**
	 * creates a new Guardian object with no children in it.
	 */
    public Guardian()
    {
        super();
        children = new ArrayList<Child>();
        super.setAccess("g");
    }

    /**
     * Creates a new guardian object with the name and phone no passed through.
     * @param name
     * @param phone
     */
    public Guardian(String name, String phone) {
        super();
        super.setName(name);
        super.setPhoneNo(phone);
        children = new ArrayList<Child>();
        super.setAccess("g");
    }
    
    /**
     * Creates a new guardian object with the name, email and phone no passed through.
     * @param name
     * @param username
     * @param emailAddress
     */
    public Guardian(String name, String username, String emailAddress) {
		super(name, username, emailAddress);
		children = new ArrayList<Child>();
	}

    /**
     * Adds the passed through child to the guardian object
     * @param child
     */
	public void addChild(Child child)
    {
    	children.add(child);
    }

	/**
	 * Returns a list of all the children associated with this guardian.
	 * @return childList
	 */
	public List<Child> getChildren() {
		return children;
	}

	/**
	 * Sets the list of children to be the one passed through
	 * @param children
	 */
	public void setChildren(List<Child> children) {
		this.children = children;
	}
	
	/**
	 * Overrides Object.equals(Object o)
	 * checks if object is null, then if same class.
	 * Checks for same reference then same username
	 */
	public boolean equals(Object o)
	{
		if (o == null) {
			return false;
		}
		if(getClass() != o.getClass()) {
			return false;
		}
		if(o == this)
		{
			return true;
		}
		Guardian g = (Guardian) o;
		if(g.getUsername().equals(this.getUsername()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    
    
}
