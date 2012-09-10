package system.marking;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import system.sessions.Setting;

@Entity
public class ObjectiveType {
	
	/*@Id
	@GeneratedValue
	private int id;*/
	@Id
	private String name;
	
    @OneToMany(targetEntity=Objective.class,
    		mappedBy="objType",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
	private List<Objective> objectives;
    
    @ManyToMany
    @JoinTable(name="SettingObjectiveType",
    		joinColumns={@JoinColumn(name="ObjectiveID")},
    		inverseJoinColumns={@JoinColumn(name="SettingID")})
    private List<Setting> settings;
	
	public ObjectiveType()
	{
		objectives = new ArrayList<Objective>();
	}
	
	public ObjectiveType(String name)
	{
		this.name = name;
		objectives = new ArrayList<Objective>();		
	}
	
	public String toString() {
		return name;
	}
	
	/*public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}*/
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<Objective> getObjectives()
	{
		return objectives;
	}

	public void addObjective(Objective objective) {
		objectives.add(objective);
	}
	
}
