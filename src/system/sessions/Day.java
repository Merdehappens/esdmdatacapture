/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.sessions;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import system.helper.SimpleKey;
import system.individuals.Child;
import system.marking.Mark;
import system.model.Room;


/**
 *
 * @author DAMIAN
 */
public class Day implements SimpleKey {
    
    private List<Child> children;
    private Calendar date;
    private String id;
    private Room room;
    private List<Session> sessions;
    private boolean template;
    private List<Mark> marks;
    
    public Day()
    {
    }

    public Day(ArrayList<Child> children, Calendar date, String id, ArrayList<Session> sessions) {
        this.children = children;
        this.date = date;
        this.id = id;
        this.sessions = sessions;
        marks = new ArrayList<Mark>();
    }
        
    public Day(Calendar date, String id)
    {
        this.date = date;
        this.id = id;
        marks = new ArrayList<Mark>();
        children = new ArrayList<Child>();
        sessions = new ArrayList<Session>();
    }
    
    public Day(Calendar date, Room room)
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
	
	public Calendar getDate()
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
