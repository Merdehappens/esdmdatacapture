
package system.model;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import system.helper.Helper;
import system.individuals.Child;
import system.individuals.Guardian;
import system.individuals.Therapist;
import system.individuals.UserAccount;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.sessions.Day;
import system.sessions.Session;



import BCrypt.BCrypt;


public class ESDMModel {
    
    private List<Child> childList;
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
        sessionList = new HashSet<Session>();
        therapistList = new ArrayList<Therapist>();
        objectiveList = new ArrayList<Objective>();
        guardianList = new ArrayList<Guardian>();
        markList = new HashSet<Mark>();
        roomList = new ArrayList<Room>();
        dayList = new ArrayList<Day>();
        currentUser = null;
        try {
			loadFromDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /*
     * Returns the list of all children in the system
     */

    public List<Child> getChildList() {
        return childList;
    }
    
    /*
     * Returns a list of all children in the system where the boolean parsed in is equal to the childs active attribute
     */
    
    public List<Child> getChildList(boolean active){
    	ArrayList<Child> tempChildList = new ArrayList<Child>();
    	
    	Child child;
    	
    	int size = childList.size();
    	for(int i = 0; i < size; i++)
    	{
    		child = childList.get(i);
    		
    		if(child.getActive() == active)
    		{
    			tempChildList.add(child);
    		}
    	}
    	
    	return tempChildList;
    	
    }
    
    /*
     * Returns all children in the list whose name contain the String parsed through.
     */
    
    public List<Child> getChildList(String filter)
    {
    	ArrayList<Child> newChildList = new ArrayList<Child>();
    	
    	int size = childList.size();
    	for(int i = 0; i < size; i++)
    	{
    		Child c = childList.get(i);
    		if(c.getName().contains(filter))
    		{
    			newChildList.add(c);
    		}
    	}
    	
    	return newChildList;
    }
    
    public Collection<Room> getRoomList() {
    	return roomList;
    }
    
    
    public Collection<Session> getSessionList() {
    	return sessionList;
    }
    
    public List<Objective> getObjectiveList() {
    	return objectiveList;
    }
    
    /* 
     * Dummy database load until database is implemented
     */
    
    private void loadFromDatabase() throws Exception
    {
        Child child = new Child();
        child.setId("C001"); 
        child.setName("John Doe");
        Calendar c = Calendar.getInstance();
        c.set(1991, 07, 21);
        child.setDob(c);
        child.setPictureLink(new URL("http://www.hanselman.com/blog/content/binary/WindowsLiveWriter/CleanupyourTempFiles_8F63/TempFiles%5B7%5D.jpg"));
        childList.add(child);
        
        child = new Child();
        child.setId("C002");
        child.setName("Sally Smith");
        c = Calendar.getInstance();
        c.set(1992, 11, 01);
        child.setDob(c);
        child.setPictureLink(new URL("http://www.hanselman.com/blog/content/binary/WindowsLiveWriter/CleanupyourTempFiles_8F63/TempFiles%5B7%5D.jpg"));
        childList.add(child);
        
        child = new Child();
        child.setId("C003");
        child.setName("Billy May");
        c = Calendar.getInstance();
        c.set(1995, 0, 04);
        child.setDob(c);
        childList.add(child);
        
        Objective objective = new Objective("Looks at indicated pictures as adult points to picture in book", 
        		"In activities with books and puzzles, when an adult points (touching or proximal point 6? or less) to a picture child will visually orient and/or grasp or tap pictures or objects that the adult is pointing to 80% of opportunities for 4 of 5 consecutive days across 3 or more adults and settings.", 1);
        Step step = new Step("1", "MC", "Orients and grasp/tap w/ partial physical prompt 50% opp");
        objective.addSteps(step);
        step = new Step("2", "MC", "Orients and/or grasp/tap 50% of opp");
        objective.addSteps(step);
        step = new Step("3", "MC", "Orients and/or grasp/tap 80% of opp");
        objective.addSteps(step);
        
        for(int i = 0; i < 2; i++)
        {
        	childList.get(i).addObjective(objective);
        }
        objectiveList.add(objective);
        
        objective = new Objective("Combines functionally related actions on a play theme", 
        		"During pretend play with an adult, when provided with the materials for a play sequence (eg. bathing, cooking, eating), child will spontaneously link functionally related actions on a play sequence for 3 different play themes over 4 consecutive days", 9);
        
        step = new Step("1", "MC", "Combines 2 related actions after adult model & ver P 50%");
        objective.addSteps(step);
        step = new Step("2", "MC", "Combines 2 related actions after adult model & ver P 80%");
        objective.addSteps(step);
        step = new Step("3", "MC", "Combines 2 related actions 1 theme, verbal prompting");
        objective.addSteps(step);
        step = new Step("4", "MC", "Combines 2 related actions 1 theme");
        objective.addSteps(step);
        step = new Step("5", "MC", "Combines 2 related actions 2 themes");
        objective.addSteps(step);
        step = new Step("6", "MC", "Combines 2 related actions 3 themes, 4 days");
        objective.addSteps(step);
  
        
        objectiveList.add(objective);
        child.addObjective(objective);
        
        
        objective = new Objective("Uses a spoon", 
        		"During mealtimes, when child is eating a meal that requires the use of a spoon, after an adult models using the spoon correctly, child will be able to use the spoon to eat 5+ spoonfuls of her meal, on 80% of opportunities over 3 consecutive days with 2+ people.", 2);
        
        step = new Step("1", "MC", "Uses spoon when adult loads spoon 50%");
        objective.addSteps(step);
        step = new Step("2", "MC", "Uses spoon when adult loads spoon 80%");
        objective.addSteps(step);
        step = new Step("3", "MC", "Loads spoon with PPP, brings spoon to mouth independently, 80%");
        objective.addSteps(step);
        step = new Step("4", "MC", "Loads spoon & brings to mouth independently, verP, 80%");
        objective.addSteps(step);
        step = new Step("5", "MC", "Loads spoon & brings to mouth independently, 3 times in a row");
        objective.addSteps(step);
        step = new Step("6", "MC", "6.Loads spoon &  brings to mouth independently 5+ times in a row");
        objective.addSteps(step);
        
        childList.get(1).addObjective(objective);
        childList.get(2).addObjective(objective);
        objectiveList.add(objective);
        
        
        Room room = new Room("Room 1");
        
        roomList.add(room);
        roomList.add(new Room("Room 2"));
        
        sessionList.add(new Session("S01", "Group"));
        sessionList.add(new Session("S02", "Centers"));
        sessionList.add(new Session("S03", "Meals"));
        sessionList.add(new Session("S04", "Enrichment"));
        sessionList.add(new Session("S05", "One/One"));
        sessionList.add(new Session("S06", "Informal"));
        sessionList.add(new Session("S07", "Toilet"));
        
        Therapist user = new Therapist();
        user.setPassword("Temporary");
        user.setUsername("Temporary");
        therapistList.add(user);
        
        user = new Therapist();		
        user.setUsername("dbisignano");
        user.setPassword("temp");
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
        
        c = Calendar.getInstance();
        c.set(2012, 4, 10);
        Day day = new Day(c, "T01");
        day.setRoom(room);
        dayList.add(day);
        

        c = Calendar.getInstance();
        c.set(2012, 4, 22);
        day = new Day(c, "T02");
        day.setRoom(room);
        dayList.add(day);

        c = Calendar.getInstance();
        c.set(2012, 4, 24);
        day = new Day(c, "T03");
        day.setRoom(room);
        dayList.add(day);

        c = Calendar.getInstance();
        c.set(2012, 4, 27);
        day = new Day(c, "T04");
        day.setRoom(room);
        dayList.add(day);
        
        
        for(int i = 0; i < objectiveList.size(); i++)
        {
        	objectiveList.get(i).setId("O000" + i);
        }
    
    }
    
    /*
     * Checks the parsed through username and password and returns true if the password is correct for that user.
     * Returns false otherwise.
     */

    public boolean login(String username, String password)
    {
    	int size = therapistList.size();
        for(int i = 0; i < size; i++)
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
    
    /*
     * Sets the password of the user that is currently logged in to the system.
     */
    
    public void setPassword(String temp) throws Exception
    {
    	if(currentUser != null)
    	{
    		currentUser.setPassword(temp);
    	}
    	else
    	{
    		throw new Exception("No user is currently logged in.");
    	}
    }
    
    /*
     * Adds a new user account to the system.
     */
    
    public String addUser(String name, String username, String emailAddress, String phoneNo) throws Exception
    {
    	if(name.length() == 0)
    	{
    		throw new Exception("Name field must be filled in.");
    	}
    	if(username.length() == 0)
    	{
    		throw new Exception("Username field must be filled in.");
    	}
    	if(emailAddress.length() == 0)
    	{
    		throw new Exception("Email field must be filled in.");
    	}
    	if(phoneNo.length() == 0)
    	{
    		throw new Exception("Phone number field must be filled in.");
    	}
    	
    	UserAccount user = new UserAccount(name, username, emailAddress);
    	
    	String password = Helper.generateRandomString(8);
    	user.setPassword(password);

    	return password;
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
    
    /* 
     * Creates a new child object and Adds it to the system with the specified name,
     * dob, date joined and list of guardians
     */
    
    public Child addChild(String name, Calendar dob, Calendar dateJoined, ArrayList<Guardian> guardians) throws Exception
    {
    	
    	if(name.length() == 0)
    	{
    		throw new Exception("Name is a required field.");
    	}
    	
    	if(dob == null)
    	{
    		throw new Exception("Date Of Birth is a required field.");
    	}
    	
        Child child = new Child(name, dob, dateJoined);

        int size = guardians.size();
        for(int i = 0; i < size; i++)
        {
        	child.addGuardian(guardians.get(i));
        }
        
        childList.add(child);
        
        return child;
    }
    
    /* 
     * Returns child with the childID Specified
     */
    
	public Child viewChild(String childId) throws Exception
    {
        Child child = (Child)Helper.search((Collection<Child>)childList, childId);
        
        if(child == null)
        {
        	throw new Exception("Child ID not found.");
        }
        
        return child;
    }
	
	/* Creates a new Day object, takes a list of Children and Session
	 * objects and adds them to that Day
	 */
	
	public Day addDay(Calendar date, ArrayList<Child> children, Room room, ArrayList<Session> sessions) throws Exception
	{
		if(date == null)
		{
			throw new Exception("Date must be entered.");
		}
		if(children == null || children.size() == 0)
		{
			throw new Exception("No children have been selected for this day.");
		}
		if(sessions == null || sessions.size() == 0)
		{
			throw new Exception("No sessions have been selected for this day.");
		}

		Day day = new Day(date, room);
		int size = children.size();
		for (int i = 0; i < size; i++)
		{
			day.addChildren(children.get(i)); //adds selected children to this day
		}
		size = sessions.size();
		for (int x = 0; x < size; x++)
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
	
	public void addObjective(String name, String description, String[][] steps, int level) throws Exception
	{
	
		//iterate through string
		//create step for each pair
		//create objective with these steps
		if(steps.length == 0)
		{
			throw new Exception("No steps have been added.");
		}
		if(name.length() == 0)
		{
			throw new Exception("Name field is empty.");
		}
		if(description.length() == 0)
		{
			throw new Exception("Description field is empty.");
		}
		
		Objective o = new Objective(name, description, level);
		for (int i = 0; i < steps.length; i++)
		{
			if(steps[i][0].length() == 0 || steps[i][1].length() == 0)
			{
				throw new Exception("One of the steps isn't completely filled.");
			}
			String no = (i + 1) + "";
			Step step = new Step(no, steps[i][0], steps[i][1]);//retrieves info from 2D array and makes a new step
			o.addSteps(step);
		}
		
		objectiveList.add(o);
	}

	/*
	 * Returns the user that is currently logged in to the system
	 */
	
	public UserAccount getCurrentUser() {
		return currentUser;
	}

	/*
	 * Sets the email of the user that is currently logged into the system to the String parsed through
	 */
	
	public void setEmail(String email) {
		currentUser.setEmailAddress(email);
	}

	/*
	 * Adds a new guardian to the system
	 */
	
	public void addGuardian(Guardian temp) {
		guardianList.add(temp);
	}

	public List<Guardian> getGuardianList() {
		return guardianList;
	}

	public List<Day> getDayList() {
		return dayList;
	}
	
	/*
	 * Changes the password of the user that is currently logged in to the system to the new password parsed through
	 */
	
	public void changePassword(String oldPassword, String newPassword1, String newPassword2) throws Exception {
		if(oldPassword.equals(newPassword1))
		{
			throw new Exception("The new and old password are the same.");
		}
		if(oldPassword.length() == 0 || newPassword1.length() == 0 || newPassword2.length() == 0)
		{
			throw new Exception("One or more of the password fields were left empty.");
		}
		else if(newPassword1.equals(newPassword2))
		{
				if(BCrypt.checkpw(oldPassword, currentUser.getPassword()))
				{
					currentUser.setPassword(newPassword2);
				}
				else
				{
					throw new Exception("Incorrect Password.");
				}
		}
		else
		{
			throw new Exception("Both Passwords do not match.");
		}
	}
	
	/*
	 * Adds a new mark to the system.
	 */
	
	public void addMark(Session session, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		if(session == null)
		{
			throw new Exception("Session not Selected.");
		}
		
		if(child == null)
		{
			throw new Exception("Child not Selected.");
		}
		
		if(objective == null)
		{
			throw new Exception("Objective not Selected.");
		}
		
		if(step == null)
		{
			throw new Exception("Step not Selected.");
		}
		
		if(mark > 1 || mark < -1)
		{
			throw new Exception("Mark not selected.");
		}
		
		Mark tempMark = new Mark(session, child, objective, step, mark, (Therapist)currentUser, day);
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);
	}
	
	public void addTimestamp(Session session, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		Mark tempMark = new Mark(session, child, objective, step, mark, (Therapist)currentUser, day);
		tempMark.setComments("Timestamp Logged.");
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);
	
	}

	//searches through child list and returns child with the ID specified
	
	public Child getChild(String childId) {
		return Helper.search(childList, childId);
	}
	
	
	/*
	 * Returns a list of all days that are between (inclusive) the 2 dates parsed through.
	 */
	
	public List<Day> getDays(Calendar from, Calendar to)
	{


	    
		
		ArrayList<Day> days = new ArrayList<Day>();
		
		if(from == null && to == null)
		{
			return dayList;
		}
		else
		{
			int size = dayList.size();

			if(from == null)
			{
				to = Helper.setCalendarTimeNull(to);
			
				for(int i = 0; i < size; i++)
				{
					if(dayList.get(i).getDate().compareTo(to) <= 0)
					{
						days.add(dayList.get(i));
					}
					
				}
			}
			else if(to == null)
			{
				from = Helper.setCalendarTimeNull(from);
				for(int i = 0; i < size; i++)
				{
					if(dayList.get(i).getDate().compareTo(from) >= 0)
					{
						days.add(dayList.get(i));
					}
					
				}
			}
			else
			{
				to = Helper.setCalendarTimeNull(to);
				from = Helper.setCalendarTimeNull(from);
				
				for(int i = 0; i < dayList.size(); i++)
				{
					Day temp = dayList.get(i);
					if(temp.getDate().compareTo(from) >= 0 && temp.getDate().compareTo(to) <= 0)
					{
						days.add(temp);
					}
				}
				
			}
			
			return days;
		}
		
		
		
	}
	
	/*
	 * Sets the child object active attribute to false.
	 */

	public void removeChild(Child child) {
		child.setInactive();
	}

	
	
	public void updateChild(Child child, String name, Calendar dob, Calendar dateJoined) throws Exception {
		child.setName(name);
		child.setDob(dob);
		child.setDateJoined(dateJoined);
	}

	public void addObjectiveChild(Child child, Objective objective) throws Exception {
		if(child == null || objective == null)	
		{
			throw new Exception("Child not selected.");
		}
		else
		{
			ArrayList<Objective> objList = new ArrayList<Objective>(child.getObjectives());
			Objective obj = null;
			int size = objList.size();
			for(int i = 0; i < size; i++)
			{
				if(objList.get(i).getId() == objective.getId())
				{
					obj = objList.get(i);
					break;
				}
			}
			
			
			if(obj != null)
			{
				throw new Exception("This objective already exists for this child.");
			}
			else
			{
				child.addObjective(objective);
			}
		}
	}
	
	
}