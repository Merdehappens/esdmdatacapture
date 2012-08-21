
package system.individuals;

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
    }
    
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="therapist",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
}
