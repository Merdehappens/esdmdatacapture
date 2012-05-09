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
    private List<Objective> objectiveList;
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
        objectiveList = new ArrayList<Objective>();
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
    
    public List<Objective> getObjectiveList() {
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
        
        Objective objective = new Objective("Expressive", "Objective Description");
        Step step = new Step("1", "Code1", "DOES THIS STUFF");
        objective.addSteps(step);
        step = new Step("2", "Code2", "DOES THIS STUFF2");
        objective.addSteps(step);
        step = new Step("3", "Code3", "DOES THIS STUF3F");
        objective.addSteps(step);
        step = new Step("4", "Code4", "DOES THIS STUFF4");
        objective.addSteps(step);
        
        objectiveList.add(objective);
        child.addObjective(objective);
        
        objective = new Objective("Responsive", "Objective Description");
        step = new Step("1", "Code1", "DOES THISadsadsFF");
        objective.addSteps(step);
        step = new Step("2", "Code2", "DOES THISadsads2");
        objective.addSteps(step);
        step = new Step("3", "Code3", "DOES THISdas");
        objective.addSteps(step);
        step = new Step("4", "Code4", "DOES THasdadsUFF4");
        objective.addSteps(step);        
        
        objectiveList.add(objective);
        child.addObjective(objective);
        
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
	
	
	
	public Day addDay(Date date, ArrayList<Child> children, Room room, ArrayList<Session> sessions)
	{

		//make new constructor for these parameters//
		Day day = new Day(date, room);
		for (int i = 0; i < children.size(); i++)
		{
			day.addChildren(children.get(i)); //adds selected children to this day
		}
		for (int x = 0; x < sessions.size(); x++)
		{
			day.addSession(sessions.get(x));
		}
		dayList.add(day);
		
		
		return day;
	}
	
	
	
	
	/* Takes in a String Name Which is the name of the objective, String description which is the description of the Objective
	 * 
	 *  it takes String[][] which are the steps.
	 *  (i.e. steps[0][0] is the code of the first step, steps[0][1] is the Description of the first step
	 *        steps[1][0] is the code of the second step, step [1][1] is the description of the second step. and so on.
	 */
	
	public void addObjective(String Name, String description, String[][] steps)
	{
	
		//iterate through string//
		//create step for each pair//
		//create objective with these steps//
		Objective o = new Objective(Name, description);
		for (int i = 0; i < steps.length; i++)
		{
			String no = (i + 1) + "";
			Step step = new Step(no, steps[i][0], steps[i][1]);//retrieves info from 2D array and makes a new step
			o.addSteps(step);
		}
		
		objectiveList.add(o);
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

	public void changePassword(String oldPassword, String newPassword1, String newPassword2) throws Exception {

			if(newPassword1.equals(newPassword2))
			{
				if(BCrypt.checkpw(oldPassword, currentUser.getPassword()))
				{
					currentUser.setPassword(newPassword2);
				}
				else
				{
					throw new Exception("Incorrect Password");
				}
			}
			else
			{
				throw new Exception("Both Passwords do not match");
			}
	}
	
	public void addMark(Session session, Child child, Objective objective, Step step, int mark, Day day)
	{
		Mark tempMark = new Mark(session, child, objective, step, mark, (Therapist)currentUser, day);
		markList.add(tempMark);
	}
	
	
	
}
