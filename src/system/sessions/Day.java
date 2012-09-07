
package system.sessions;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import system.helper.SimpleKey;
import system.individuals.Child;
import system.marking.Mark;
import system.model.Room;


@Entity
public class Day implements SimpleKey {
	@Id
	@GeneratedValue
	@Column(name="DayID")
	private int id;
    @ManyToMany
    @JoinTable(name="DayChild",
    		joinColumns={@JoinColumn(name="DayID")},
    		inverseJoinColumns={@JoinColumn(name="ChildID")})
    private List<Child> children;


    private Calendar date;
	@ManyToOne
	@JoinColumn(name="roomId")
    private Room room;
    // ^ ^ ^ "room" set to Transient, as I believe Room is no longer used
    @ManyToMany
    @JoinTable(name="DaySetting",
    		joinColumns={@JoinColumn(name="DayID")},
    		inverseJoinColumns={@JoinColumn(name="SettingID")})
    private List<Setting> settings;
    private boolean template;
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="day",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;
    
    public Day()
    {
    	children = new ArrayList<Child>();
    	settings = new ArrayList<Setting>();
        marks = new ArrayList<Mark>();
    }

    public Day(ArrayList<Child> children, Calendar date, int id, ArrayList<Setting> settings) {
        this.children = children;
        this.date = date;
        setDateTime();
        this.id = id;
        this.settings = settings;
        marks = new ArrayList<Mark>();
    }
    
    private void setDateTime()
    {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }
    
    public Day(Calendar date)
    {
        this.date = date;
        setDateTime();
        marks = new ArrayList<Mark>();
        children = new ArrayList<Child>();
        settings = new ArrayList<Setting>();
    }
        
    public Day(Calendar date, int id)
    {
        this.date = date;
        setDateTime();
        this.id = id;
        marks = new ArrayList<Mark>();
        children = new ArrayList<Child>();
        settings = new ArrayList<Setting>();
    }
    
    public Day(Calendar date, Room room)
    {
    	this.date = date;
    	setDateTime();
    	this.room = room;
    	marks = new ArrayList<Mark>();
    	children = new ArrayList<Child>();
    	settings = new ArrayList<Setting>();
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
    
    public int getId()
    {
        return id;
    }
    
    public Room getRoom()
    {
    	return room;
    }
    
    public Collection<Setting> getSettings()
    {
    	return settings;
   
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
		return (id+"");
	}
	
	public Calendar getDate()
	{
		return date;
	}
	
	public void addChildren(Child child)
	{
	        children.add(child);
	}
	
	public void addSetting(Setting setting)
	{
		settings.add(setting);

	}

	public void setId(int id) {
		this.id = id;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
    
	
}
