
package system.individuals;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import system.helper.Helper;
import BCrypt.BCrypt;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class UserAccount {
    @Id
	private String username;
    @Basic
    private String emailAddress;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String name;
    @Basic
    private String phoneNo;
    @Basic
    private String access;
    
    public UserAccount()
    {
    	access = "n";
    }
    
    public UserAccount(String name, String username, String emailAddress) {
		this.name = name;
		this.username = username;
		this.emailAddress = emailAddress;
		access = "n";
	}
    
    public String getAccess()
    {
    	return access;
    }
    
    public void setAccess(String access)
    {
    	this.access = access;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String toString()
    {
    	return username;
    }


	public String getId() {
    	return username;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    
    /*
     * a plain-text String that is the password is parsed in and then the password gets hashed and stored in the 
     * password attribute
     */

    public void setPassword(String password) throws Exception {
    	if(!(Helper.checkPasswordLength(password)))
    	{
    		throw new Exception("Password is not enough letters");
    	}
    	this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public void tempSetPassword(String password) {
    	this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public void setHashedPassword(String password) {
    	this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
   
}
