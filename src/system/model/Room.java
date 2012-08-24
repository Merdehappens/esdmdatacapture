package system.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {
	@Id
	@GeneratedValue
	private int id;
	private String roomName;
	
	public Room(int id, String roomName)
	{
		
		this.id = id;
		this.roomName = roomName;
	}
	
	public Room()
	{
		
	}
	
	public Room(String roomName)
	{
		this.roomName = roomName;
	}
	
	public String toString()
	{
		return roomName;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getRoomName()
	{
		return roomName;
	}
	
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}
}
