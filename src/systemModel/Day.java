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
    private List<Mark> marks;
    
    public Day()
    {
    }

    public Day(ArrayList<Child> children, Date date, String id, ArrayList<Session> sessions) {
        this.children = children;
        this.date = date;
        this.id = id;
        this.sessions = sessions;
        marks = new ArrayList<Mark>();
    }
        
    public Day(Date date, String id)
    {
        this.date = date;
        this.id = id;
        marks = new ArrayList<Mark>();
        children = new ArrayList<Child>();
        sessions = new ArrayList<Session>();
    }
    
    public Day(Date date, Room room)
    {
    	this.date = date;
    	this.room = room;
    	marks = new ArrayList<Mark>();
    	children = new ArrayList<Child>();
    	sessions = new ArrayList<Session>();
    }
    
    public void addMark(Mark mark)
    {
    	marks.add(mark);
    }
    
    public List<Mark> getMarks()
    {
    	return marks;
    }
    
    public void setRoom(Room room)
    {
    	this.room = room;
    }
    
    public String getId()
    {
        return id;
    }
    
    public Room getRoom()
    {
    	return room;
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
	
	public String toString()
	{
		return id;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void addChildren(Child child)
	{
	        children.add(child);
	}
	
	public void addSession(Session session)
	{
		sessions.add(session);

	}
    
}
