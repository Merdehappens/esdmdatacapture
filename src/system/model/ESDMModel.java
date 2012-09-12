
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
    
    public ESDMModel() throws MalformedURLException
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
		config.configure("hibernate.cfg.xml");
		
		//new SchemaExport(config).create(true, true);
		// ^ ^ ^ SchemaExport is commented out once all tables are created,
		// ^ ^ ^ uncommented when class annotations have been changed and
		// ^ ^ ^ therefore classes need to be added again.
		
		factory = config.buildSessionFactory();
    }
    
    // Is called when the program is closed. makes sure the session factory
    // object gets closed properly.
    public void modelExit()
    {
    	factory.close();
    }
    
    // returns the annotation configuration object
    public AnnotationConfiguration getConfig() {
		return config;
	}

    // returns the session factory object
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
    
    public Collection<Setting> getSettingList() {
    	return settingList;
    }
    
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
        user.setName("temp");
        user.setAccess("a");
        userList.add(user);
       	session.save(user);
       	
    	
    	session.getTransaction().commit();*/
           	
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
    	// Checks that no strings are empty
    	if(name.length() == 0)
    	{
    		throw new MissingResourceException("10002: Name field must be filled in.", null, null);
    	}
    	if(username.length() == 0)
    	{
    		throw new MissingResourceException("10002: Username field must be filled in.", null, null);
    	}
    	if(emailAddress.length() == 0)
    	{
    		throw new MissingResourceException("10002: Email field must be filled in.", null, null);
    	}
    	if(phoneNo.length() == 0)
    	{
    		throw new MissingResourceException("10002: Phone number field must be filled in.", null, null);
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
    
	public Guardian newGuardian(String name, String username, String emailAddress, String phoneNo, String password, String access) throws Exception
	{
    	// Checks that no strings are empty
		if(name.length() == 0)
		{
			throw new IllegalArgumentException("Name field must be filled in.");
		}
		if(username.length() == 0)
		{
			throw new IllegalArgumentException("Username field must be filled in.");
		}
		if(emailAddress.length() == 0)
		{
			throw new IllegalArgumentException("Email field must be filled in.");
		}
		if(phoneNo.length() == 0)
		{
			throw new IllegalArgumentException("Phone number field must be filled in.");
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
    	
    	if(dateJoined != null)
    	{
    		if(dob.compareTo(dateJoined) > 0)
    		{
    			throw new Exception("Date Joined cannot be before Date of Birth");
    		}
    	}
    	
		if(dob.compareTo(Calendar.getInstance()) > 0)
		{
			throw new Exception("Date of Birth cannot be after today");
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
	
	public Day addDay(Calendar date, ArrayList<Child> children, Room room, ArrayList<Setting> settings) throws Exception
	{
		if(date == null)
		{
			throw new Exception("Date is not in correct format or missing.");
		}
		if(children == null || children.size() == 0)
		{
			throw new Exception("No children have been selected for this day.");
		}
		if(settings == null || settings.size() == 0)
		{
			throw new Exception("No sessions have been selected for this day.");
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
		if(name.length() == 0)
		{
			throw new Exception("Name field is empty.");
		}
		if(level == 0)
		{
			throw new Exception("Level field is empty.");
		}
		if(description.length() == 0)
		{
			throw new Exception("Description field is empty.");
		}
		if(steps.length == 0)
		{
			throw new Exception("No steps have been added.");
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
			throw new Exception("The email address is not valid.");
		}

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
	
	public void addMark(Setting setting, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		if(setting == null)
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
		
		Mark tempMark = new Mark(setting, child, objective, step, mark, (Therapist)currentUser, day);
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);    	

		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	
    	dbSession.save(tempMark);
    	
    	dbSession.getTransaction().commit();
		
	}
	
	public void addTimestamp(Setting setting, Child child, Objective objective, Step step, int mark, Day day) throws Exception
	{
		Therapist t = (Therapist)currentUser;
		Mark tempMark = new Mark(setting, child, objective, step, mark, t, day);
		tempMark.setComments("Timestamp Logged.");
		day.addMark(tempMark);
		child.addMark(tempMark);
		markList.add(tempMark);
		t.addMark(tempMark);
		
    	org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	
    	dbSession.save(tempMark);
    	
    	dbSession.getTransaction().commit();
	
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
		updateObject(child);
	}

	
	// updates the values of the child to the values parsed in.
	public void updateChild(Child child, String name, Calendar dob, Calendar dateJoined) throws Exception {
    	if(name.length() == 0)
    	{
    		throw new Exception("Name is a required field.");
    	}
    	
    	if(dob == null)
    	{
    		throw new Exception("Date Of Birth is a required field.");
    	}
    	

     	if(dateJoined != null)
    	{
    		if(dob.compareTo(dateJoined) > 0)
    		{
    			throw new Exception("Date Joined cannot be before Date of Birth");
    		}
    	}
    	
		if(dob.compareTo(Calendar.getInstance()) > 0)
		{
			throw new Exception("Date of Birth cannot be after today");
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

	
	// Takes in a child object and objective object and adds the objective to the child
	public void addObjectiveChild(Child child, Objective objective) throws Exception {
		// Checks that neither object is null
		if(child == null || objective == null)	
		{
			throw new IllegalArgumentException("Child or Objective not selected.");
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
				throw new IllegalArgumentException("This objective already exists for this child.");
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

	// Returns the user account with the same username as the username
	// parsed through
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

	// Returns the string access level of the currently logged in user
	public String getCurrentAccess() {
		return currentUser.getAccess();
	}

	// Takes in a mark and all details required for a mark
	public void updateMark(Mark mark, Setting setting, Child child,
			Calendar time, Objective objective, Step step, int markVal,
			String comment) {
		
		mark.setSetting(setting);
		mark.setChild(child);
		mark.setTime(time);
		mark.setObjective(objective);
		mark.setStep(step);
		mark.setMark(markVal);
		mark.setComments(comment);
		
    	org.hibernate.Session dbSession = factory.getCurrentSession();

    	dbSession.beginTransaction();
    	dbSession.save(mark);
    	dbSession.getTransaction().commit();
		
	}
	
	// Takes in objective, strings for name and description, int level, and a string array for steps.
	// updates the details in the objective object with the ones parsed through and updates in
	// Database
	public void saveObjective(Objective objective, String name, String description, int level, String[][] steps) throws Exception
	{
		if(name.length() == 0)
		{
			throw new Exception("Name field is empty.");
		}
		if(level == 0)
		{
			throw new Exception("Level field is empty.");
		}
		if(description.length() == 0)
		{
			throw new Exception("Description field is empty.");
		}
		
		objective.setName(name);
		objective.setDescription(description);
		objective.setLevel(level);
		
		// Iterates through the array setting the value of the steps to the values parsed through
		int x = 1;
		for(int i = 0; i < steps.length; i++)
		{
			if(steps[i][0].isEmpty() || steps[i][1].isEmpty())
			{
				throw new Exception("Not all steps have been filled in.");
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


	// Takes in a boolean, child and objective object. if that objective exists in the child
	// the value of mastered will be set to the value parsed through
	public void setMastered(Child c, Objective o, boolean value) throws Exception {
		c.setMastered(o, value);
		updateObject(c);
	}

	// Takes in any object that is to be saved to the database. and saves it to the database.
	public void updateObject(Object o) {
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	o = (Object) session.merge(o);
    	session.save(o);
    	session.getTransaction().commit();
	}

	// Adds a guardian to a child
	public void addChildGuardian(Child child, Guardian guardian) throws Exception {
		
		// Check if guardian already exists in this child.
		ArrayList<Guardian> childsGuardians = new ArrayList<Guardian>(child.getGuardians());
		for(int i = 0; i < childsGuardians.size(); i++)
		{
			// if guardian exists throw an exception
			if(childsGuardians.get(i).equals(guardian))
			{
				throw new Exception ("Guardian already exists for this child");
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
	
	
	// Takes in any SimpleKey object. and plays the WAV file associated to that object.
	// Runs in a thread so that you can still navigate around while file is playing
	public void playSound(SimpleKey object) throws Exception {

		String filePath = object.getClass().getSimpleName();
		File file = new File(filePath + "\\" + object.getId() + ".wav");
		
		PlaySound ps = new PlaySound(file);
		Thread thread = new Thread(ps);
		thread.start();
		
	}
	
	// Takes in a child object and Objective object and removes that 
	public void removeObjective(Child c, Objective o)
	{
		ChildObjective co = c.removeObjective(o);
		//TODO Check removing objective correctly
        org.hibernate.Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(co);
        session.getTransaction().commit();
	}
	
	
	// Takes in a child object, an objective object and increments the 
	// current step of that objective for that child (if exists in the 
	// childs list of objectives)	
	public void incrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, 1);
		updateObject(c);
		updateObject(o);
	}

	// Takes in a child object, an objective object and decrements the 
	// current step of that objective for that child (if exists in the
	// childs list of objectives)
	public void decrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, -1);
		updateObject(c);
		updateObject(o);
	}
	
	// Takes in an objective and an objective type and sets the objective
	// to the objectiveType parsed in.
	public void setObjectiveType(Objective objective, ObjectiveType objectiveType) {
		objective.setType(objectiveType);
		objectiveType.addObjective(objective);

		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	objective = (Objective) session.merge(objective);
    	session.update(objective);
    	session.getTransaction().commit();
		
	}
	
	// Creates a new objective type object, adds it to the system
	// and saves it to the database
	public void addObjectiveType(String name) {
		ObjectiveType objType = new ObjectiveType(name);
		
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	session.save(objType);
    	session.getTransaction().commit();
    	
    	objectiveTypeList.add(objType);
	}
	
	public void removeObjectiveType(ObjectiveType objType) {

		boolean temp = objectiveTypeList.remove(objType);
		org.hibernate.Session session = factory.getCurrentSession();
		session.beginTransaction();
		objType = (ObjectiveType) session.merge(objType);
		session.delete(objType);
		session.getTransaction().commit();
		
		
		
		System.out.println("Successfully?: " + temp);
	}
	
	public void setSettingTypeList(Setting setting, List<ObjectiveType> objTypeList) {
		setting.setObjectives(objTypeList);
		updateObject(setting);
		//TODO Check Setting Type List in DB
	}


	// Takes in a string for the name of the room.
	// Creates a new Room object with that name and 
	// adds it to the database
	public void addRoom(String name) {
		Room room = new Room(name);

    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	session.save(room);
    	session.getTransaction().commit();
    	
    	roomList.add(room);
	}

	
	// Takes in a room object and a string for room name
	// Updates the room objects name to the string and updates
	// with the database
	public void editRoom(Room room, String name) {
		if(name.length() == 0)
		{
			throw new IllegalArgumentException("Name cannot be null.");
		}
		room.setRoomName(name);
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	room = (Room) session.merge(room);
    	session.update(room);
    	session.getTransaction().commit();
	}

	// Takes in a room object if it has no foreign constraints it is
	// removed from the database and system. otherwise it throws
	// an SQL exception
	public void removeRoom(Room room) {
		
		org.hibernate.Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		String qry = ("Select count(*) from Day day where day.roomId = " + room.getRoomName());
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
    		throw new IllegalArgumentException("Unable to delete this room as it exists in a day object");
    	}
		
    	
    	
	}
	

	
	// Takes in a session object and sets the name attribute to the string
	// that is parsed in
	public void updateSetting(Setting setting, String description) throws Exception {

		if(description.length() == 0)
		{
			throw new Exception("You must enter a value for description");
		}
		setting.setDescription(description);
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	setting = (Setting) session.merge(setting);
    	session.update(setting);
    	session.getTransaction().commit();
	}
	
	// Takes in a session object. if it has no foreign constraints it is
	// removed from the database and system. otherwise it throws
	// an SQL exception
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
    		throw new IllegalArgumentException("Setting cannot be deleted as it is in a day object.");
    	}
    	
    	session.getTransaction().commit();
    	
	}
	
	// Takes in a user account and a boolean enabled, sets the user accounts
	// access attribute to d (Disabled)
	public void disableAccount(UserAccount u) {
		
		u.setAccess("d");
		
    	org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	u = (UserAccount) session.merge(u);
    	session.update(u);
    	session.getTransaction().commit();
		
	}

	// Takes in a name, creates a new setting object with that name
	// and adds it to the database and list
	public void addSetting(String name) {
		Setting s = new Setting(name);
		
    	org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	dbSession.save(s);
    	dbSession.getTransaction().commit();
    	
    	settingList.add(s);
	}

	public Collection<ObjectiveType> getObjectiveTypeList() {
		return objectiveTypeList;
	}
	

    public List<UserAccount> getUserList() {
		return userList;
	}
	
	
	

}