
package system.sessions;


import java.util.ArrayList;
import java.util.List;

import system.helper.SimpleKey;
import system.marking.Objective;


public class Session implements SimpleKey {
    
	private String id;
	private String description;
	private List<Objective> objectives;
    
    public Session()
    {    
    }
    
    public Session(String id, String description)
    {
        this.id = id;
        this.description = description;
        objectives = new ArrayList<Objective>();
    }
    
    public String getId()
    {
        return id;
    }
    
    public String toString()
    {
    	return description;
    }
    
    public List<Objective> getObjectives()
    {
    	return objectives;
    }
}
