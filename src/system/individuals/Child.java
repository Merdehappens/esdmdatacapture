/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.individuals;


import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;

import system.helper.SimpleKey;
import system.marking.Mark;
import system.marking.Objective;


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
    List<Mark> marks;
    ImageIcon picture;
    URL pictureLink;
    
    public Child()
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
        marks = new ArrayList<Mark>();
    }
    
    public Child(String name, Date dob, Date dateJoined)
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
        marks = new ArrayList<Mark>();
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

	public List<Mark> getMarks() {
		return marks;
	}
    
    
}
