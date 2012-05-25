/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author DAMIAN
 */

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
        loadFromDatabase();
    }

    public List<Child> getChildList() {
        return childList;
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
        
        Calendar c = Calendar.getInstance();
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
    

    
	public Child viewChild(String childId) throws Exception
    {
        Child child = (Child)Helper.search((Collection<Child>)childList, childId);
        
        if(child == null)
        {
        	throw new Exception("Child Id not found");
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
			throw new Exception("Date must be entered");
		}

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
	
	public void addObjective(String Name, String description, String[][] steps) throws Exception
	{
	
		//iterate through string//
		//create step for each pair//
		//create objective with these steps//
		if(steps.length == 0)
		{
			throw new Exception("No steps have been added");
		}
		
		Objective o = new Objective(Name, description);
		for (int i = 0; i < steps.length; i++)
		{
			if(steps[i][0].equals("") || steps[i][1].equals(""))
			{
				throw new Exception("one of the steps isn't completely filled");
			}
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
		if(oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals(""))
		{
			throw new Exception("One or more of the passwords was left empty");
		}
		else if(newPassword1.equals(newPassword2))
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
	
	public void addMark(Session session, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		if(session == null)
		{
			throw new Exception("Session not Selected");
		}
		
		if(child == null)
		{
			throw new Exception("Child not Selected");
		}
		
		if(objective == null)
		{
			throw new Exception("Objective not Selected");
		}
		
		if(step == null)
		{
			throw new Exception("Step not Selected");
		}
		
		if(mark > 1 || mark < -1)
		{
			throw new Exception("Mark not selected");
		}
		
		Mark tempMark = new Mark(session, child, objective, step, mark, (Therapist)currentUser, day);
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);
	}

	//searches through child list and returns child with the ID specified
	
	public Child getChild(String childId) {
		return Helper.search(childList, childId);
	}
	
	public List<Day> getDays(Date from, Date to)
	{
		ArrayList<Day> days = new ArrayList<Day>();
		
		if(from == null && to == null)
		{
			return dayList;
		}
/*		else
		{

			
			if(from == null)
			{
				for(int i = 0; i < dayList.size(); i++)
				{
					if(dayList.get(i).getDate().compareTo(to) > 0)
					{
						days.add(dayList.get(i));
					}
					
				}
			}
			else if(to == null)
			{
			
				for(int i = 0; i < dayList.size(); i++)
				{
					if(dayList.get(i).getDate().compareTo(from) > 0)
					{
						days.add(dayList.get(i));
					}
					
				}
				
			}
			else
			{
				for(int i = 0; i < dayList.size(); i++)
				{
					Day temp = dayList.get(i);
					if(temp.getDate().compareTo(from) < 0 && temp.getDate().compareTo(to) > 0)
					{
						days.add(temp);
					}
				}
				
			}
			
			return days;
		}*/
		
		return dayList;
		
		
		
	}
	
	
}
