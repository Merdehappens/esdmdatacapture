
package system.marking;


import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import system.helper.SimpleKey;
import system.individuals.Child;
import system.individuals.Therapist;
import system.sessions.Day;
import system.sessions.Session;

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
	@JoinColumn(name="session_id")
    private Session session;
	@ManyToOne
	@JoinColumn(name="objective_id")
    private Objective objective;
    // ^ ^ ^ Transient, as database model does not have connection between Mark and Objective
	@ManyToOne
	@JoinColumn(name="step_id")
    private Step step;
	@ManyToOne
	@JoinColumn(name="therapist_id")
    private Therapist therapist;
	
    private Calendar time;
    private String comment;
    
    public Child getChild() {
		return child;
	}

	public int getMark() {
		return mark;
	}

	public Day getDay() {
		return day;
	}

	public Session getSession() {
		return session;
	}

	public Objective getObjective() {
		return objective;
	}

	public Step getStep() {
		return step;
	}

	public Therapist getTherapist() {
		return therapist;
	}

	public Calendar getTime() {
		return time;
	}

	public String getComment() {
		return comment;
	}

	public Mark(int i)
	{
		mark = i;
	}
	
	/*
	 * Creates a new mark object with the time that is parsed through
	 */

    public Mark(int id, Child child, Objective objective, int mark, Session session, 
    					Step step, Therapist therapist, Calendar time) {
        this.id = id;
        this.child = child;
        this.mark = mark;
        this.session = session;
        this.step = step;
        this.therapist = therapist;
        this.time = time;
        type = 'n';
    }
    
    public Mark(int id, int mark, Child child, Therapist therapist, char type) {
    	this.id = id;
    	this.mark = mark;
    	this.child = child;
    	this.type = type;
    	this.therapist = therapist;
    }
    
    public Mark(int mark, Child child, Therapist therapist, char type) {
    	this.mark = mark;
    	this.child = child;
    	this.type = type;
    	this.therapist = therapist;
    }
    
    public Mark()
    {
    }
    
	/*
	 * Creates a new mark object with the time set to current system time
	 */
    
	public Mark(Session session, Child child, Objective objective,
			Step step, int mark, Therapist therapist, Day day) {
		this.session = session;
		this.child = child;
		this.objective = objective;
		this.step = step;
		this.mark = mark;
		this.day = day;
		this.therapist = therapist;
		time = Calendar.getInstance();
		type = 'n';
	}

	public int getId()
    {
        return id;
    }
	
	public void setId(int id) {
		this.id = id;
	}

	public String toString()
	{
		if(type == 'n') {
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
		} else {
			return "" + mark;
		}
		
	}
	
	public void setComments(String comment)
	{
		this.comment = comment;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}
	
	public void setMark(int mark)
	{
		this.mark = mark;
	}

	public void setStep(Step step) {
		this.step = step;
		objective = step.getObjective();
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	public char getType() {
		return type;
	}
	
	
}
