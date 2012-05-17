/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.marking;


import java.util.Calendar;

import system.helper.SimpleKey;
import system.individuals.Child;
import system.individuals.Therapist;
import system.sessions.Day;
import system.sessions.Session;

/**
 *
 * @author DAMIAN
 */
public class Mark implements SimpleKey {
    String id;
    Child child;
    int mark;
    Day day;
    Session session;
    Objective objective;
    Step step;
    Therapist therapist;
    Calendar time;
	String comment;
    
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



    public Mark(String id, Child child, int Mark, Session session, Step step, Therapist therapist, Calendar time) {
        this.id = id;
        this.child = child;
        this.mark = mark;
        this.session = session;
        this.step = step;
        this.therapist = therapist;
        this.time = time;
    }
    

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
		return id;
	}
	
	public void setComments(String comment)
	{
		this.comment = comment;
	}
}
