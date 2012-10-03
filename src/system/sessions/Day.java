
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
    
    /**
     * Creates a new Day object
     */
    public Day()
    {
    	children = new ArrayList<Child>();
    	settings = new ArrayList<Setting>();
        marks = new ArrayList<Mark>();
    }

    /**
     * Creates a new day object with the parameters passed in
     * @param children
     * @param date
     * @param id
     * @param settings
     */
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
    
    /**
     * Creates a new day object with the date passed through
     * @param date
     */
    public Day(Calendar date)
    {
        this.date = date;
        setDateTime();
        marks = new ArrayList<Mark>();
        children = new ArrayList<Child>();
        settings = new ArrayList<Setting>();
    }
        
    /**
     * Creates a new Day with the date and id passed through
     * @param date
     * @param id
     */
    public Day(Calendar date, int id)
    {
        this.date = date;
        setDateTime();
        this.id = id;
        marks = new ArrayList<Mark>();
        children = new ArrayList<Child>();
        settings = new ArrayList<Setting>();
    }
    
    /**
     * Creates a new day object with the date and room passed through
     * @param date
     * @param room
     */
    public Day(Calendar date, Room room)
    {
    	this.date = date;
    	setDateTime();
    	this.room = room;
    	marks = new ArrayList<Mark>();
    	children = new ArrayList<Child>();
    	settings = new ArrayList<Setting>();
    }
    
    /**
     * Adds a mark to this day object
     * @param mark
     */
    public void addMark(Mark mark)
    {
    	marks.add(mark);
    }
    
    /**
     * Returns a list of all marks in this object.
     * @return marksList
     */
    public List<Mark> getMarks()
    {
    	return marks;
    }
    
    /**
     * Sets the room of this object to the one passed through.
     * @param room
     */
    public void setRoom(Room room)
    {
    	this.room = room;
    }
    
    /**
     * Returns the ID associated with this day object.
     * @return id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Returns the room associated with this day object.
     * @return
     */
    public Room getRoom()
    {
    	return room;
    }
    
    /**
     * Returns all of the settings in this day.
     * @return settingList
     */
    public Collection<Setting> getSettings()
    {
    	return settings;
   
    }

    /**
     * Returns all of the children in this day.
     * @return childList
     */
	public Collection<Child> getChildren() {
		return children;
	}
    
	/**
	 * Retrieves a boolean which states whether or not this day is a template
	 * @return template
	 */
	public boolean getTemplate(){
		return template;
	}
	
	/**
	 * Sets this day as a "template" day
	 * @param template
	 */
	public void setTemplate(boolean template) {
		this.template = template;
	}
	
	/**
	 * Returns the date associated with this Day object.
	 * @return date
	 */
	public Calendar getDate()
	{
		return date;
	}
	
	/**
	 * Adds the child parameter to the list of children.
	 * @param child
	 */
	public void addChildren(Child child)
	{
		children.add(child);
	}
	
	/**
	 * Adds the setting parameter to the list of settings.
	 * @param setting
	 */
	public void addSetting(Setting setting)
	{
		settings.add(setting);

	}

	/**
	 * Sets the id of this Day object to the string passed through.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the list of children to the list passed through
	 * @param childList
	 */
	public void setChildren(List<Child> children) {
		this.children = children;
	}

	/**
//	 * Sets the date in the Day object to the one passed through.
	 * @param date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Sets the setting list in the Day object to the one passed through.
	 * @param settingsList
	 */
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	/**
	 * Sets the marks list in the day object to the one passed through
	 * @param marksList
	 */
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	
	public String toString()
	{
		return (id+"");
	}
    
	
}
