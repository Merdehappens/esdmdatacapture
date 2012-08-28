
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
    
    public ChildObjective(Objective objective, Child child)
    {
    	this.objective = objective;
    	this.child = child;
    	currentStep = 1;
    	mastered = false;
    }
    
    public ChildObjective(Objective objective, Child child, int currentStep)
    {
    	this.objective = objective;
    	this.child = child;
    	this.currentStep = currentStep;
    	mastered = false;
    }
    
    public ChildObjective()
    {
    	currentStep = 1;
    	mastered = false;
    }

	public Objective getObjective() {
		return objective;
	}

	// Takes in an int and increments the step by that int.
	// Can be either positive or negative. if 0 or less or greater than the size then 
	// throws exception
	public void incrementStep(int num) throws Exception {
		int stepNum = currentStep + num;
		if(stepNum <= 0 || stepNum > objective.getStepsNo())
		{
			throw new Exception("Cannot increment or decrement step any more");
		}
		else
		{
			currentStep = stepNum;
		}
	}

	public Step getStep() {
		return objective.getStep(currentStep);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}

	public boolean getMastered() {
		return mastered;
	}
	
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
