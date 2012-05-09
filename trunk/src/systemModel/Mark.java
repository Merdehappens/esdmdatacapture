/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

import java.util.Calendar;
import java.util.Date;

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
}
