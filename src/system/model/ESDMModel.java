
package system.model;



import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import system.helper.Helper;
import system.helper.PlaySound;
import system.helper.SimpleKey;
import system.individuals.Child;
import system.individuals.ChildObjective;
import system.individuals.Guardian;
import system.individuals.Therapist;
import system.individuals.UserAccount;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.ObjectiveType;
import system.marking.Step;
import system.sessions.Day;
import system.sessions.Setting;



import BCrypt.BCrypt;


public class ESDMModel {
    
    private List<Child> childList;
    private Set<Setting> settingList;
    private List<Objective> objectiveList;
    private List<UserAccount> userList;
    private List<ObjectiveType> objectiveTypeList;
    private Set<Mark> markList;
    private List<Day> dayList;
    private List<Room> roomList;
    private UserAccount currentUser;
    private AnnotationConfiguration config;
	private SessionFactory factory;
    
	/**
	 * Creates a new Model object. Sets up all the lists and calls the hibernate setup methods
	 * @throws Exception 
	 */
    public ESDMModel() throws Exception
    {
    	childList = new ArrayList<Child>();
        settingList = new HashSet<Setting>();
        objectiveList = new ArrayList<Objective>();
        markList = new HashSet<Mark>();
        roomList = new ArrayList<Room>();
        dayList = new ArrayList<Day>();
        userList = new ArrayList<UserAccount>();
        objectiveTypeList = new ArrayList<ObjectiveType>();
        currentUser = null;
        hibernateSetUp();
        loadFromDatabase();
		
        
    }
    
    private void hibernateSetUp()
    {
    	config = new AnnotationConfiguration();
		config.addAnnotatedClass(Step.class);
		config.addAnnotatedClass(ObjectiveType.class);
		config.addAnnotatedClass(Objective.class);
		config.addAnnotatedClass(UserAccount.class);
		config.addAnnotatedClass(Therapist.class);
		config.addAnnotatedClass(Guardian.class);
		config.addAnnotatedClass(Child.class);
		config.addAnnotatedClass(Mark.class);
		config.addAnnotatedClass(Day.class);
		config.addAnnotatedClass(Setting.class);
		config.addAnnotatedClass(ChildObjective.class);
		config.addAnnotatedClass(Room.class);

		File f = new File("hibernate.cfg.xml");
		
		config.configure(f);
		
		//new SchemaExport(config).create(true, true);
		// ^ ^ ^ SchemaExport is commented out once all tables are created,
		// ^ ^ ^ uncommented when class annotations have been changed and
		// ^ ^ ^ therefore classes need to be added again.
		
		factory = config.buildSessionFactory();
		
		System.out.println("*************************************" + f.getAbsolutePath() + "************");
    }
    
    // Is called when the program is closed. makes sure the session factory
    // object gets closed properly.
    /**
     * Is called when the program is closed. Makes sure the session factory is closed properly.
     */
    public void modelExit()
    {
    	factory.close();
    }
    
    /**
     * Returns the annotation configuration object
     * @return config
     */
    public AnnotationConfiguration getConfig() {
		return config;
	}

    /**
     * returns the session factory object
     * @return
     */
	public SessionFactory getFactory() {
		return factory;
	}
    
    /**
     * Returns the list of all children in the system
     * @return childList
     */

    public List<Child> getChildList() {
    	
        return childList;
    }
    
    /**
     * Returns a list of all children in the system where the boolean parsed in is equal to the childs active attribute
     * @return childList
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
    
    /**
     * Returns the list of all rooms in the system
     * @return roomList
     */
    public Collection<Room> getRoomList() {
    	return roomList;
    }
    
    /**
     * Returns the list of all settings in the system
     * @return settingList
     */
    public Collection<Setting> getSettingList() {
    	return settingList;
    }
    
    /**
     * Returns a list of all objectives in the system
     * @return objectiveList
     */
    public List<Objective> getObjectiveList() {
    	return objectiveList;
    }
    
    
    /* 
     * Loads all data from the database
     */
    private void loadFromDatabase() throws Exception
    {
    	org.hibernate.Session session = factory.openSession();
     	session.beginTransaction();
    	
      	/*Therapist user = new Therapist();
        user.setUsername("temp");
        user.tempSetPassword("temp");
        user.setName("Temporary User");
        user.setAccess("a");
        userList.add(user);
       	session.save(user);*/
       	

           	
    	String qry = ("Select sess from Setting sess");
    	Query query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Setting s = (Setting) it.next();
    		settingList.add(s);
    	}
    	
    	qry = ("Select objType from ObjectiveType objType");
    	query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		ObjectiveType ot = (ObjectiveType) it.next();
    		objectiveTypeList.add(ot);
    	}
    	
    	qry = ("Select room from Room room");
    	query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Room r = (Room) it.next();
    		roomList.add(r);
    	}
    	
    	
    	qry = ("Select obj from Objective obj");
    	query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Objective o = (Objective) it.next();
    		objectiveList.add(o);
    	}
    	
    	qry = ("Select child from Child child");
    	query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Child c = (Child) it.next();
    		childList.add(c);
    	}
    	
    	qry = ("Select day from Day day order by day.date DESC");
    	query = session.createQuery(qry);

    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Day d = (Day) it.next();
    		dayList.add(d);
    	}
    	
    	
    	qry = ("Select mark from Mark mark");
    	query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Mark m = (Mark) it.next();
    		markList.add(m);
    	}
    	
    
    	qry = ("Select account from UserAccount account");
    	query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		UserAccount ua = (UserAccount) it.next();
    		userList.add(ua);
    	}
    	

    	session.getTransaction().commit();
    	
    
    }
  

    /**
     * Checks the parsed through username and password and returns true if the password is correct for that user.
     * Returns false otherwise.
     * 
     * @param username
     * @param password
     * @return boolean
     */
    public boolean login(String username, String password)
    {
    	int size = userList.size();
        for(int i = 0; i < size; i++)
        {
        	UserAccount temp = userList.get(i);
        	
        	if(temp.getUsername() != null && temp.getUsername().equals(username) && !(temp.getAccess().equals("d")) )
        	{
        		if(BCrypt.checkpw(password, temp.getPassword()))
        		{
        			currentUser = temp;
        			//TODO Sessions
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
     * 
     */
    
    /**
     * Sets the password of the user that is currently logged in to the system to the String passed through.
     * 
     * @param temp
     * @throws Exception
     */
    public void setPassword(String temp) throws Exception
    {
    	if(currentUser != null)
    	{
    		currentUser.setPassword(temp);
    	}
    	else
    	{
    		throw new Exception("10005: No user is currently logged in.");
    	}
    }
    
    /*
     * 
     */
    
    
    /**
     * Adds a new user account to the system with the attributes passed through
     * @param name
     * @param username
     * @param emailAddress
     * @param phoneNo
     * @param password
     * @param access
     * @return therapist
     * @throws Exception
     */
    public Therapist newTherapist(String name, String username, String emailAddress, String phoneNo, String password, String access) throws Exception
    {
    	// Checks that no strings are empty
    	if(name.length() == 0)
    	{
    		throw new MissingResourceException("30001: Name field must be filled in.", null, null);
    	}
    	if(username.length() == 0)
    	{
    		throw new MissingResourceException("30001: Username field must be filled in.", null, null);
    	}
    	if(emailAddress.length() == 0)
    	{
    		throw new MissingResourceException("30001: Email field must be filled in.", null, null);
    	}
    	if(phoneNo.length() == 0)
    	{
    		throw new MissingResourceException("30001: Phone number field must be filled in.", null, null);
    	}
    	
    	// Creates new therapist object. sets the attributes to the values parsed in
    	Therapist user = new Therapist(name, username, emailAddress);
    	user.setAccess(access);
    	user.setPhoneNo(phoneNo);
    	user.setPassword(password);
    	
    	// gets the current session and saves the new therapist to DB
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	session.save(user);
    	session.getTransaction().commit();

    	// Adds user to the model
    	userList.add(user);
    	return user;
    }
    
    /**
     * Adds a new user account to the system with the attributes passed through
     * @param name
     * @param username
     * @param emailAddress
     * @param phoneNo
     * @param password
     * @param access
     * @return guardian
     * @throws Exception
     */
	public Guardian newGuardian(String name, String username, String emailAddress, String phoneNo, String password, String access) throws Exception
	{
    	// Checks that no strings are empty
		if(name.length() == 0)
		{
			throw new IllegalArgumentException("30001: Name field must be filled in.");
		}
		if(username.length() == 0)
		{
			throw new IllegalArgumentException("30001: Username field must be filled in.");
		}
		if(emailAddress.length() == 0)
		{
			throw new IllegalArgumentException("30001: Email field must be filled in.");
		}
		if(phoneNo.length() == 0)
		{
			throw new IllegalArgumentException("30001: Phone number field must be filled in.");
		}
    	// Creates new guardian object. sets the attributes to the values parsed in
	
		Guardian user = new Guardian(name, username, emailAddress);
		user.setAccess(access);
		user.setPassword(password);
		user.setPhoneNo(phoneNo);
		
    	// gets the current session and saves the new guardian to DB
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	session.save(user);
    	session.getTransaction().commit();
    	
    	// Adds user to the model
		userList.add(user);
    	
		return user;
	}
    
    
    /**
     * Returns true if a user is currently logged in, false otherwise
     * @return
     */
    public boolean loggedIn()
    {
        if(currentUser == null)
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * Creates a new child object and Adds it to the system with the specified name,
     * dob, date joined and list of guardians
     * @param name
     * @param dob
     * @param dateJoined
     * @return child
     * @throws Exception
     */
    public Child addChild(String name, Calendar dob, Calendar dateJoined) throws Exception
    {
    	
    	if(name.length() == 0)
    	{
    		throw new Exception("30001: Name is a required field.");
    	}
    	
    	if(dob == null)
    	{
    		throw new Exception("30001: Date Of Birth is a required field.");
    	}
    	
    	if(dateJoined != null)
    	{
    		if(dob.compareTo(dateJoined) > 0)
    		{
    			throw new Exception("30003: Date Joined cannot be before Date of Birth");
    		}
    	}
    	
		if(dob.compareTo(Calendar.getInstance()) > 0)
		{
			throw new Exception("30003: Date of Birth cannot be after today");
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
     * 
     */
    
    /**
     * Returns child with the id passed through
     * @param childId
     * @return
     * @throws Exception
     */
	public Child viewChild(int childId) throws Exception
    {
        Child child = (Child)Helper.search((Collection<Child>)childList, childId);
        
        if(child == null)
        {
        	throw new Exception("10006: Child ID not found.");
        }
        
        return child;
    }
	
	/** 
	 * Creates a new Day object, takes a list of Children and Session
	 * objects and adds them to that Day
	 * @param date
	 * @param children
	 * @param room
	 * @param settings
	 * @return
	 * @throws Exception
	 */
	public Day addDay(Calendar date, ArrayList<Child> children, Room room, ArrayList<Setting> settings) throws Exception
	{
		if(date == null)
		{
			throw new Exception("30004: Date is not in correct format or missing.");
		}
		if(children == null || children.size() == 0)
		{
			throw new Exception("30005: No children have been selected for this session.");
		}
		if(settings == null || settings.size() == 0)
		{
			throw new Exception("30006: No settings have been selected for this session.");
		}

		Day day = new Day(date, room);
		int size = children.size();
		for (int i = 0; i < size; i++)
		{
			day.addChildren(children.get(i)); //adds selected children to this day
		}
		size = settings.size();
		for (int x = 0; x < size; x++)
		{
			day.addSetting(settings.get(x));
		}
		dayList.add(day);
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
    	session.save(day);
    	session.getTransaction().commit();
		
		
		return day;
	}
	
	
	/**
	 * Takes in a String Name Which is the name of the objective, String description which is the description of the Objective
	 * 
	 *  it takes String[][] which are the steps.
	 *  (i.e. steps[0][0] is the code of the first step, steps[0][1] is the Description of the first step
	 *        steps[1][0] is the code of the second step, step [1][1] is the description of the second step. and so on.
	 *        
	 * @param name
	 * @param description
	 * @param steps
	 * @param level
	 * @param objType
	 * @throws Exception
	 */
	public void addObjective(String name, String description, String[][] steps, String level, ObjectiveType objType) throws Exception
	{
		level = level.trim();
		name = name.trim();
		description = description.trim();
		int levelInt;
		
		//iterate through string
		//create step for each pair
		//create objective with these steps
		if(name.length() == 0)
		{
			throw new Exception("30001: The Name Field is empty.");
		}
		if(level.length() == 0)
		{
			throw new Exception("30001: The level field is empty.");
		}
		try {
		levelInt = Integer.parseInt(level);
		} catch (Exception e) {
			throw new Exception("30002: Level must be a number.");
		}
		
		if(description.length() == 0)
		{
			throw new Exception("30001: The description field is empty.");
		}
		if(steps.length == 0)
		{
			throw new Exception("30007: No steps have been added.");
		}
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
		
		Objective o = new Objective(name, description, levelInt);
		o.setType(objType);
		for (int i = 0; i < steps.length; i++)
		{
			if(steps[i][0].length() == 0 )
			{
				throw new Exception("30001: One of the code fields is empty.");
			}
			if(steps[i][1].length() == 0 )
			{
				throw new Exception("30001: One of the step fields is empty.");
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
	 * 
	 */
	
	/**
	 * Returns the user that is currently logged in to the system
	 * @return User
	 */
	public UserAccount getCurrentUser() {
		return currentUser;
	}

	
	/**
	 * Sets the email of the user that is currently logged into the system to the String passed through
	 * @param email
	 * @throws Exception
	 */
	public void setEmail(String email) throws Exception {
		if(Helper.isValidEmailAddress(email))
		{
			currentUser.setEmailAddress(email);
			
			org.hibernate.Session session = factory.openSession();
			session.beginTransaction();
			currentUser = (UserAccount) session.merge(currentUser);
			session.save(currentUser);
	    	session.getTransaction().commit();

		}
		else
		{
			throw new Exception("30002: The email address is not valid.");
		}

	}
	
	/**
	 * Adds the guardian passed through to the system
	 * @param guardian
	 */
	public void addGuardian(Guardian guardian) {
		userList.add(guardian);
	}

	/**
	 * Returns a list of all days in the system
	 * @return dayList
	 */
	public List<Day> getDayList() {
		return dayList;
	}
	
	/*
	 *
	 */
	
	/**
	 * Changes the password of the user that is currently logged in to the system to the new password parsed through
	 * Checks that both new passwords match and that they aren't same as old password and meet minimum complexity
	 * @param oldPassword
	 * @param newPassword1
	 * @param newPassword2
	 * @throws Exception
	 */
	public void changePassword(String oldPassword, String newPassword1, String newPassword2) throws Exception {
		if(oldPassword.equals(newPassword1))
		{
			throw new Exception("40001: The new and old password are the same.");
		}
		if(oldPassword.length() == 0 || newPassword1.length() == 0 || newPassword2.length() == 0)
		{
			throw new Exception("30001: One or more of the password fields were left empty.");
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
					throw new Exception("40002: Incorrect Password.");
				}
		}
		else
		{
			throw new Exception("30002: Both Passwords do not match.");
		}
	}
	
	/**
	 * Adds a new mark to the system with the attributes passed through.
	 * @param setting
	 * @param child
	 * @param objective
	 * @param step
	 * @param mark
	 * @param day
	 * @throws Exception
	 */
	public void addMark(Setting setting, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		if(setting == null)
		{
			throw new Exception("50001: Session not Selected.");
		}
		if(child == null)
		{
			throw new Exception("50001: Child not Selected.");
		}
		
		if(objective == null)
		{
			throw new Exception("50001: Objective not Selected.");
		}
		
		if(step == null)
		{
			throw new Exception("50001: Step not Selected.");
		}
		
		if(mark > 1 || mark < -1)
		{
			throw new Exception("50001: Mark not selected.");
		}
		Therapist therapist = (Therapist) currentUser;
		

		Calendar time = Calendar.getInstance();
		
		Mark tempMark = new Mark(setting, child, objective, step, mark, therapist, day, time);
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);    	
		therapist.addMark(tempMark);

		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	
    	dbSession.save(tempMark);
    	
    	dbSession.getTransaction().commit();
		
	}
	
	/**
	 * Adds a new timestamp to the system with the attributes passed through.
	 * @param setting
	 * @param child
	 * @param objective
	 * @param step
	 * @param mark
	 * @param day
	 * @throws Exception
	 */
	public void addTimestamp(Setting setting, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		
		Therapist t = (Therapist)currentUser;

		Calendar time = Calendar.getInstance();
		Mark tempMark = new Mark(setting, child, objective, step, mark, t, day, time);

		
		tempMark.setComments("Timestamp Logged.");
		day.addMark(tempMark);
		if(child != null) {
			child.addMark(tempMark);
		}
		markList.add(tempMark);
		t.addMark(tempMark);
		
    	org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	
    	dbSession.save(tempMark);
    	
    	dbSession.getTransaction().commit();
	
	}

	
	/**
	 * Returns a list of all days that are between (inclusive) the 2 dates parsed through.
	 * @param from
	 * @param to
	 * @return
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
	 */

	/**
	 * Sets the child object active attribute to false.
	 * @param child
	 */
	public void removeChild(Child child) {
		child.setInactive();
		updateObject(child);
	}

	
	/**
	 * updates the values of the child to the values parsed in.
	 * @param child
	 * @param name
	 * @param dob
	 * @param dateJoined
	 * @throws Exception
	 */
	public void updateChild(Child child, String name, Calendar dob, Calendar dateJoined) throws Exception {
    	if(name.length() == 0)
    	{
    		throw new Exception("30001: Name is a required field.");
    	}
    	
    	if(dob == null)
    	{
    		throw new Exception("30001: Date Of Birth is a required field.");
    	}
    	

     	if(dateJoined != null)
    	{
    		if(dob.compareTo(dateJoined) > 0)
    		{
    			throw new Exception("30002: Date Joined cannot be before Date of Birth");
    		}
    	}
    	
		if(dob.compareTo(Calendar.getInstance()) > 0)
		{
			throw new Exception("30002: Date of Birth cannot be after today");
		}
				
		child.setName(name);
		child.setDob(dob);
		child.setDateJoined(dateJoined);
		
		// updates the changes to the child object in the database
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	child = (Child) session.merge(child);
    	session.save(child);
    	session.getTransaction().commit();
		
	}

	
	/**
	 * Takes in a child object and objective object and adds the objective to the child
	 * @param child
	 * @param objective
	 * @throws Exception
	 */
	public void addObjectiveChild(Child child, Objective objective) throws Exception {
		// Checks that neither object is null
		if(child == null || objective == null)	
		{
			throw new IllegalArgumentException("30007: Child or Objective not selected.");
		}
		else
		{
			ArrayList<Objective> objList = new ArrayList<Objective>(child.getObjectives());
			Objective obj = null;
			int size = objList.size();
			// iterating through a list of the childs objectives to find this objective
			for(int i = 0; i < size; i++)
			{
				if(objList.get(i) == objective)
				{
					obj = objList.get(i);
					break;
				}
			}
			ChildObjective co = null;
			// if objective does exist for this child throw an exception
			if(obj != null)
			{
				throw new IllegalArgumentException("30008: This objective already exists for this child.");
			}
			else
			{
				co = new ChildObjective(objective, child);
				child.addChildObjective(co);
				objective.addChild(co);
			}
		
		
			org.hibernate.Session session = factory.getCurrentSession();

    		session.beginTransaction();
    		session.save(co);
    		session.getTransaction().commit();
		}
		
	}
	
	// Returns a list of the guardians in the system
	/**
	 * Returns a list of all guardians in the system
	 * @return guardianList
	 */
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
	
	
	/**
	 * Returns the useraccount with the username passed throuhg
	 * @param username
	 * @return
	 */
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

	// 
	/**
	 * Returns the string access level of the currently logged in user
	 * @return access
	 */
	public String getCurrentAccess() {
		return currentUser.getAccess();
	}

	/**
	 * Takes in a mark and all details required to update the mark
	 * @param mark
	 * @param setting
	 * @param child
	 * @param time
	 * @param objective
	 * @param step
	 * @param markVal
	 * @param comment
	 * @throws Exception
	 */
	public void updateMark(Mark mark, Setting setting, Child child,
			Calendar time, Objective objective, Step step, int markVal,
			String comment) throws Exception {
		
		if(objective != null) {
			if(step == null)
			{
				throw new Exception("30001: Mark must have step filled in.");
			}
			mark.setObjective(objective);
			mark.setStep(step);
		}
		
		
		mark.setSetting(setting);
		mark.setChild(child);
		mark.setTime(time);
		mark.setMark(markVal);
		mark.setComments(comment);
		
    	org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();

    	mark = (Mark) dbSession.merge(mark);
    	dbSession.update(mark);
    	dbSession.getTransaction().commit();
		
	}
	
	// 
	/**
	 * Takes in objective, strings for name and description, int level, and a string array for steps.
	 * updates the details in the objective object with the ones parsed through and updates in Database
	 * @param objective
	 * @param name
	 * @param description
	 * @param level
	 * @param steps
	 * @param objType
	 * @param hidden
	 * @throws Exception
	 */
	public void saveObjective(Objective objective, String name, String description, int level, String[][] steps, ObjectiveType objType, boolean hidden) throws Exception
	{
		if(name.length() == 0)
		{
			throw new Exception("30001: The name field is empty.");
		}
		if(level == 0)
		{
			throw new Exception("30001: The level field is empty.");
		}
		if(description.length() == 0)
		{
			throw new Exception("30001: The description field is empty.");
		}
		if(objType == null)
		{
			throw new Exception("30001: The type must be selected.");
		}
		
		objective.setName(name);
		objective.setDescription(description);
		objective.setLevel(level);
		objective.setHidden(hidden);
		objective.setType(objType);
		// Iterates through the array setting the value of the steps to the values parsed through
		int x = 1;
		for(int i = 0; i < steps.length; i++)
		{
			if(steps[i][0].isEmpty() || steps[i][1].isEmpty())
			{
				throw new Exception("30001: Not all steps have been filled in.");
			}
			Step s = objective.getStep(x);
			s.setNo("" + x++);
			s.setCode(steps[i][0]);
			s.setDescription(steps[i][1]);
		}
		
		// Gets session object and saves the objective to the database
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	objective = (Objective) session.merge(objective);
    	session.save(objective);
    	session.getTransaction().commit();
	}

	/**
	 * If objective passed through exists in child value of mastered gets set to the boolean passed through
	 * @param c
	 * @param o
	 * @param value
	 * @throws Exception
	 */
	public void setMastered(Child c, Objective o, boolean value) throws Exception {
		c.setMastered(o, value);
		updateObject(c);
	}

	/*
	 * Takes in any object that is to be saved to the database. and saves it to the database.
	 */
	public void updateObject(Object o) {
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	o = (Object) session.merge(o);
    	session.save(o);
    	session.getTransaction().commit();
	}

	/**
	 * Adds the passed in guardian to the child passed in
	 * @param child
	 * @param guardian
	 * @throws Exception
	 */
	public void addChildGuardian(Child child, Guardian guardian) throws Exception {
		
		// Check if guardian already exists in this child.
		ArrayList<Guardian> childsGuardians = new ArrayList<Guardian>(child.getGuardians());
		for(int i = 0; i < childsGuardians.size(); i++)
		{
			// if guardian exists throw an exception
			if(childsGuardians.get(i).equals(guardian))
			{
				throw new Exception ("30009: Guardian already exists for this child");
			}
		}
		// Adds guardian to child and child to guardian\
		child.addGuardian(guardian);
		
		// Save both objects to database
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	guardian = (Guardian) session.merge(guardian);
    	child = (Child) session.merge(child);
    	
    	session.update(guardian);
    	session.update(child);
    	session.getTransaction().commit();
	}
	
	
	/**
	 * Takes in any SimpleKey object. and plays the WAV file associated to that object.
	 * Runs in a thread so that you can still navigate around while file is playing
	 * @param object
	 * @throws Exception
	 */
	public void playSound(SimpleKey object) throws Exception {

		String filePath = object.getClass().getSimpleName();
		File file = new File(filePath + "\\" + object.getId() + ".wav");
		
		PlaySound ps = new PlaySound(file);
		Thread thread = new Thread(ps);
		thread.start();
		
	}
	
	
	/**
	 * Takes in a child and Objective and removes the objective from the child 
	 * @param c
	 * @param o
	 */
	public void removeObjective(Child c, Objective o)
	{
		ChildObjective co = c.removeObjective(o);
        org.hibernate.Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(co);
        session.getTransaction().commit();
	}
	
	
	/**
	 * Increments the step of that objective for that child
	 * @param c
	 * @param o
	 * @throws Exception
	 */
	public void incrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, 1);
		updateObject(c);
		updateObject(o);
	}

	/**
	 * Decrements the step of that objective for that child
	 * @param c
	 * @param o
	 * @throws Exception
	 */
	public void decrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, -1);
		updateObject(c);
		updateObject(o);
	}
	
	/**
	 * Sets the type of the objective passed in to the objectivetype passed in
	 * @param objective
	 * @param objectiveType
	 */
	public void setObjectiveType(Objective objective, ObjectiveType objectiveType) {
		objective.setType(objectiveType);
		objectiveType.addObjective(objective);

		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	objective = (Objective) session.merge(objective);
    	session.update(objective);
    	session.getTransaction().commit();
		
	}
	
	/**
	 * Creates a new objective type object and adds it to the system
	 * @param name
	 */
	public void addObjectiveType(String name) {
		ObjectiveType objType = new ObjectiveType(name);
		
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	session.save(objType);
    	session.getTransaction().commit();
    	
    	objectiveTypeList.add(objType);
	}
	
	/**
	 * Removes the ObjectiveType passed through from the system
	 * @param objType
	 */
	public void removeObjectiveType(ObjectiveType objType) {

		boolean temp = objectiveTypeList.remove(objType);
		org.hibernate.Session session = factory.getCurrentSession();
		session.beginTransaction();
		objType = (ObjectiveType) session.merge(objType);
		session.delete(objType);
		session.getTransaction().commit();
		
		
		
		System.out.println("Successfully?: " + temp);
	}
	
	/**
	 * Creates a new room object with the name passed through
	 * @param name
	 */
	public void addRoom(String name) {
		Room room = new Room(name);

    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	session.save(room);
    	session.getTransaction().commit();
    	
    	roomList.add(room);
	}

	/**
	 * Edits the name of the room object passed through with the string passed through
	 * @param room
	 * @param name
	 */
	public void editRoom(Room room, String name) {
		if(name.length() == 0)
		{
			throw new IllegalArgumentException("30001: Name cannot be null.");
		}
		room.setRoomName(name);
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	room = (Room) session.merge(room);
    	session.update(room);
    	session.getTransaction().commit();
	}

	/**
	 * Takes in a room and removes it from system if it's not added to any Day objects.
	 * @param room
	 */
	public void removeRoom(Room room) {
		
		org.hibernate.Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		String qry = ("Select count(*) from Day day where day.roomId = \"" + room.getRoomName() + "\"");
		Query query = session.createSQLQuery(qry);
    	BigInteger s = (BigInteger) query.uniqueResult();
    	System.out.println(s.intValue());
    	
    	if(s.intValue() == 0)
    	{
    		roomList.remove(room);
        	session.delete(room);
        	session.getTransaction().commit();
    	}
    	else
    	{
    		throw new IllegalArgumentException("50002: Unable to delete this room as it exists in a day object");
    	}
		
    	
    	
	}
	
	/**
	 * Sets the name of the setting passed through to the description String passed through.
	 * @param setting
	 * @param description
	 * @throws Exception
	 */
	public void updateSetting(Setting setting, String description) throws Exception {

		if(description.length() == 0)
		{
			throw new Exception("30001: You must enter a value for description");
		}
		setting.setDescription(description);
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	setting = (Setting) session.merge(setting);
    	session.update(setting);
    	session.getTransaction().commit();
	}
	
	
	/**
	 * Takes in a session and removes it from the system if it is not associated with any days
	 * @param setting
	 */
	public void removeSetting(Setting setting) {
	

    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	
		String qry = ("Select count(*) from DaySetting ds where ds.SettingID = " + setting.getId());
		Query query = session.createSQLQuery(qry);
    	BigInteger s = (BigInteger) query.uniqueResult();
    	
    	if(s.intValue() == 0)
    	{
    		settingList.remove(setting);
    		setting = (Setting) session.merge(setting);
    		session.delete(setting);
    		
    	}
    	else
    	{
    		throw new IllegalArgumentException("50002: Setting cannot be deleted as it is in a day object.");
    	}
    	
    	session.getTransaction().commit();
    	
	}
	
	/**
	 * Takes in a User Account and sets the access to d (Disabled)
	 * @param u
	 */
	public void disableAccount(UserAccount u) {
		
		u.setAccess("d");
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	u = (UserAccount) session.merge(u);
    	session.update(u);
    	session.getTransaction().commit();
		
	}

	/**
	 * Creates a setting object with the String passed through for the name
	 * @param name
	 */
	public void addSetting(String name) {
		Setting s = new Setting(name);
		
    	org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	dbSession.save(s);
    	dbSession.getTransaction().commit();
    	
    	settingList.add(s);
	}

	/**
	 * Returns a list fo all Objective Types
	 * @return objectiveTypeList
	 */
	public Collection<ObjectiveType> getObjectiveTypeList() {
		return objectiveTypeList;
	}

	/**
	 * Adds a behavioural mark to the system with the items passed through
	 * @param day
	 * @param child
	 * @param markInt
	 * @throws Exception
	 */
	public void addBehaviouralMark(Day day, Child child, int markInt) throws Exception
	{
		if(child == null)
		{
			throw new Exception("30001: Child not Selected.");
		}
		
		if(markInt < 0 || markInt > 6)
		{
			throw new Exception("30001: Mark not selected.");
		}
		Therapist therapist = (Therapist) currentUser;
		
		Mark tempMark = new Mark(markInt, child, therapist, 'b', day);
		Calendar timestamp = Calendar.getInstance();
		tempMark.setTime(timestamp);
		tempMark.setComments("Behavioural Mark");
		child.addMark(tempMark);
		markList.add(tempMark);    	
		therapist.addMark(tempMark);
		day.addMark(tempMark);

		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	dbSession.save(tempMark);
    	dbSession.getTransaction().commit();
	}

	/**
	 * Checks if there is a day object associated with this room and date and returns true if so
	 * @param room
	 * @param date
	 * @return
	 */
	public boolean dayExists(Room room, Calendar date) {

    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	date = Helper.setCalendarTimeNull(date);

    	String qry = "Select day from Day day WHERE day.room = \'" + room + "\'" + "ORDER BY day.date";
    	Query query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Day day = (Day) it.next();
    		if(day.getDate().equals(date))
    		{
    	    	session.getTransaction().commit();
    			return true;
    		}
    	}
    	session.getTransaction().commit();
    	return false;
	}

	/**
	 * Returns a list of all Users in the system
	 * @return userList
	 */
	public List<UserAccount> getUserList() {
		return userList;
	}

	/**
	 * Sets the password of the user passed through to the String passed through
	 * @param u
	 * @param password
	 * @throws Exception
	 */
	public void setPassword(UserAccount u, String password) throws Exception {
		u.setPassword(password);
		
		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	u = (UserAccount) dbSession.merge(u);
    	dbSession.update(u);
    	dbSession.getTransaction().commit();
	}

	/**
	 * Sets the name of the objective type passed through to the String passed through.
	 * @param objType
	 * @param name
	 * @throws Exception
	 */
	public void editObjectiveType(ObjectiveType objType, String name) throws Exception  {
		objType.setName(name);

		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	objType = (ObjectiveType) dbSession.merge(objType);
    	dbSession.update(objType);
    	dbSession.getTransaction().commit();
	}

	/**
	 * Sets all details of the user acocunt passed through to the details passed through
	 * @param u
	 * @param name
	 * @param ph
	 * @param email
	 * @throws Exception
	 */
	public void setUserDetails(UserAccount u, String name, String ph,
			String email) throws Exception  {
		
		u.setName(name);
		u.setPhoneNo(ph);
		u.setEmailAddress(email);
		
		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	u = (UserAccount) dbSession.merge(u);
    	dbSession.update(u);
    	dbSession.getTransaction().commit();		
	}
}