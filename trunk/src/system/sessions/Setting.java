
package system.sessions;


import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import system.helper.SimpleKey;
import system.marking.Mark;
import system.marking.ObjectiveType;


@Entity
public class Setting implements SimpleKey {
	@Id
	@GeneratedValue
	@Column(name="SettingID")
	private int id;
	private String description;
	@ManyToMany
    @JoinTable(name="SettingObjectiveType",
    		joinColumns={@JoinColumn(name="SettingID")},
    		inverseJoinColumns={@JoinColumn(name="ObjectiveTypeID")})
	private List<ObjectiveType> objectives;
    @ManyToMany
    @JoinTable(name="DaySetting",
    		joinColumns={@JoinColumn(name="SettingID")},
    		inverseJoinColumns={@JoinColumn(name="DayID")})
	private List<Day> days;
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="setting",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;
    
    /**
     * Creates a new Setting object
     */
    public Setting()
    {    
    }
    
    /**
     * Creates a new setting object with the description passed through
     * @param description
     */
    public Setting(String description)
    {
        this.description = description;
        objectives = new ArrayList<ObjectiveType>();
    }
    
    /**
     * Creates a new setting object with the description and ID passed through
     * @param id
     * @param description
     */
    public Setting(int id, String description)
    {
        this.id = id;
        this.description = description;
        objectives = new ArrayList<ObjectiveType>();
    }
    
    /**
     * Returns the id associated with this setting.
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Returns the list of objectives associated with this setting
     * @return objectives
     */
    public List<ObjectiveType> getObjectives()
    {
    	return objectives;
    }

    /**
     * Returns the description of the setting
     * @return description
     */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the setting to the String passed through.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the id of the setting to the int passed through.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the objectives list to the one passed through
	 * @param objTypeList
	 */
	public void setObjectives(List<ObjectiveType> objTypeList) {
		this.objectives = objTypeList;
	}

	/**
	 * Returns all days this setting is in
	 * @return
	 */
	public List<Day> getDays() {
		return days;
	}

	/**
	 * sets the list of days that this setting is a part of
	 * @param days
	 */
	public void setDays(List<Day> days) {
		this.days = days;
	}

	/**
	 * Returns a list of all marks for this setting
	 * @return markList
	 */
	public List<Mark> getMarks() {
		return marks;
	}

	/**
	 * sets the list of marks associated with this setting
	 * @param marks
	 */
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

    public String toString()
    {
    	return description;
    }
    
    
}
