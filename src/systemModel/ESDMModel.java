/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemModel;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 *
 * @author DAMIAN
 */
public class ESDMModel {
    
    private Set<Child> childList;
    private Set<Setting> settingList;
    private Set<Session> sessionList;
    private Set<Guardian> guardianList;
    private Set<Therapist> therapistList;
    private Set<Objective> objectiveList;
    private Set<Mark> markList;
    private Set<Day> dayList;
    private List<Room> roomList;
    private UserAccount currentUser;
    
    public ESDMModel() throws MalformedURLException
    {
        childList = new HashSet<Child>();
        settingList = new HashSet<Setting>();
        sessionList = new HashSet<Session>();
        therapistList = new HashSet<Therapist>();
        objectiveList = new HashSet<Objective>();
        guardianList = new HashSet<Guardian>();
        markList = new HashSet<Mark>();
        roomList = new ArrayList<Room>();
        currentUser = null;
        loadFromDatabase();
        
    }

    public Collection<Child> getChildList() {
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
    }
    
    public boolean login(String username, char[] password)
    {
        
        currentUser = new UserAccount();
        return true;
    }
    
    
    /****************************************************
     *  Returns true if a user is currently logged in   *
     *  False otherwise                                 *
     *                                                  *
     * **************************************************/
    
    public boolean loggedIn()
    {
        if(currentUser != null)
        {
            return true;
        }
        return false;
    }
    
    public void addChild(String name, Date dob, Guardian guardian)
    {
        Child child = new Child(name, dob);
        child.addGuardian(guardian);
        
        childList.add(child);
        
        System.out.println(child.getId());
        System.out.println(child.getName());
        System.out.println(child.getDob());
        System.out.println(child.getDateJoined());
        ArrayList<Guardian> temp = new ArrayList<Guardian>(child.getGuardians());
        
    }
    
	public Child viewChild(String childId)
    {
        Child child = (Child)Helper.search((Collection<Child>)childList, childId);
        
        return child;
    }
	
	
	
	public void addDay(Date date, ArrayList<Child> children, String room, ArrayList<Session> sessions)
	{
		
		
	}
	


    
}
