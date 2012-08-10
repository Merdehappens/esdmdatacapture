
package system.individuals;


import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;

import system.helper.SimpleKey;
import system.marking.Mark;
import system.marking.Objective;


public class Child implements SimpleKey {
    private String id;
    private String name;
    private Calendar dateJoined;
    private Calendar dob;
    private List<Guardian> guardians;
    private List<Objective> objectives;
    private List<Mark> marks;
    private ImageIcon picture;
    private URL pictureLink;
    private boolean active;
    
    public Child()
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
        marks = new ArrayList<Mark>();
        active = true;
    }
    
    public Child(String name, Calendar dob, Calendar dateJoined)
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
        marks = new ArrayList<Mark>();
        this.name = name;
        this.dob = dob;
        this.dateJoined = dateJoined;
        active = true;
    }

    public final Calendar getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Calendar dateJoined) {
        this.dateJoined = dateJoined;
    }

    public final Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public final List<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(ArrayList<Guardian> guardians) {
        this.guardians = guardians;
    }
    
    public void addGuardian(Guardian guardian)
    {
        guardians.add(guardian);
    }
    
    public void addMark(Mark mark)
    {
    	marks.add(mark);
    }

    public final String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(name.equals(""))
        {
        	throw(new Exception("The Child's name cannot be empty."));
        }
    	this.name = name;
    }
    
    public void addObjective(Objective o)
    {
        objectives.add(o);
    }
    
    /* This method takes in a URL. and sets the URL in the child object to that URL and sets the childs picture to be the
     * picture that the URL points to
     */
    
    public void setPictureLink(URL pictureLink) {
        this.pictureLink = pictureLink;
        picture = new ImageIcon(pictureLink);
    }
    
    public final URL getPictureLink()
    {
        return pictureLink;
    }
    
    public final ImageIcon getPicture()
    {
        return picture;
    }
    
    public String toString()
    {
        return name;
    }

	public final List<Objective> getObjectives() {
		return objectives;
	}

	public final List<Mark> getMarks() {
		return marks;
	}
	
	public void setInactive()
	{
		active = false;
	}
	
	public void setActive()
	{
		active = true;
	}

	public final boolean getActive() {
		return active;
	}
    
    
}
