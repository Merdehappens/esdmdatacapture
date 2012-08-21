
package system.sessions;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import system.helper.SimpleKey;
import system.marking.Mark;
import system.marking.Objective;


@Entity
public class Session implements SimpleKey {
	@Id
	@Column(name="SessionID")
	private String id;
	private String description;
    @ManyToMany
    @JoinTable(name="SessionObjective",
    		joinColumns={@JoinColumn(name="SessionID")},
    		inverseJoinColumns={@JoinColumn(name="ObjectiveID")})
	private List<Objective> objectives;
    @ManyToMany
    @JoinTable(name="DaySession",
    		joinColumns={@JoinColumn(name="SessionID")},
    		inverseJoinColumns={@JoinColumn(name="DayID")})
	private List<Day> days;
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="session",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;
    
    public Session()
    {    
    }
    
    public Session(String id, String description)
    {
        this.id = id;
        this.description = description;
        objectives = new ArrayList<Objective>();
    }
    
    public String getId()
    {
        return id;
    }
    
    public String toString()
    {
    	return description;
    }
    
    public List<Objective> getObjectives()
    {
    	return objectives;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public void setId(String id) {
		this.id = id;
	}

	public void setObjectives(List<Objective> objectives) {
		this.objectives = objectives;
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
