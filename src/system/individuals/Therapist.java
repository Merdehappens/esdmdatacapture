
package system.individuals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import system.marking.Mark;

@Entity
public class Therapist extends UserAccount {
    
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="therapist",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;

    /**
     * Creates a new therapist object
     */
    public Therapist()
    {
        super();
        marks = new ArrayList<Mark>();
    }
    
    /**
     * Returns a list of all the marks the therapist has marked
     * @return marksList
     */
	public List<Mark> getMarks() {
		return marks;
	}

	/**
	 * Creates a new therapist with the name, username and email address passed in.
	 * @param name
	 * @param username
	 * @param emailAddress
	 */
	public Therapist(String name, String username, String emailAddress) {
		super(name, username, emailAddress);
		marks = new ArrayList<Mark>();
	}
	
	/**
	 * Sets the therapists list of marks to the list passed through.
	 * @param marks
	 */
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
		
	/**
	 * Adds the Mark that is passed through to the therapists list of marks.
	 * @param mark
	 */
	public void addMark(Mark mark)
	{
		marks.add(mark);
	}
}
