
package system.marking;


import java.util.Calendar;

import system.helper.SimpleKey;
import system.individuals.Child;
import system.individuals.Therapist;
import system.sessions.Day;
import system.sessions.Session;


public class Mark implements SimpleKey {
    private String id;
    private Child child;
    private int mark;
    private Day day;
    private Session session;
    private Objective objective;
    private Step step;
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

    public Mark(String id, Child child, Objective objective, int mark, Session session, 
    					Step step, Therapist therapist, Calendar time) {
        this.id = id;
        this.child = child;
        this.mark = mark;
        this.session = session;
        this.step = step;
        this.therapist = therapist;
        this.time = time;
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
	}

	public String getId()
    {
        return id;
    }
	
	
	public String toString()
	{
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
	
	public void setComments(String comment)
	{
		this.comment = comment;
	}
}
