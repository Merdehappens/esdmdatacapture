
package system.sessions;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import system.helper.SimpleKey;
import system.individuals.Child;
import system.marking.Mark;
import system.model.Room;



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
        setDateTime();
        this.id = id;
        this.sessions = sessions;
        marks = new ArrayList<Mark>();
    }
    
    private void setDateTime()
    {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }
        
    public Day(Calendar date, String id)
    {
        this.date = date;
        setDateTime();
        this.id = id;
        marks = new ArrayList<Mark>();
        children = new ArrayList<Child>();
        sessions = new ArrayList<Session>();
    }
    
    public Day(Calendar date, Room room)
    {
    	this.date = date;
    	setDateTime();
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
