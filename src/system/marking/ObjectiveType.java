package system.marking;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import system.individuals.ChildObjective;
import system.sessions.Session;

@Entity
public class ObjectiveType {
	
	@Id
	@GeneratedValue
	private int id;
	@Basic
	private String name;
    @OneToMany(targetEntity=Objective.class,
    		mappedBy="objType",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.EAGER)

	private List<Objective> objectives;
	
	public ObjectiveType()
	{
		objectives = new ArrayList<Objective>();
	}
	
	public ObjectiveType(String name)
	{
		this.name = name;
		objectives = new ArrayList<Objective>();		
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
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
	
}
