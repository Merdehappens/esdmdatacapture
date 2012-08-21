
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
public class Child implements SimpleKey {
    @Id
    @Column(name="ChildID")
	private String id;
    private String name;
    private Calendar dateJoined;
    private Calendar dob;
    @ManyToMany
    @JoinTable(name="GuardianChild",
		joinColumns={@JoinColumn(name="ChildID")},
		inverseJoinColumns={@JoinColumn(name="name")})
    private List<Guardian> guardians;
    @ManyToMany
    @JoinTable(name="ChildObjective",
    		joinColumns={@JoinColumn(name="ChildID")},
    		inverseJoinColumns={@JoinColumn(name="ObjectiveID")})
    private List<Objective> objectives;
    @Transient
    private List<Integer> currentStep;
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="child",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;
    @ManyToMany
    @JoinTable(name="DayChild",
    		joinColumns={@JoinColumn(name="ChildID")},
    		inverseJoinColumns={@JoinColumn(name="DayID")})
    private List<Day> days;
    @Transient
    private ImageIcon picture;
    @Transient
    private URL pictureLink;
    private boolean active;
    
    public Child()
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
        currentStep = new ArrayList<Integer>();
        marks = new ArrayList<Mark>();
        active = true;
    }
    
    public Child(String name, Calendar dob, Calendar dateJoined)
    {
        guardians = new ArrayList<Guardian>();
        objectives = new ArrayList<Objective>();
        marks = new ArrayList<Mark>();
        currentStep = new ArrayList<Integer>();
        this.name = name;
        this.dob = dob;
        this.dateJoined = dateJoined;
        active = true;
    }

    public List<Integer> getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(List<Integer> currentStep) {
		this.currentStep = currentStep;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	public void setObjectives(List<Objective> objectives) {
		this.objectives = objectives;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public void setPicture(ImageIcon picture) {
		this.picture = picture;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public final Calendar getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Calendar dateJoined) {
        this.dateJoined = dateJoined;
    }

    public final Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public final List<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(ArrayList<Guardian> guardians) {
        this.guardians = guardians;
    }
    
    // Adds a guardian to the list of guardians
    public void addGuardian(Guardian guardian)
    {
        guardians.add(guardian);
    }
    
    // adds a mark to the list of marks
    public void addMark(Mark mark)
    {
    	marks.add(mark);
    }

    public final String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }
    
    // Increments the current step of the objective parsed through by one
    public void incrementStep(Objective o) throws Exception
    {
    	int size = objectives.size();
    	int x = -1;
    	
    	for(int i = 0; i < size; i++)
    	{
    		if(objectives.get(i) == o)
    		{
    			objectives.get(i).getStepsNo();
    			x = currentStep.get(i);
    			x++;
    			if(objectives.get(i).getStepsNo() >= x)
    			{
    				currentStep.set(i, x);
    			}
    			else
    			{
    				throw new Exception("Already at the maximum step for this objective");
    			}
    			break;
    		}
    	}
    	
    	if(x == -1)
    	{
    		throw new Exception("Could not find objective to increment step of");
    	}
    }

    public void setName(String name) throws Exception {
        if(name.equals(""))
        {
        	throw(new Exception("The child's name cannot be empty."));
        }
    	this.name = name;
    }
    
    public void addObjective(Objective o)
    {
        objectives.add(o);
        currentStep.add(0);
    }
    
    final public int getCurrentStep(Objective o)
    {
    	int size = objectives.size();
    	
    	for(int i = 0; i < size; i++)
    	{
    		if(objectives.get(i) == o)
    		{
    			return currentStep.get(i);
    		}
    	}
    	
    	return -1;
    
    }
    
    /* This method takes in a URL. and sets the URL in the child object to that URL and sets the childs picture to be the
     * picture that the URL points to
     */
    
    public void setPictureLink(URL pictureLink) {
        this.pictureLink = pictureLink;
        picture = new ImageIcon(pictureLink);
    }
    
    public final URL getPictureLink()
    {
        return pictureLink;
    }
    
    public final ImageIcon getPicture()
    {
        return picture;
    }
    
    public String toString()
    {
        return name;
    }

	public final List<Objective> getObjectives() {
		return objectives;
	}

	public final List<Mark> getMarks() {
		return marks;
	}
	
	public void setInactive()
	{
		active = false;
	}
	
	public void setActive()
	{
		active = true;
	}

	public final boolean getActive() {
		return active;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}
    
    
}
