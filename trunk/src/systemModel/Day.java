/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DAMIAN
 */
public class Day implements SimpleKey {
    
    private List<Child> children;
    private Date date;
    private String id;
    private Room room;
    private List<Session> sessions;
    private boolean template;
    
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
    
    public Collection<Session> getSessions()
    {
    	return sessions;
   
    }

	public Collection<Child> getChildren() {
		return children;
	}
    
	public boolean getTemplate(){
		return template;
	}
	
	public void setTemplate(boolean template) {
		this.template = template;
	}
    
}
