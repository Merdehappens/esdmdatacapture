
package system.individuals;


import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import system.helper.SimpleKey;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.sessions.Day;

@Entity
public class ChildObjective{

    int currentStep;
    Objective objective;
    Child child;
    
    public ChildObjective(Objective objective, Child child)
    {
    	this.objective = objective;
    	this.child = child;
    	currentStep = 0;
    }
    
    public ChildObjective(Objective objective, Child child, int currentStep)
    {
    	this.objective = objective;
    	this.child = child;
    	this.currentStep = currentStep;
    }

	public Objective getObjective() {
		return objective;
	}

	public void incrementStep() {
		currentStep++;
	}

	public int getStep() {
		return currentStep;
	}
        
}
