/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

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
    
    public Objective()
    {
        steps = new ArrayList<Step>();
    }
    
    public Objective(Collection<Step> c)
    {
        steps = new ArrayList<Step>(c);
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
    
    
    
}
