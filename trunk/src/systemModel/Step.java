/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;

/**
 *
 * @author DAMIAN
 */
public class Step {
    
    private String id;
    private String description;
    
    public Step()
    {
    }
    
    public Step(String id, String description)
    {
        this.id = id;
        this.description = description;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getId()
    {
        return id;
    }
    
    
    
}
