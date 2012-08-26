
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
        
        printAll();
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
		config.addAnnotatedClass(Room.class);
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
    	/*
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
        Child child1 = new Child();
        //child1.setId("C001"); 
        child1.setName("John Doe");
        Calendar c = Calendar.getInstance();
        c.set(1991, 07, 21);
    	this.addChild("John Doe", c, c);

        
        c = Calendar.getInstance();
        c.set(1992, 11, 01);    	
        this.addChild("Sally Pearson", c, c);
     
        
        c = Calendar.getInstance();
        c.set(1992, 11, 01);    	
        this.addChild("Billy May", c, c);
        
        
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
        	this.addObjectiveChild(childList.get(i), objective);
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
      //  this.addObjectiveChild(childList.get(0), objective);
        
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
 
        
        Therapist user = new Therapist();		
        user.setUsername("dbisignano");
        user.tempSetPassword("temp");
        userList.add(user);
        
        user = new Therapist();
        user.setUsername("temp");
        user.tempSetPassword("temp");
        user.setAccess("n");
        userList.add(user);
        

        Guardian guardian = new Guardian();
        guardian.setName("Name2");
        guardian.setEmailAddress("EmailAddress2");
        guardian.setPhoneNo("Phone No 2");

        userList.add(guardian);
        
        
        guardian = new Guardian();
        guardian.setUsername("Temp");
        guardian.tempSetPassword("Temp");
        guardian.setName("Name1");
        guardian.setEmailAddress("EmailAddress1");
        guardian.setPhoneNo("Phone No 1");
        guardian.setAccess("g");
        userList.add(guardian);
        

        childList.get(0).addGuardian(guardian);
        guardian.addChild(childList.get(0));
        
        
        
        
        
        for(int i = 0; i < objectiveList.size(); i++)
        {
        	objectiveList.get(i).setId(i);
        }

		session.getTransaction().commit();
        
        //		^		^		^		^
        // Uncomment above session.save code (and session.beginTransaction code at top of this method)
        // if these hard-coded objects ever need to be re-added to the database.
    	
        */
        Therapist user = new Therapist();
        user.setUsername("temp1");
        user.tempSetPassword("temp1");
        user.setAccess("a");
        userList.add(user);
        
    	
    	
    	org.hibernate.Session session = factory.openSession();
 
    	
    	/*   	String sqlSessionQry = ("Select session.id from Session session");
    	Query query = session.createQuery(sqlSessionQry);

    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		
    		int id = (int) it.next();
    		Session s = (Session) session.get(Session.class, id);
    		sessionList.add(s);
    	}*/



    	String sqlSessionQry = ("Select session.id, session.description from Session session");
    	Query query = session.createQuery(sqlSessionQry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();
    		int id = (Integer)row[0];
    		String description = "" + row[1];
    		
    		sessionList.add(new Session(id, description));
    	}
    	
    	String sqlRoomQry = ("Select room.id, room.roomName From Room room");
    	query = session.createQuery(sqlRoomQry);
    	Room room;
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();
    		int id = (int) row[0];
    		String name = ""+row[1];
    		room = new Room(id, name);
    		roomList.add(room);
    	}
    	
    	
    	String sqlUserAccountQry = ("Select useraccount.name, useraccount.access, useraccount.emailAddress," +
    			"useraccount.password, useraccount.phoneNo, useraccount.username From UserAccount useraccount");
    	query = session.createQuery(sqlUserAccountQry);
    	
    	Therapist ther;
    	Guardian guar;
    	String therCheck;
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();

    		therCheck = (row[1]+"");
    		if(!(therCheck.equals("g")))
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
    		else
    		{
    			guar = new Guardian();
    			guar.setName(row[0]+"");
    			guar.setAccess(therCheck);
    			guar.setEmailAddress(row[2]+"");
    			guar.setHashedPassword(row[3]+"");
    			guar.setPhoneNo(row[4]+"");
    			guar.setUsername(row[5]+"");
    			userList.add(guar);
    		}
    	}
    	
    	
    	
    	
    	String sqlObjectiveQry = ("Select objective.id, objective.description, objective.level, " +
    			"objective.name From Objective objective");
    	query = session.createQuery(sqlObjectiveQry);
    	
    	Objective objective;
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();
    		
    		int id = (int) row[0];
    		String description = ""+row[1];
    		int level = (int) row[2];
    		String name = ""+row[3];
    		
    		objective = new Objective(name, description, level);
    		objective.setId(id);
    		
    		objectiveList.add(objective);
    		
    		String sqlStepQry = ("Select step.id, step.code, step.description, step.no" +
    				" From Step step where step.objective = " + id + " Order by step.no");
    		Query stepQuery = session.createQuery(sqlStepQry);
    		
    		for(Iterator it2 = stepQuery.iterate(); it2.hasNext();)
    		{
    			Object[] stepRow = (Object[]) it2.next();
    			int stepId = (int) stepRow[0];
    			String code = ""+stepRow[1];
    			String stepDescription = ""+stepRow[2];
    			String no = ""+stepRow[3];
    			
    			Step s = new Step(no, code, stepDescription);
    			s.setId(stepId);
    			s.setObjective(objective); 
    			objective.addSteps(s);
    		}
    		
    	}
    	
    	String sqlChildQry = ("Select child.id, child.active, child.dateJoined," +
    			" child.dob, child.name, child.pictureLink From Child child");
    	query = session.createQuery(sqlChildQry);
    	
    	Child child;
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();
    		
    		
    		int id = (int) row[0];
    		boolean active = (boolean) row[1];
    		Calendar dateJoined = (Calendar) row[2];
    		Calendar dob = (Calendar) row[3];
    		String name = row[4] + "";
    		String pictureLink = row[5] + "";
    		
    		child = new Child(name, dob, dateJoined);
    		child.setId(id);
    		child.setActive(active);
    		child.setPictureLink(pictureLink);
    		childList.add(child);
    		
    	}
    	
    	/*
    	
    	String sqlDayQry = ("Select day.id, day.template, day.room, day.date From Day day");
    	query = session.createQuery(sqlDayQry);
    	
    	Day day;
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();
    		day = new Day();
    		day.setId((int) row[0]);
    		day.setTemplate((boolean) row[1]);
    		day.setRoom((Room)row[2]);
    		day.setDate((Calendar)row[3]);
    		dayList.add(day);
    	}
    	
    	*/
	
    	// TODO
    	/* Loading of the followign items is alraedy implemented
    	 * room, Session, User Account, Therapist, Guardian & Objectives & steps. they are implemented 100%
    	 * Children, Day are being loaded in but not all associations are being loaded.
    	
    	*/
    	
   /* TODO FFUUUUUUUU NOT WORKINGGGGGGGG
     	String sqlChildObjectiveQry = ("Select co.id, co.objective, co.child, co.currentStep, co.mastered From ChildObjective co");
    	query = session.createQuery(sqlChildObjectiveQry);
    	
    	ChildObjective co;
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Object[] row = (Object[]) it.next();
    		int id = (int) row[0];
    		Objective o = (Objective) row[1];
    		Child c = (Child) row[2];
    		int currentStep = (int) row[3];
    		boolean mastered = (boolean) row[4];
    		
    		
    		co = new ChildObjective(o, c);
    		c.addChildObjective(co);
    		co.setCurrentStep(currentStep);
    		co.setId(id);
    		co.setMastered(mastered);
    		o.addChild(co);
    		
    	}*/
    	
    
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
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
    	session.save(user);
    	session.getTransaction().commit();
    	
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

    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
    	session.save(child);
    	session.getTransaction().commit();
        
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
			throw new Exception("Date is not in correct format or missing.");
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
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
    	session.save(day);
    	session.getTransaction().commit();
		
		
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
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
		
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
			step.setObjective(o);
			

	    	
	    	session.save(step);
			
		}
		
		session.save(o);
    	session.getTransaction().commit();
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
		/* not in 100% working order yet TODO
		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	
    	dbSession.save(tempMark);
    	
    	Not sure if we have to do these Updates or if hibernate automatically goes around updating everything
    	dbSession.update(child);
    	dbSession.update(day);
    	dbSession.getTransaction().commit();
    	*/
		
	}
	
	public void addTimestamp(Session session, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		Therapist t = (Therapist)currentUser;
		Mark tempMark = new Mark(session, child, objective, step, mark, t, day);
		tempMark.setComments("Timestamp Logged.");
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);
		t.addMark(tempMark);
		
		/* not in 100% working order yet TODO
    	org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	dbSession.save(t);
    	dbSession.save(tempMark);
    	dbSession.update(child);
    	dbSession.update(day);
    	dbSession.getTransaction().commit();
		*/
	
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
				if(objList.get(i) == objective)
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
		
		/*
		 * TODO - Not very fast. seems to update everything in child. not just childs objectives. don't know better way?
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
    	session.update(child);
    	session.getTransaction().commit();
		*/
		
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
	
	public void removeObjective(Child c, Objective o)
	{
		c.removeObjective(o);
	}

	public void updateMark(Mark mark, Session session, Child child,
			Calendar time, Objective objective, Step step, int markVal,
			String comment) {
		
		mark.setSession(session);
		mark.setChild(child);
		mark.setTime(time);
		mark.setObjective(objective);
		mark.setStep(step);
		mark.setMark(markVal);
		mark.setComments(comment);
	}
	
	public void saveObjective(Objective objective, String[] objDetails, int level, String[][] steps)
	{
		objective.setDescription(objDetails[0]);
		objective.setName(objDetails[1]);
		objective.setLevel(level);
		
		ArrayList<Step> stepList = new ArrayList<Step>();
		
		for(int i = 0; i < steps.length; i++)
		{
			Step s = new Step(steps[i][0], steps[i][1], steps[i][2]);
			stepList.add(s);
			s.setObjective(objective);
		}
		objective.setSteps(stepList);
		
	}

	public void incrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, 1);
	}

	public void decrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, -1);
	}

	public void setMastered(Child c, Objective o) throws Exception {
		c.setMastered(o, true);
	}
	
	
	public void printAll()
	{
				
				for(int i = 0; i < childList.size(); i++)
				{
					Child c = childList.get(i);
					String s = c.getName() + "\n";
					
					ArrayList<ChildObjective> co = new ArrayList<ChildObjective>(c.getChildObjectives());
					for(int x = 0; x < co.size(); x++)
					{
						ChildObjective childo = co.get(x);
						s = s + childo.getMastered() + " " + childo.getCurrentStep() + " " +
								childo.getObjective().getName() + " " + childo.getChild().getName() + "\n\n";
					}
					
					System.out.println("Child --------------\n" + s);
					
				}
				
				
				for(int i = 0; i < dayList.size(); i++)
				{
					Day d = dayList.get(i);
				}
	}


	
}