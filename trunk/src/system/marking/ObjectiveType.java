package system.marking;
import java.util.ArrayList;
import java.util.List;
import system.sessions.Session;

public class ObjectiveType {
	private String id;
	private String name;
	// Stores the objectives that are this type
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
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
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
