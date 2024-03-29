
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
    
    /**
     * Creates a user account object and sets the access to n
     */
    public UserAccount()
    {
    	access = "n";
    }
    
    /**
     * Creates a user account object, sets the name email and username to the
     * strings passed through and sets access to n
     * @param name
     * @param username
     * @param emailAddress
     */
    public UserAccount(String name, String username, String emailAddress) {
		this.name = name;
		this.username = username;
		this.emailAddress = emailAddress;
		access = "n";
	}
    
    /**
     * Returns the access attribute for this UserAccount object.
     * @return access
     */
    public String getAccess()
    {
    	return access;
    }
    
    /**
     * Sets the access attribute for this UserAccount.
     * @param access
     */
    public void setAccess(String access)
    {
    	this.access = access;
    }

    /**
     * Returns the phone number attribute for this UserAccount
     * @return phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the phone number attribute to the String passed through.
     * @param phoneNo
     */
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
    		throw new IllegalArgumentException("10004: Password does not meet minimum complexity rules");
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
