
package system.marking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.Transient;

import system.helper.SimpleKey;
import system.individuals.Child;
import system.individuals.ChildObjective;
import system.sessions.Setting;

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

	@ManyToOne
	@JoinColumn(name="objtype_ID")
    private ObjectiveType objType;
    
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="objective",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;
    
    @Basic
    boolean hidden;
    
	/**
	 * Creates a new objective object and sets hidden to false.
	 */
    public Objective()
    {
        steps = new ArrayList<Step>();
        children = new ArrayList<ChildObjective>();
        marks = new ArrayList<Mark>();
        hidden = false;
    }
        
    /**
     * Creates a new objective object and sets hidden to false, Sets attributes
     * to the ones passed through
     * @param name
     * @param description
     * @param level
     */
    public Objective(String name, String description, int level)
    {
    	this.name = name;
    	this.description = description;
    	this.level = level;
    	steps = new ArrayList<Step>();
    	children = new ArrayList<ChildObjective>();
    	marks = new ArrayList<Mark>();
    	hidden = false;
    }
    
    /**
     * Sets the objective type of this objective to the one passed through.
     * @param objType
     */
	public void setType(ObjectiveType objType)
	{
		this.objType = objType;
	}
	
	/**
	 * Returns the ObjectiveType attribute of this Objective
	 * @return objectiveType
	 */
	public ObjectiveType getType()
	{
		return objType;
	}
    
	/**
	 * Adds a mark to this objectives list of marks.
	 * @param mark
	 */
    public void addMark(Mark mark)
    {
    	marks.add(mark);
    }
    
    /**
     * returns the list of marks in this objective.
     * @return markList
     */
    public List<Mark> getMarks()
    {
    	return marks;
    }
    
    /**
     * Sets the mark list in the objective to the one passed through
     * @param marks
     */
    public void setMarks(List<Mark> marks)
    {
    	this.marks = new ArrayList<Mark>(marks);
    }
    
    /**
     * Adds the new step to the objective.
     * @param step
     */
    public void addSteps(Step newStep)
    {
        steps.add(newStep);
    }
    
    /**
     * Returns the number of steps in this objective. 
     * @return steps
     */
    public int getStepsNo()
    {
    	return steps.size();
    }
    
    /**
     * Sets the list of steps to the list passed through
     * @param step
     */
    public void setSteps(Collection<Step> step)
    {
        steps = new ArrayList<Step>(step);
    }
    
    /**
     * Returns the description of the Objective.
     * @return description
     */
    public final String getDescription()
    {   
        return description;
    }
    
    /**
     * Returns the name of the Objective.
     * @return name
     */
    public final String getName()
    {
    	return name;
    }
    
    /**
     * Returns the list of steps in this Objective
     * @return stepsList
     */
	public final List<Step> getSteps() {
		return steps;
	}
	
	/**
	 * Sets the name of the Objective to the String passed through
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the description of the Objective to the String passed through
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Sets the level of the Objective to the integer passed through.
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
    
	/**
	 * returns the list of ChildObjectives in the Objective
	 * @return children
	 */
    public List<ChildObjective> getChildren() {
		return children;
	}

    /**
     * Returns the step related with the Integer passed through
     * @param currentStep
     * @return
     */
	public Step getStep(int currentStep) {
		return steps.get(currentStep - 1);
	}
	
	/**
	 * Sets the lits of children to the one parsed through
	 * @param children
	 */
	public void setChildren(List<ChildObjective> children) {
		this.children = children;
	}
	
	/**
	 * Adds a child objective to the ChildObjective list
	 * @param childObjective
	 */
	public void addChild(ChildObjective co)
	{
		children.add(co);
	}
	
	/**
	 * Sets the step of the Objective to the one parsed through
	 * @param steps
	 */
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	/**
	 * Returns the level of the Objective
	 * @return level
	 */
	public final int getLevel()
    {
    	return level;
    }
    
	/**
	 * Returns the id of the Objective
	 * @return id
	 */
	public final int getId()
	{
		return id;
	}
	
	/**
	 * Sets the ID of the objective to the one passed through
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Removes the child from this Objective
	 * @param child
	 * @return removed
	 * If removed it will return false. otherwise return true
	 */
	public boolean removeChild(Child c) {
		boolean removed = false;
		int size = children.size();
		for (int i = 0; i < size; i++) {
			if (children.get(i).getChild() == c) {
				children.remove(i);
				return true;
			}
		}
		return removed;

	}

	/**
	 * Sets the Hidden attribute  to the boolean passed through.
	 * @param hidden
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Returns the hidden attribute in the Object.
	 * @return hidden
	 */
	public boolean getHidden() {
		return hidden;
	}
	
    public String toString()
    {
    	return name;
    }
    
}
