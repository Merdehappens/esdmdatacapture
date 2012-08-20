
package system.marking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import system.helper.SimpleKey;

@Entity
public class Objective implements SimpleKey  {
	@Id
	private String id;
	private String name;
    private String description;
    private int level;
    @OneToMany(targetEntity=Step.class, mappedBy="objective", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Step> steps;
    
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
    }
    
    public Objective(Collection<Step> c)
    {
        steps = new ArrayList<Step>(c);
    }
    
    public Objective(String name, String description, int level)
    {
    	this.name = name;
    	this.description = description;
    	this.level = level;
    	steps = new ArrayList<Step>();
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


    
    
    
}
