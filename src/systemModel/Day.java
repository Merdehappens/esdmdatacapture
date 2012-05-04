/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DAMIAN
 */
public class Day implements SimpleKey {
    
    List<Child> children;
    Date date;
    String id;
    Room room;
    List<Session> sessions;
    
    public Day()
    {
    }

    public Day(ArrayList<Child> children, Date date, String id, ArrayList<Session> sessions) {
        this.children = children;
        this.date = date;
        this.id = id;
        this.sessions = sessions;
    }
        
    public Day(Date date, String id)
    {
        this.date = date;
        this.id = id;
        children = new ArrayList<Child>();
        sessions = new ArrayList<Session>();
    }
    
    public void setRoom(Room room)
    {
    	this.room = room;
    }
    
    public String getId()
    {
        return id;
    }
    
    
}
