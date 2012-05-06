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
    private String no;
    private String code;
    private String description;
    
    public Step()
    {
    }
    
    public Step(String id, String description)
    {
        this.id = id;
        this.description = description;
    }
    
    public Step(String no, String code, String description)
    {
    	this.no = no;
    	this.code = code;
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
    
    public String getCode()
    {
    	return code;
    }
    
    public String getNo()
    {
    	return no;
    }
    
    
}
