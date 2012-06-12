package system.marking;

import system.individuals.Child;

public class ObjectiveChild {

	private Child child;
	private Objective objective;
	private int masteryCriteria;
	private int step;
	
	public ObjectiveChild(Child child, Objective objective)
	{
		this.child = child;
		this.objective = objective;
		masteryCriteria = 0;
		step = -1;
	}
	
	public ObjectiveChild(Child child, Objective objective, int step)
	{
		this.child = child;
		this.objective = objective;
		masteryCriteria = 0;
		this.step = step;
	}
	
	public Child getChild()
	{
		return child;
	}
	
	public Objective getObjective()
	{
		return objective;
	}
	
	public int getStep()
	{
		return step;
	}
	
	public int getMastery()
	{
		return masteryCriteria;
	}
	
}
