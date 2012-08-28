
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
    
    public Guardian(String name, String username, String emailAddress) {
		super(name, username, emailAddress);
	}

	public void addChild(Child child)
    {
    	children.add(child);
    }

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}
	
	// Checks if the object is null, checks if same class
	// Checks if it's the same reference then checks if the same
	// Username.
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
