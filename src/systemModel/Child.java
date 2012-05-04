/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

import java.net.URL;
import java.util.Date;
import java.util.HashSet;
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
    Set<Guardian> guardians;
    Set<Objective> objectives;
    ImageIcon picture;
    URL pictureLink;
    
    public Child()
    {
        guardians = new HashSet<Guardian>();
        objectives = new HashSet<Objective>();
    }
    
    public Child(String name, Date dob)
    {
        guardians = new HashSet<Guardian>();
        objectives = new HashSet<Objective>();
        this.name = name;
        this.dob = dob;
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

    public Set<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(Set<Guardian> guardians) {
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
    
    
}
