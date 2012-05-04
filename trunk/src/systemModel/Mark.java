/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

import java.util.Date;

/**
 *
 * @author DAMIAN
 */
public class Mark implements SimpleKey {
    String id;
    Child child;
    int Mark;
    Session session;
    Step step;
    Therapist therapist;
    Date time;

    public Mark(String id, Child child, int Mark, Session session, Step step, Therapist therapist, Date time) {
        this.id = id;
        this.child = child;
        this.Mark = Mark;
        this.session = session;
        this.step = step;
        this.therapist = therapist;
        this.time = time;
    }
    
    public String getId()
    {
        return id;
    }
}
