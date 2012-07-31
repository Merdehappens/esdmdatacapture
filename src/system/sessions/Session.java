
package system.sessions;


import java.util.ArrayList;
import java.util.List;

import system.helper.SimpleKey;
import system.marking.Objective;


public class Session implements SimpleKey {
    
    String id;
    String description;
    List<Objective> objectives;
    
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
}
