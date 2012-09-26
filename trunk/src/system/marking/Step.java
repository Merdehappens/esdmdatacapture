/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.marking;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author DAMIAN
 */

@Entity
public class Step {
    
	@Id
	@GeneratedValue
    private int id;
	@Column(nullable=false)
    private String no;
	@Column(nullable=false)
    private String code;
	@Basic
    private String description;
	@ManyToOne
	@JoinColumn(name="objective_id")
    private Objective objective;
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="step",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;

    /**
     * Creates a new Step object
     */
	public Step()
	{
    }
    
    /**
     * Creates a new step object with the No, Code and Description passed through
     * @param no
     * @param code
     * @param description
     */
    public Step(String no, String code, String description)
    {
    	this.no = no;
    	this.code = code;
    	this.description = description;
    }
    
    /**
     * Sets the objective of this Step to the one passed through
     * @param objective
     */
    public void setObjective(Objective objective)
    {
    	this.objective = objective;
    }
    
    /**
     * Returns the objective for this step
     * @return objective
     */
    final public Objective getObjective()
    {
    	return objective;
    }
    
    /**
     * Sets the ID of the step to the String passed through.
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * Sets the description of the step to the String passed through.
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
     * Sets the Number of the step to the String passed through.
     * @param no
     */
    public void setNo(String no)
    {
    	this.no = no;
    }
    
    /**
     * Sets the code of the Step to the String passed through.
     * @param code
     */
    public void setCode(String code)
    {
    	this.code = code;
    }
    
    /**
     * Returns the description of this step.
     * @return description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Returns the ID of this Step
     * @return id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Returns the code of this Step
     * @return code
     */
    public String getCode()
    {
    	return code;
    }
    
    /**
     * Returns the Number of this step
     * @return number
     */
    public String getNo()
    {
    	return no;
    }
    
    /**
     * Returns the list of marks for this step
     * @return marksList
     */
	public List<Mark> getMarks() {
		return marks;
	}

	/**
	 * Sets the list of marks to the one passed through.
	 * @param marks
	 */
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

    public String toString()
    {
    	return no;
    }
	
}
