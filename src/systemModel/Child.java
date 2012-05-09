/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;

/**
 *
 * @author DAMIAN
 */
public class Child implements SimpleKey {
    String id;
    String name;
    Date dateJoined;
    Date dob;
    List<Guardian> guardians;
    List<Objective> objectives;
    ImageIcon picture;
    URL pictureLink;
    
    public Child()
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
    }
    
    public Child(String name, Date dob, Date dateJoined)
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
        this.name = name;
        this.dob = dob;
        this.dateJoined = dateJoined;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addObjective(Objective o)
    {
        objectives.add(o);
    }
    
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
    
    
}
