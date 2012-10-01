
package system.individuals;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import system.marking.Objective;
import system.marking.Step;

@Entity
public class ChildObjective{
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name="objective_id")
	private Objective objective;
	@ManyToOne
	@JoinColumn(name="child_id")
    private Child child;
    @Basic
    private int currentStep;
    private boolean mastered;
    
    
    /**
     * Constructs a Child Objective object with the child and objective parsed through
     * sets current step to 1 and mastered to false
     * @param objective
     * @param child
     */
    public ChildObjective(Objective objective, Child child)
    {
    	this.objective = objective;
    	this.child = child;
    	currentStep = 1;
    	mastered = false;
    }

    /**
     * Constructs a Child Objective object with the child and objective parsed through & sets the step
     * to the step parsed through. sets mastered to false
     * @param objective
     * @param child
     * @param currentStep
     */
    public ChildObjective(Objective objective, Child child, int currentStep)
    {
    	this.objective = objective;
    	this.child = child;
    	this.currentStep = currentStep;
    	mastered = false;
    }
    
    /**
     * Creates a childobjective object with current step 1 and mastered false
     */
    public ChildObjective()
    {
    	currentStep = 1;
    	mastered = false;
    }

    /**
     * Returns the objective for this ChildObjective object
     * @return objective
     */
	public Objective getObjective() {
		return objective;
	}

	/**
	 * Takes in an int and increments the step by the int parsed though.
	 * Can be positive or negative.
	 * @param num
	 * @throws Exception
	 *  is thrown when num is 0, greater than the size or less than 0
	 */
	public void incrementStep(int num) throws Exception {
		int stepNum = currentStep + num;
		if(stepNum <= 0 || stepNum > objective.getStepsNo())
		{
			throw new Exception("10003: Cannot increment or decrement step any more");
		}
		else
		{
			currentStep = stepNum;
		}
	}

	/**
	 * returns the step of this ChildObjective object
	 * @return step
	 */
	public Step getStep() {
		return objective.getStep(currentStep);
	}

	/**
	 * Returns the id of this object
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this object.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the child attribute of this object
	 * @return child
	 */
	public Child getChild() {
		return child;
	}

	/**
	 * Sets the child attribute of this object to the one passed through.
	 * @param child
	 */
	public void setChild(Child child) {
		this.child = child;
	}

	/**
	 * Returns the step for the child and objective for this object
	 * @return step
	 */
	public int getCurrentStep() {
		return currentStep;
	}

	/**
	 * Sets the current step to the step parsed through
	 * @param currentStep
	 */
	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	/**
	 * Sets the objective to the objective passed through
	 * @param objective
	 */
	public void setObjective(Objective objective) {
		this.objective = objective;
	}

	/**
	 * returns the mastered attribute
	 * @return mastered
	 */
	public boolean getMastered() {
		return mastered;
	}
	
	/**
	 * Sets the mastered attribute to the one parsed through
	 * @param mastered
	 */
	public void setMastered(boolean mastered)
	{
		this.mastered = mastered;
	}
	
	public String toString()
	{
		return "Child: " + child + " Objective: " + objective + " Step " + currentStep
							+ " Mastered: " + mastered;
	}
        
}
