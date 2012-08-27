
package system.marking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.mysql.jdbc.Blob;

import system.helper.SimpleKey;
import system.individuals.Child;
import system.individuals.ChildObjective;
import system.sessions.Session;

@Entity
public class Objective implements SimpleKey  {
	@Id
	@GeneratedValue
	@Column(name="ObjectiveID")
	private int id;
	private String name;
	@Column(length=10000)
    private String description;
    private int level;
    @OneToMany(targetEntity=Step.class,
    		mappedBy="objective",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.EAGER)
    private List<Step> steps;
    @OneToMany(targetEntity=ChildObjective.class,
    		mappedBy="objective",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<ChildObjective> children;
    @ManyToMany
    @JoinTable(name="SessionObjective",
    		joinColumns={@JoinColumn(name="ObjectiveID")},
    		inverseJoinColumns={@JoinColumn(name="SessionID")})
    private List<Session> sessions;
/*	@ManyToOne
	@JoinColumn(name="objtype_ID")*/
    @Transient
    private ObjectiveType objType;
    

	public void setType(ObjectiveType objType)
	{
		this.objType = objType;
	}
	
	public ObjectiveType getType()
	{
		return objType;
	}
	
    public Objective()
    {
        steps = new ArrayList<Step>();
        children = new ArrayList<ChildObjective>();
    }
    
    public Objective(Collection<Step> c)
    {
        steps = new ArrayList<Step>(c);
        children = new ArrayList<ChildObjective>();
    }
    
    public Objective(String name, String description, int level)
    {
    	this.name = name;
    	this.description = description;
    	this.level = level;
    	steps = new ArrayList<Step>();
    	children = new ArrayList<ChildObjective>();
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
    	return name;
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
    
    public List<ChildObjective> getChildren() {
		return children;
	}

	public Step getStep(int currentStep) {
		Step s = steps.get(currentStep - 1);
		return s;
	}
	
	public void setChildren(List<ChildObjective> children) {
		this.children = children;
	}
	
	public void addChild(ChildObjective co)
	{
		children.add(co);
	}
	


	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public final int getLevel()
    {
    	return level;
    }
    
	public final int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
    
}
