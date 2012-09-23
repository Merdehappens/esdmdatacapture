
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
import system.marking.Objective;
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
    
    public Setting()
    {    
    }
    
    public Setting(String description)
    {
        this.description = description;
        objectives = new ArrayList<ObjectiveType>();
    }
    
    public Setting(int id, String description)
    {
        this.id = id;
        this.description = description;
        objectives = new ArrayList<ObjectiveType>();
    }
    
    public int getId()
    {
        return id;
    }
    
    public String toString()
    {
    	return description;
    }
    
    public List<ObjectiveType> getObjectives()
    {
    	return objectives;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setObjectives(List<ObjectiveType> objTypeList) {
		this.objectives = objTypeList;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
    
    
}
