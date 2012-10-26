
package system.marking;


import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import system.helper.SimpleKey;
import system.individuals.Child;
import system.individuals.Therapist;
import system.sessions.Day;
import system.sessions.Setting;

@Entity
public class Mark implements SimpleKey {
	@Id
	@GeneratedValue
    private int id;
	@Basic
	private char type;
	@ManyToOne
	@JoinColumn(name="child_id")
    private Child child;
    private int mark;
	@ManyToOne
	@JoinColumn(name="day_id")
    private Day day;
	@ManyToOne
	@JoinColumn(name="setting_id")
    private Setting setting;
	@ManyToOne
	@JoinColumn(name="objective_id")
    private Objective objective;
	@ManyToOne
	@JoinColumn(name="step_id")
    private Step step;
	@ManyToOne
	@JoinColumn(name="therapist_id")
    private Therapist therapist;
	
    private Calendar time;
    private String comment;
   
    /**
     * Creates the mark object with the int parsed through
     * @param mark
     */
    public Mark(int i)
	{
		mark = i;
	}
	
    /**
     * Creates a mark object with the attributes passed through. sets type to 'n' (normal)
     * @param setting
     * @param child
     * @param objective
     * @param step
     * @param mark
     * @param therapist
     * @param day
     * @param time
     */
	public Mark(Setting setting, Child child, Objective objective,
			Step step, int mark, Therapist therapist, Day day, Calendar time) {
		this.setting = setting;
		this.child = child;
		this.objective = objective;
		this.step = step;
		this.mark = mark;
		this.day = day;
		this.therapist = therapist;
		this.time = time;
		type = 'n';
	}
    
	/**
	 * Creates a mark object with the attributes passed through
	 * @param id
	 * @param mark
	 * @param child
	 * @param therapist
	 * @param type
	 */
    public Mark(int id, int mark, Child child, Therapist therapist, char type) {
    	this.id = id;
    	this.mark = mark;
    	this.child = child;
    	this.type = type;
    	this.therapist = therapist;
    }
    
    /**
     * Creates a mark object with the attributes passed through
     * @param mark
     * @param child
     * @param therapist
     * @param type
     * @param day
     */
    public Mark(int mark, Child child, Therapist therapist, char type, Day day) {
    	this.mark = mark;
    	this.child = child;
    	this.type = type;
    	this.therapist = therapist;
    	this.day = day;
    }
    
    /**
     * Creates a mark object with all the attributes set to null
     */
    public Mark()
    {
    }
    
    /**
     * Returns the child attribute inside the mark object
     * @return child
     */
    public Child getChild() {
		return child;
	}

    /**
     * Returns the actual mark int for this mark.
     * @return mark
     */
	public int getMark() {
		return mark;
	}
	
	/**
	 * Returns the day attribute in the mark object.
	 * @return day
	 */
	public Day getDay() {
		return day;
	}

	/**
	 * Returns the setting attribute in the mark object.
	 * @return setting
	 */
	public Setting getSetting() {
		return setting;
	}

	/**
	 * Returns the objective attribute in the mark object.
	 * @return objective
	 */
	public Objective getObjective() {
		return objective;
	}

	/**
	 * Returns the step attribute in the mark object.
	 * @return step
	 */
	public Step getStep() {
		return step;
	}

	/**
	 * Returns the therapist attribute in the mark object
	 * @return therapist
	 */
	public Therapist getTherapist() {
		return therapist;
	}

	/**
	 * Returns the time attribute in the mark object
	 * @return time
	 */
	public Calendar getTime() {
		return time;
	}

	/**
	 * Returns the comment attribute in the mark object
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Returns the ID attribute in the mark object
	 * @return id
	 */
	public int getId()
    {
        return id;
    }
	
	/**
	 * Sets the id attribute to the integer passed through
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Sets the comment attribute in the object to the String passed through
	 * @param comment
	 */
	public void setComments(String comment)
	{
		this.comment = comment;
	}

	/**
	 * Sets the setting attribute to the setting passed through
	 * @param setting
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	/**
	 * Sets the child attribute to the child passed through.
	 * @param child
	 */
	public void setChild(Child child) {
		this.child = child;
	}

	/**
	 * Sets the time attribute to the Calendar passed through.
	 * @param time
	 */
	public void setTime(Calendar time) {
		this.time = time;
	}

	/**
	 * sets the objective attribute to the Objective passed through.
	 * @param objective
	 */
	public void setObjective(Objective objective) {
		this.objective = objective;
	}
	
	/**
	 * Sets the mark attribute to the Integer passed through
	 * @param mark
	 */
	public void setMark(int mark)
	{
		this.mark = mark;
	}

	public void setStep(Step step) {
		this.step = step;
		objective = step.getObjective();
	}
	
	/**
	 * Sets the type attribute to the char passed through.
	 * @param type
	 */
	public void setType(char type) {
		this.type = type;
	}
	
	/**
	 * Returns the type attribute
	 * @return type
	 */
	public char getType() {
		return type;
	}
	
	public String toString()
	{
		if(type == 'b') {
			return "" + mark;
		} else {	
			if(mark == 0)
			{
				return "/";
			}
			else if(mark > 0)
			{
				return "+";
			}
			else
			{
				return "-";
			}
		}
		
	}
	
	
}
