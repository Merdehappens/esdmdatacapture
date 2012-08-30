
package system.model;



import java.net.MalformedURLException;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import system.helper.Helper;
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
		config.addAnnotatedClass(Room.class);
		//config.addAnnotatedClass(ObjectiveType.class);
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
    	org.hibernate.Session session = factory.openSession();
    /*  session.beginTransaction();
    	
      	Therapist user = new Therapist();
        user.setUsername("temp");
        user.tempSetPassword("temp");
        user.setName("temp");
        user.setAccess("a");
        userList.add(user);
       	session.save(user);
       	
       	
        Guardian guard = new Guardian();
        guard.setUsername("guard");
        guard.tempSetPassword("guard");
        guard.setName("guard");
        guard.setAccess("g");
        userList.add(guard);
       	session.save(guard);
               

        session.save(new Room("Room 1"));
        session.save(new Room("Room 2"));
        session.save(new Room("Room 3"));

        session.save(new Session("Meals"));
        session.save(new Session("Toilet"));
        session.save(new Session("Group"));
        session.save(new Session("One-On-One"));
    	
    	session.getTransaction().commit();

*/    
           	
    	String qry = ("Select sess from Session sess");
    	Query query = session.createQuery(qry);
    	
    	for(Iterator it = query.iterate(); it.hasNext();)
    	{
    		Session s = (Session) it.next();
    		sessionList.add(s);
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
    	
    	qry = ("Select day from Day day");
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

		org.hibernate.Session dbSession = factory.getCurrentSession();
    	dbSession.beginTransaction();
    	
    	dbSession.save(tempMark);
    	
    	dbSession.getTransaction().commit();
		
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
		
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	child = (Child) session.merge(child);
    	session.save(child);
    	session.getTransaction().commit();
		
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
		
    	org.hibernate.Session session = factory.getCurrentSession();

    	session.beginTransaction();
    	child = (Child) session.merge(child);
    	session.save(child);
    	session.getTransaction().commit();

		
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
		
    	org.hibernate.Session dbSession = factory.getCurrentSession();

    	dbSession.beginTransaction();
    	dbSession.save(mark);
    	dbSession.getTransaction().commit();
		
	}
	
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
		
		objective.getStepsNo();
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
		
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	objective = (Objective) session.merge(objective);
    	session.save(objective);
    	session.getTransaction().commit();
	}



	public void setMastered(Child c, Objective o) throws Exception {
		c.setMastered(o, true);
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

	public void addChildGuardian(Child child, Guardian guardian) throws Exception {
		
		System.out.println("Child: " + child.getId() + " Guardian: " + guardian.getId());
		
		ArrayList<Guardian> childsGuardians = new ArrayList<Guardian>(child.getGuardians());
		for(int i = 0; i < childsGuardians.size(); i++)
		{
			
			if(childsGuardians.get(i).equals(guardian))
			{
				throw new Exception ("Guardian already exists for this child");
			}
		}
		
		guardian.addChild(child);
		child.addGuardian(guardian);
		
		org.hibernate.Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	guardian = (Guardian) session.merge(guardian);
    	child = (Child) session.merge(child);
    	
    	session.update(guardian);
    	session.update(child);
    	session.getTransaction().commit();
	}
	
	public void removeObjective(Child c, Objective o)
	{
		c.removeObjective(o);
		// TODO Save 
	}
	
	public void incrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, 1);
		// TODO SAve to dB
	}

	public void decrementStep(Child c, Objective o) throws Exception {
		c.incrementStep(o, -1);
		// TODO SAve to dB
	}
	
	
	public void setObjectiveType(Objective objective, ObjectiveType objectiveType) {
		//TODO Add Code Here
	}
	
	public void addNewObjectiveType(String name) {
		//TODO Add Code Here
	}
	
	

}