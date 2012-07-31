
package system.individuals;

import system.helper.SimpleKey;
import BCrypt.BCrypt;


public class UserAccount implements SimpleKey {
    private String name;
    private String emailAddress;
    private String password;
    private String username;
    private String phoneNo;
    private boolean access;
    
    public UserAccount()
    {
    }
    
    public UserAccount(String name, String username, String emailAddress) {
		this.name = name;
		this.username = username;
		this.emailAddress = emailAddress;
	}
    
    public boolean getAccess()
    {
    	return access;
    }
    
    public void setAccess(boolean access)
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

    public void setPassword(String password) {
    	this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
   
}
