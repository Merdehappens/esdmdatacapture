/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.marking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 *
 * @author DAMIAN
 */
public class Objective {
    private List<Step> steps;
    private String name;
    private String description;
    private int level;
	private String id;
    
    public int getLevel()
    {
    	return level;
    }
    
	public String getId()
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
    
    public Objective(String name, String description)
    {
    	this.name = name;
    	this.description = description;
    	steps = new ArrayList<Step>();
    }
    
    public void addSteps(Step newStep)
    {
        steps.add(newStep);
    }
    
    public void setSteps(Collection<Step> step)
    {
        steps = new ArrayList<Step>(step);
    }
    
    public String getDescription()
    {   
        return description;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public String toString()
    {
    	return name + " Level: " + level;
    }

	public List<Step> getSteps() {
		return steps;
	}
    
    
    
}
