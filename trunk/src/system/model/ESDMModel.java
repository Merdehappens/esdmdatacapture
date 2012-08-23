
package system.model;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import system.helper.Helper;
import system.individuals.Child;
import system.individuals.ChildObjective;
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
    private List<Objective> objectiveList;
    private List<UserAccount> userList;
    private Set<Mark> markList;
    private List<Day> dayList;
    private List<Room> roomList;
    private UserAccount currentUser;
    private AnnotationConfiguration config;
	private SessionFactory factory;
    
    public ESDMModel() throws MalformedURLException
    {
        childList = new ArrayList<Child>();
        sessionList = new HashSet<Session>();
        objectiveList = new ArrayList<Objective>();
        markList = new HashSet<Mark>();
        roomList = new ArrayList<Room>();
        dayList = new ArrayList<Day>();
        userList = new ArrayList<UserAccount>();
        currentUser = null;
        hibernateSetUp();
        try {
			loadFromDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void hibernateSetUp()
    {
    	config = new AnnotationConfiguration();
		config.addAnnotatedClass(Step.class);
		config.addAnnotatedClass(Objective.class);
		config.addAnnotatedClass(UserAccount.class);
		config.addAnnotatedClass(Therapist.class);
		config.addAnnotatedClass(Guardian.class);
		config.addAnnotatedClass(Child.class);
		config.addAnnotatedClass(Mark.class);
		config.addAnnotatedClass(Day.class);
		config.addAnnotatedClass(Session.class);
		config.addAnnotatedClass(ChildObjective.class);
		config.configure("hibernate.cfg.xml");
		
		//new SchemaExport(config).create(true, true);
		// ^ ^ ^ SchemaExport is commented out once all tables are created,
		// ^ ^ ^ uncommented when class annotations have been changed and
		// ^ ^ ^ therefore classes need to be added again.
		
		factory = config.buildSessionFactory();
    }
    
    public void modelExit()
    {
    	factory.close();
    }
    
    public AnnotationConfiguration getConfig() {
		return config;
	}

	public SessionFactory getFactory() {
		return factory;
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
    	//org.hibernate.Session session = factory.getCurrentSession();
    	//session.beginTransaction();
    	
        /*Child child1 = new Child();
        //child1.setId("C001"); 
        child1.setName("John Doe");
        Calendar c = Calendar.getInstance();
        c.set(1991, 07, 21);
        child1.setDob(c);
        child1.setPictureLink("http://www.hanselman.com/blog/content/binary/WindowsLiveWriter/CleanupyourTempFiles_8F63/TempFiles%5B7%5D.jpg");
        childList.add(child1);
        
        Child child2 = new Child();
        //child2.setId("C002");
        child2.setName("Sally Smith");
        c = Calendar.getInstance();
        c.set(1992, 11, 01);
        child2.setDob(c);
        child2.setPictureLink("http://www.hanselman.com/blog/content/binary/WindowsLiveWriter/CleanupyourTempFiles_8F63/TempFiles%5B7%5D.jpg");
        childList.add(child2);
        
        Child child3 = new Child();
        //child3.setId("C003");
        child3.setName("Billy May");
        c = Calendar.getInstance();
        c.set(1995, 0, 04);
        child3.setDob(c);
        childList.add(child3);
        
        Objective objective1 = new Objective("Looks at indicated pictures as adult points to picture in book", 
        		"In activities with books and puzzles, when an adult points (touching or proximal point 6? or less) to a picture child will visually orient and/or grasp or tap pictures or objects that the adult is pointing to 80% of opportunities for 4 of 5 consecutive days across 3 or more adults and settings.", 1);
        Step step1 = new Step("1", "MC", "Orients and grasp/tap w/ partial physical prompt 50% opp");
        objective1.addSteps(step1);
        step1.setObjective(objective1);
        Step step2 = new Step("2", "MC", "Orients and/or grasp/tap 50% of opp");
        objective1.addSteps(step2);
        step2.setObjective(objective1);
        Step step3 = new Step("3", "MC", "Orients and/or grasp/tap 80% of opp");
        objective1.addSteps(step3);
        step3.setObjective(objective1);
        
        for(int i = 0; i < 2; i++)
        {
        	childList.get(i).addObjective(objective1);
        }
        objectiveList.add(objective1);
        
        Objective objective2 = new Objective("Combines functionally related actions on a play theme", 
        		"During pretend play with an adult, when provided with the materials for a play sequence (eg. bathing, cooking, eating), child will spontaneously link functionally related actions on a play sequence for 3 different play themes over 4 consecutive days", 9);
        
        Step step4 = new Step("1", "MC", "Combines 2 related actions after adult model & ver P 50%");
        objective2.addSteps(step4);
        step4.setObjective(objective2);
        Step step5 = new Step("2", "MC", "Combines 2 related actions after adult model & ver P 80%");
        objective2.addSteps(step5);
        step5.setObjective(objective2);
        Step step6 = new Step("3", "MC", "Combines 2 related actions 1 theme, verbal prompting");
        objective2.addSteps(step6);
        step6.setObjective(objective2);
        Step step7 = new Step("4", "MC", "Combines 2 related actions 1 theme");
        objective2.addSteps(step7);
        step7.setObjective(objective2);
        Step step8 = new Step("5", "MC", "Combines 2 related actions 2 themes");
        objective2.addSteps(step8);
        step8.setObjective(objective2);
        Step step9 = new Step("6", "MC", "Combines 2 related actions 3 themes, 4 days");
        objective2.addSteps(step9);
        step9.setObjective(objective2);
  
        
        objectiveList.add(objective2);
        child3.addObjective(objective2);
        
        
        Objective objective3 = new Objective("Uses a spoon", 
        		"During mealtimes, when child is eating a meal that requires the use of a spoon, after an adult models using the spoon correctly, child will be able to use the spoon to eat 5+ spoonfuls of her meal, on 80% of opportunities over 3 consecutive days with 2+ people.", 2);
        
        Step step10 = new Step("1", "MC", "Uses spoon when adult loads spoon 50%");
        objective3.addSteps(step10);
        step10.setObjective(objective3);
        Step step11 = new Step("2", "MC", "Uses spoon when adult loads spoon 80%");
        objective3.addSteps(step11);
        step11.setObjective(objective3);
        Step step12 = new Step("3", "MC", "Loads spoon with PPP, brings spoon to mouth independently, 80%");
        objective3.addSteps(step12);
        step12.setObjective(objective3);
        Step step13 = new Step("4", "MC", "Loads spoon & brings to mouth independently, verP, 80%");
        objective3.addSteps(step13);
        step13.setObjective(objective3);
        Step step14 = new Step("5", "MC", "Loads spoon & brings to mouth independently, 3 times in a row");
        objective3.addSteps(step14);
        step14.setObjective(objective3);
        Step step15 = new Step("6", "MC", "6.Loads spoon &  brings to mouth independently 5+ times in a row");
        objective3.addSteps(step15);
        step15.setObjective(objective3);
        
        childList.get(1).addObjective(objective3);
        childList.get(2).addObjective(objective3);
        objectiveList.add(objective3);
        
        
        Room room = new Room("Room 1");
        
        roomList.add(room);
        roomList.add(new Room("Room 2"));
        
        //Session session1 = new Session("S01", "Group");
        //Session session2 = new Session("S02", "Centers");
        //Session session3 = new Session("S03", "Meals");
        //Session session4 = new Session("S04", "Enrichment");
        //Session session5 = new Session("S05", "One/One");
        //Session session6 = new Session("S06", "Informal");
        //Session session7 = new Session("S07", "Toilet");
        Session session1 = new Session("Group");
        Session session2 = new Session("Centers");
        Session session3 = new Session("Meals");
        Session session4 = new Session("Enrichment");
        Session session5 = new Session("One/One");
        Session session6 = new Session("Informal");
        Session session7 = new Session("Toilet");
        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);
        sessionList.add(session4);
        sessionList.add(session5);
        sessionList.add(session6);
        sessionList.add(session7);
 
        
        Therapist user1 = new Therapist();		
        user1.setUsername("dbisignano");
        user1.tempSetPassword("temp");
        user1.setName("Damian B");
        userList.add(user1);
        
        Therapist user2 = new Therapist();
        user2.setUsername("temp");
        user2.tempSetPassword("temp");
        user2.setAccess("n");
        user2.setName("Random Temp");
        userList.add(user2);
        

        Guardian guardian1 = new Guardian();
        guardian1.setUsername("Name 2");
        guardian1.setName("Name 2");
        guardian1.tempSetPassword("Name2Pass");
        guardian1.setEmailAddress("EmailAddress2");
        guardian1.setPhoneNo("Phone No 2");
        guardian1.setAccess("g");
        userList.add(guardian1);
        
        
        Guardian guardian2 = new Guardian();
        guardian2.setUsername("Temp");
        guardian2.tempSetPassword("Temp");
        guardian2.setName("Name1");
        guardian2.setEmailAddress("EmailAddress1");
        guardian2.setPhoneNo("Phone No 1");
        guardian2.setAccess("g");
        userList.add(guardian2);
        
        child3.addGuardian(guardian2);
        guardian2.addChild(child3);
        
        
        
        
        c = Calendar.getInstance();
        c.set(2012, 4, 10);
        //Day day1 = new Day(c, "T01");
        Day day1 = new Day(c);
        day1.setRoom(room);
        dayList.add(day1);

        c = Calendar.getInstance();
        c.set(2012, 4, 22);
        //Day day2 = new Day(c, "T02");
        Day day2 = new Day(c);
        day2.setRoom(room);
        dayList.add(day2);

        c = Calendar.getInstance();
        c.set(2012, 4, 24);
        //Day day3 = new Day(c, "T03");
        Day day3 = new Day(c);
        day3.setRoom(room);
        dayList.add(day3);

        c = Calendar.getInstance();
        c.set(2012, 4, 27);
        //Day day4 = new Day(c, "T04");
        Day day4 = new Day(c);
        day4.setRoom(room);
        dayList.add(day4);*/
        
        
        //for(int i = 0; i < objectiveList.size(); i++)
        //{
        //	objectiveList.get(i).setId("O000" + i);
        //}
        
        /*session.save(child1);
        session.save(child2);
        session.save(child3);
        session.save(objective1);
        session.save(objective2);
        session.save(objective3);
        session.save(step1);
        session.save(step2);
        session.save(step3);
        session.save(step4);
        session.save(step5);
        session.save(step6);
        session.save(step7);
        session.save(step8);
        session.save(step9);
        session.save(step10);
        session.save(step11);
        session.save(step12);
        session.save(step13);
        session.save(step14);
        session.save(step15);
        session.save(session1);
        session.save(session2);
        session.save(session3);
        session.save(session4);
        session.save(session5);
        session.save(session6);
        session.save(session7);
        session.save(user1);
        session.save(user2);
        session.save(guardian1);
        session.save(guardian2);
        session.save(day1);
        session.save(day2);
        session.save(day3);
        session.save(day4);
        
        session.getTransaction().commit();*/
        
        //		^		^		^		^
        // Uncomment above session.save code (and session.beginTransaction code at top of this method)
        // if these hard-coded objects ever need to be re-added to the database.
    	
    	org.hibernate.Session session = factory.openSession();
    	String sqlUserAccountQry = ("Select useraccount.name, useraccount.access, useraccount.emailAddress," +
    			"useraccount.password, useraccount.phoneNo, useraccount.username From UserAccount useraccount");
    	Query query = session.createQuery(sqlUserAccountQry);
    	
    	Therapist ther;
    	String therCheck;
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();

    		therCheck = (row[1]+"");
    		if(!(therCheck.equals("g")));
    		{
    			ther = new Therapist();
    			ther.setName(row[0]+"");
    			ther.setAccess(therCheck);
    			ther.setEmailAddress(row[2]+"");
    			ther.setHashedPassword(row[3]+"");
    			ther.setPhoneNo(row[4]+"");
    			ther.setUsername(row[5]+"");
    			userList.add(ther);
    		}
    	}
    
    }
    
    /*
     * Checks the parsed through username and password and returns true if the password is correct for that user.
     * Returns false otherwise.
     */

    public boolean login(String username, String password)
    {
    	int size = userList.size();
        for(int i = 0; i < size; i++)
        {
        	UserAccount temp = userList.get(i);
        	
        	if(temp.getUsername() != null && temp.getUsername().equals(username))
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
    
    public Therapist newTherapist(String name, String username, String emailAddress, String phoneNo, String password, String access) throws Exception
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
    	
    	Therapist user = new Therapist(name, username, emailAddress);
    	user.setAccess(access);
    	
    	user.setPassword(password);
    	
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
    	session.save(user);
    	session.getTransaction().commit();
    	
    	return user;
    }
    
	public Guardian newGuardian(String name, String username, String emailAddress, String phoneNo, String password, String access) throws Exception
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
	
		Guardian user = new Guardian(name, username, emailAddress);
		user.setAccess(access);
		user.setPassword(password);
		userList.add(user);
		//TODO ADD GUARDIAN
		return user;
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
    
    public Child addChild(String name, Calendar dob, Calendar dateJoined) throws Exception
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
       
        childList.add(child);
        //TODO - ADD CHILD HERE
        
        return child;
    }
    
    /* 
     * Returns child with the childID Specified
     */
    
	public Child viewChild(int childId) throws Exception
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
		//TODO ADD DAY HERE
		
		
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
			//TODO ADD STEPS HERE
		}
		
		objectiveList.add(o);
		
		//TODO ADD OBJECTIVE HERE
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
	
	public void addGuardian(Guardian guardian) {
		userList.add(guardian);
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

			    	org.hibernate.Session session = factory.getCurrentSession();
			    	session.beginTransaction();
			    	
			    	session.update(currentUser);
			    	session.getTransaction().commit();
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
		//TODO ADD MARK HERE
	}
	
	public void addTimestamp(Session session, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		Mark tempMark = new Mark(session, child, objective, step, mark, (Therapist)currentUser, day);
		tempMark.setComments("Timestamp Logged.");
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);
		
		//TODO ADD MARK HERE TOO
	
	}

	//searches through child list and returns child with the ID specified
	
	public Child getChild(int childId) {
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
	
	public ArrayList<Guardian> getGuardianList() {
		ArrayList<Guardian> guardians = new ArrayList<Guardian>();
		int size = userList.size();
		for(int i = 0; i < size; i++)
		{
			UserAccount u = userList.get(i);
			if(u instanceof Guardian)
			{
				guardians.add((Guardian)u);
			}
		}
		return guardians;
	}


	public UserAccount getUserAccount(String username) {
		int size = userList.size();
		for(int i = 0; i < size; i++)
		{
			UserAccount user = userList.get(i);
			if(user.getUsername().equals(username))
			{
				return user;
			}
		}
		return null;
	}

	public String getCurrentAccess() {
		return currentUser.getAccess();
	}


	
}