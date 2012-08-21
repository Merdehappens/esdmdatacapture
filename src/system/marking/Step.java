/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.marking;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import system.individuals.Child;

/**
 *
 * @author DAMIAN
 */

@Entity
public class Step {
    
	@Id
    private String id;
	@Column(nullable=false)
    private String no;
	@Column(nullable=false)
    private String code;
	@Basic
    private String description;
	@ManyToOne
	@JoinColumn(name="objective_id")
    private Objective objective;
	@Transient
	private Child child;
    @OneToMany(targetEntity=Mark.class,
    		mappedBy="step",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.LAZY)
    private List<Mark> marks;


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
    
    public void setObjective(Objective objective)
    {
    	this.objective = objective;
    }
    
    final public Objective getObjective()
    {
    	return objective;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void setNo(String no)
    {
    	this.no = no;
    }
    
    public void setCode(String code)
    {
    	this.code = code;
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
    
    public String toString()
    {
    	return no + " " + description;
    }
    
    public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	
	
}
