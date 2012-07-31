
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
    String id;
    String name;
    Calendar dateJoined;
    Calendar dob;
    List<Guardian> guardians;
    List<Objective> objectives;
    List<Mark> marks;
    ImageIcon picture;
    URL pictureLink;
    boolean active;
    
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

    public Calendar getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Calendar dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public List<Guardian> getGuardians() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(name.equals(""))
        {
        	throw(new Exception("Childs name cannot be empty"));
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
    
    public URL getPictureLink()
    {
        return pictureLink;
    }
    
    public ImageIcon getPicture()
    {
        return picture;
    }
    
    public String toString()
    {
        return name;
    }

	public List<Objective> getObjectives() {
		return objectives;
	}

	public List<Mark> getMarks() {
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

	public boolean getActive() {
		return active;
	}
    
    
}
