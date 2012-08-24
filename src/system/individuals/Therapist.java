
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
    
    public Therapist()
    {
        super();
        marks = new ArrayList<Mark>();
    }
    
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="therapist",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;

	public List<Mark> getMarks() {
		return marks;
	}

	public Therapist(String name, String username, String emailAddress) {
		super(name, username, emailAddress);
		marks = new ArrayList<Mark>();
	}
	
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
		
	public void addMark(Mark mark)
	{
		marks.add(mark);
	}
}
