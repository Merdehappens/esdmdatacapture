/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;



import BCrypt.BCrypt;

/**
 *
 * @author DAMIAN
 */
public class ESDMModel {
    
    private List<Child> childList;
    private Set<Setting> settingList;
    private Set<Session> sessionList;
    private List<Guardian> guardianList;
    private List<Therapist> therapistList;
    private Set<Objective> objectiveList;
    private Set<Mark> markList;
    private List<Day> dayList;
    private List<Room> roomList;
    private UserAccount currentUser;
    
    public ESDMModel() throws MalformedURLException
    {
        childList = new ArrayList<Child>();
        settingList = new HashSet<Setting>();
        sessionList = new HashSet<Session>();
        therapistList = new ArrayList<Therapist>();
        objectiveList = new HashSet<Objective>();
        guardianList = new ArrayList<Guardian>();
        markList = new HashSet<Mark>();
        roomList = new ArrayList<Room>();
        dayList = new ArrayList<Day>();
        currentUser = null;
        loadFromDatabase();
        
    }

    public List<Child> getChildList() {
        return childList;
    }
    
    public Collection<Room> getRoomList() {
    	return roomList;
    }
    
    public Collection<Setting> getSettingList() {
    	return settingList;
    }
    
    public Collection<Session> getSessionList() {
    	return sessionList;
    }
    
    public Collection<Objective> getObjectiveList() {
    	return objectiveList;
    }
    
    
    
    private void loadFromDatabase() throws MalformedURLException
    {
        Child child = new Child();
        child.setId("Temp1"); 
        child.setName("Temp1");
        child.setDob(new Date(2001, 4, 21));
        child.setPictureLink(new URL("http://www.hanselman.com/blog/content/binary/WindowsLiveWriter/CleanupyourTempFiles_8F63/TempFiles%5B7%5D.jpg"));
        childList.add(child);
        
        child = new Child();
        child.setId("Temp2");
        child.setName("Temp2");
        child.setDob(new Date(2004, 5, 22));
        child.setPictureLink(new URL("http://www.hanselman.com/blog/content/binary/WindowsLiveWriter/CleanupyourTempFiles_8F63/TempFiles%5B7%5D.jpg"));
        childList.add(child);
        
        roomList.add(new Room("Room 1"));
        roomList.add(new Room("Room 2"));
        
        sessionList.add(new Session("S01", "Session 1"));
        sessionList.add(new Session("S01", "Session 2"));
        sessionList.add(new Session("S01", "Session 3"));
        sessionList.add(new Session("S01", "Session 4"));
        sessionList.add(new Session("S01", "Session 5"));
        
        Therapist user = new Therapist();
        user.setPassword("Temporary");
        user.setUsername("Temporary");
        therapistList.add(user);
        
        user = new Therapist();
        user.setUsername("temp");
        user.setPassword("temp");
        therapistList.add(user);
        
        Guardian guardian = new Guardian();
        guardian.setName("Name1");
        guardian.setEmailAddress("EmailAddress1");
        guardian.setPhoneNo("Phone No 1");
        
        guardianList.add(guardian);
        
        guardian = new Guardian();
        guardian.setName("Name2");
        guardian.setEmailAddress("EmailAddress2");
        guardian.setPhoneNo("Phone No 2");
        
        guardianList.add(guardian);
    }
    

    public boolean login(String username, String password)
    {
        for(int i = 0; i < therapistList.size(); i++)
        {
        	UserAccount temp = therapistList.get(i);
        	
        	if(temp.getUsername().equals(username))
        	{
        		if(BCrypt.checkpw(password, temp.getPassword()))
        		{
        			currentUser = temp;
        			return true;
        		}
        		else
        		{
        			return false;
        		}
        	}
        		        		
        }
      
        return false;
    }
    
    public void setPassword(String temp)
    {
    	currentUser.setPassword(temp);
    }
    
    
    /****************************************************
     *  Returns true if a user is currently logged in   *
     *  False otherwise                                 *
     *                                                  *
     * **************************************************/
    
    public boolean loggedIn()
    {
        if(currentUser == null)
        {
            return false;
        }
        return true;
    }
    
    public Child addChild(String name, Date dob, Date dateJoined, ArrayList<Guardian> guardians)
    {
        Child child = new Child(name, dob, dateJoined);

        for(int i = 0; i < guardians.size(); i++)
        {
        	child.addGuardian(guardians.get(i));
        }
        
        childList.add(child);
        
        return child;
    }
    

    
	public Child viewChild(String childId)
    {
        Child child = (Child)Helper.search((Collection<Child>)childList, childId);
        
        return child;
    }
	
	
	
	public void addDay(Date date, ArrayList<Child> children, String room, ArrayList<Session> sessions)
	{
		
		
	}
	
	
	
	
	/* Takes in a String Name Which is the name of the objective, String description which is the description of the Objective
	 * 
	 *  it takes String[][] which are the steps.
	 *  (i.e. steps[0][0] is the code of the first step, steps[0][1] is the Description of the first step
	 *        steps[1][0] is the code of the second step, step [1][1] is the description of the second step. and so on.
	 */
	
	public void addObjective(String Name, String description, String[][] steps)
	{
	


	}

	public UserAccount getCurrentUser() {
		return currentUser;
	}

	public void setEmail(String email) {
		currentUser.setEmailAddress(email);
	}

	public void addGuardian(Guardian temp) {
		guardianList.add(temp);
	}

	public List<Guardian> getGuardianList() {
		return guardianList;
	}

	public List<Day> getDayList() {
		return dayList;
	}
	
	
	
}
