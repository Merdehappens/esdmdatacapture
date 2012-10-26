package system.individuals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import system.helper.SimpleKey;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.sessions.Day;

@Entity
public class Child implements SimpleKey {
	@Id
	@GeneratedValue
	@Column(name = "ChildID")
	private int id;
	private String name;
	private Calendar dateJoined;
	private Calendar dob;
	@ManyToMany
	@JoinTable(name = "GuardianChild", joinColumns = { @JoinColumn(name = "ChildID") }, inverseJoinColumns = { @JoinColumn(name = "username") })
	private List<Guardian> guardians;
	@OneToMany(targetEntity = ChildObjective.class, mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ChildObjective> objectives;
	@OneToMany(targetEntity = Mark.class, mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Mark> marks;
	@ManyToMany
	@JoinTable(name = "DayChild", joinColumns = { @JoinColumn(name = "ChildID") }, inverseJoinColumns = { @JoinColumn(name = "DayID") })
	private List<Day> days;
	@Basic
	private String pictureLink;
	private boolean active;

	
	/**
	 * Creates a child object, creates the Guardians, Objectives and Marks list, sets active to true
	 * 
	 */
	public Child() {
		guardians = new ArrayList<Guardian>();
		objectives = new ArrayList<ChildObjective>();
		marks = new ArrayList<Mark>();
		active = true;
	}

	
	/**
	 * Creates a child object,  creates the Guardians, Objectives and Marks list, sets active to true
	 * @param name
	 *  Sets the childs name to the name passed through
	 * @param dob
	 *  Sets the childs date of birth to the date passed through
	 * @param dateJoined
	 *  Sets the childs date joined to the date passed through
	 */
	public Child(String name, Calendar dob, Calendar dateJoined) {
		guardians = new ArrayList<Guardian>();
		objectives = new ArrayList<ChildObjective>();
		marks = new ArrayList<Mark>();
		this.name = name;
		this.dob = dob;
		this.dateJoined = dateJoined;
		active = true;
	}

	/**
	 * Sets the list of guardians to the one passed through
	 * 
	 * @param guardians
	 */
	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	/**
	 * Adds the objectives passed through to the child
	 * 
	 * @param childObjectives
	 */
	public void setObjectives(List<Objective> childObjectives) {
		int size = childObjectives.size();
		// Iterates through the list and for each objective creates a Child
		// Objective object
		for (int i = 0; i < size; i++) {
			ChildObjective co = new ChildObjective(childObjectives.get(i), this);
			objectives.add(co);
		}
	}

	/**
	 * Sets the marks list to the list passed through
	 * @param marks
	 */
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}


	/**
	 * Sets the active attribute to the one passed through
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Returns a Calendar object of the date joined
	 * @return dateJoined
	 */
	public final Calendar getDateJoined() {
		return dateJoined;
	}

	/**
	 * Sets the date joined to the Calendar passed through
	 * @param dateJoined
	 */
	public void setDateJoined(Calendar dateJoined) {
		this.dateJoined = dateJoined;
	}

	/**
	 * Returns the date of birth of the child
	 * @return dateOfBirth
	 */
	public final Calendar getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth to the Calendar passed through
	 * @param dob
	 */
	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	/** 
	 * Returns the list of guardians from this child object
	 * @return guardianList
	 */
	public final List<Guardian> getGuardians() {
		return guardians;
	}

	/**
	 * Sets the list of guardians to the one passed through
	 * @param guardians
	 */
	public void setGuardians(ArrayList<Guardian> guardians) {
		this.guardians = guardians;
	}

	/**
	 * Adds the passed through guardian to the list of guardians
	 * @param guardian
	 */
	public void addGuardian(Guardian guardian) {
		guardians.add(guardian);
	}

	/**
	 * Adds the passed through mark to the lits of marks
	 * @param mark
	 */
	public void addMark(Mark mark) {
		marks.add(mark);
	}
	

	/**
	 * returns the ID of the child
	 * @return id
	 */
	public final int getId() {
		return id;
	}
	
	/**
	 * Sets the childs ID to the id passed through
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of the child
	 * @return name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Increments the current step of the objective parsed through by one
	 * @param objective
	 * @param number
	 * @throws Exception
	 *  if number isn't -1 or +1 an Exception is thrown
	 */
	public void incrementStep(Objective o, int num) throws Exception {
		// If the int isn't either + or - 1 exception is thrown
		if (num != 1 && num != -1) {
			throw new IllegalArgumentException("The number parsed through was not valid");
		}
		int size = objectives.size();
		boolean x = false;

		for (int i = 0; i < size; i++) {
			if (objectives.get(i).getObjective() == o) {
				objectives.get(i).incrementStep(num);
				x = true;
				break;
			}
		}
		// If unable to find the objective in the childs list of objectives
		// throw an exception
		if (!(x)) {
			throw new Exception("10002: The objective does not exist in that child");
		}

	}

	/**
	 * Sets the name of the child to the name parsed through
	 * @param name
	 * @throws Exception
	 * If name is empty or null an Exception is thrown
	 */
	public void setName(String name) throws Exception {
		if (name.equals("")) {
			throw new IllegalArgumentException("30001: The child's name cannot be empty.");
		}
		this.name = name;
	}

	// Takes in an objective and creates a new CHildObjective out of it and adds
	// it to the childs childobjectives list
	
	/**
	 * Creates a new Child Objective and adds it to the childs list
	 * @param o
	 *  Uses this objective to create the child objective from
	 */
	public void addObjective(Objective o) {
		ChildObjective co = new ChildObjective(o, this);
		objectives.add(co);
	}

	/**
	 * Adds the parsed through ChildObjective to the objectives list
	 * @param co
	 */
	public void addChildObjective(ChildObjective co) {
		objectives.add(co);
	}

	/**
	 * Get the current step of the objective passed through
	 * @param objective 
	 * @return step 
	 *  the step that this child is up to for this objective
	 */
	final public Step getCurrentStep(Objective o) {
		int size = objectives.size();

		for (int i = 0; i < size; i++) {
			if (objectives.get(i).getObjective() == o) {
				return objectives.get(i).getStep();
			}
		}

		return null;

	}

	public String toString() {
		return name;
	}

	
	/**
	 * Returns a list of all the objectives that are in this child.
	 * @return listObjective
	 */
	public final List<Objective> getObjectives() {
		int size = objectives.size();
		ArrayList<Objective> o = new ArrayList<Objective>();

		for (int i = 0; i < size; i++) {
			o.add(objectives.get(i).getObjective());
		}

		return o;
	}

	/**
	 * Returns the childs list of marks.
	 * @return listMarks
	 */
	public final List<Mark> getMarks() {
		return marks;
	}

	/**
	 * Sets the active attribute to false (Inactive)
	 */
	public void setInactive() {
		active = false;
	}

	/**
	 * Sets the active attribute to true (Active)
	 */
	public void setActive() {
		active = true;
	}

	/**
	 * Returns the active attribute of the child
	 * @return active
	 */
	public final boolean getActive() {
		return active;
	}

	/**
	 *  Returns a list of day objects that this child has participated in
	 * @return listDays
	 */
	public List<Day> getDays() {
		return days;
	}
	
	/**
	 * Sets the list of days inside this child to the list passed in.
	 * @param days
	 */
	public void setDays(List<Day> days) {
		this.days = days;
	}

	/**
	 * Returns the list of ChildObjective objects in this child
	 * @return childObjectivesList
	 */
	public List<ChildObjective> getChildObjectives() {
		return objectives;
	}

	/**
	 * Removes the passed through objective from this child
	 * @param objective
	 * @return childObjective
	 *  Returns the childObjective object that this objective was in.
	 */
	public ChildObjective removeObjective(Objective o) {
		ChildObjective co = null;
		int size = objectives.size();
		for (int i = 0; i < size; i++) {
			if (objectives.get(i).getObjective() == o) {
				co = objectives.remove(i);
				o.removeChild(this);
				break;
			}
		}
		return co;

	}

	/**
	 * Sets the mastered attribute for the childObjective object
	 * @param objective
	 *  the Objective you wish to change mastered for
	 * @param mastered
	 *  a boolean to set mastered on.
	 * @throws Exception
	 *  Objective doesn't exist in this child throws an Exception
	 */
	public void setMastered(Objective o, boolean b) throws Exception {
		int size = objectives.size();
		boolean x = false;
		for (int i = 0; i < size; i++) {
			if (objectives.get(i).getObjective() == o) {
				objectives.get(i).setMastered(b);
				x = true;
				break;
			}
		}
		if (!x) {
			throw new Exception("10002: This objective does not exist for this child");
		}
	}


	public void removeGuardian(Guardian guardian) {
		guardians.remove(guardian);
	}

	
}
