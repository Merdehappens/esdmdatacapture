
package system.marking;

import java.util.ArrayList;
import java.util.Collection;
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
import system.individuals.Child;
import system.sessions.Session;

@Entity
public class Objective implements SimpleKey  {
	@Id
	@Column(name="ObjectiveID")
	private String id;
	private String name;
    private String description;
    private int level;
    @OneToMany(targetEntity=Step.class,
    		mappedBy="objective",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Step> steps;
    @ManyToMany
    @JoinTable(name="ChildObjective",
		joinColumns={@JoinColumn(name="ObjectiveID")},
		inverseJoinColumns={@JoinColumn(name="ChildID")})
    private List<Child> children;
    @ManyToMany
    @JoinTable(name="SessionObjective",
    		joinColumns={@JoinColumn(name="ObjectiveID")},
    		inverseJoinColumns={@JoinColumn(name="SessionID")})
    private List<Session> sessions;
    
    public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public final int getLevel()
    {
    	return level;
    }
    
	public final String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
    public Objective()
    {
        steps = new ArrayList<Step>();
        children = new ArrayList<Child>();
    }
    
    public Objective(Collection<Step> c)
    {
        steps = new ArrayList<Step>(c);
        children = new ArrayList<Child>();
    }
    
    public Objective(String name, String description, int level)
    {
    	this.name = name;
    	this.description = description;
    	this.level = level;
    	steps = new ArrayList<Step>();
    	children = new ArrayList<Child>();
    }
    
    public void addSteps(Step newStep)
    {
        steps.add(newStep);
    }
    
    public int getStepsNo()
    {
    	return steps.size();
    }
    
    public void setSteps(Collection<Step> step)
    {
        steps = new ArrayList<Step>(step);
    }
    
    public final String getDescription()
    {   
        return description;
    }
    
    public final String getName()
    {
    	return name;
    }
    
    public String toString()
    {
    	return name + " Level: " + level;
    }

	public final List<Step> getSteps() {
		return steps;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}


    
    
    
}
