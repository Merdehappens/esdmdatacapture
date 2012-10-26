package system.marking;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import system.sessions.Setting;

@Entity
public class ObjectiveType {
	
	@Id
	private String name;
    @OneToMany(targetEntity=Objective.class,
    		mappedBy="objType",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
	private List<Objective> objectives;
    
    @ManyToMany
    @JoinTable(name="SettingObjectiveType",
    		joinColumns={@JoinColumn(name="ObjectiveTypeID")},
    		inverseJoinColumns={@JoinColumn(name="SettingID")})
    private List<Setting> settings;
	
    /**
     * Creates a new ObjectiveType
     */
	public ObjectiveType()
	{
		objectives = new ArrayList<Objective>();
	}
	
	/**
	 * Creates a new objective type with the String passed through
	 * @param name
	 */
	public ObjectiveType(String name)
	{
		this.name = name;
		objectives = new ArrayList<Objective>();		
	}
	
	/**
	 * Returns the name of the ObjectiveType object
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name of the ObjectiveType object to the name passed through
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Returns the list of objectives that are in this object
	 * @return objectiveList
	 */
	public List<Objective> getObjectives()
	{
		return objectives;
	}

	/**
	 * Add an objective to this objectiveType object
	 * @param objective
	 */
	public void addObjective(Objective objective) {
		objectives.add(objective);
	}

	public String toString() {
		return name;
	}
		
}
