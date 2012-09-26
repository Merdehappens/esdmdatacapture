package system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import system.helper.SimpleKey;

@Entity
public class Room {
	@Id
	private String roomName;
	
	/**
	 * Creates a new empty room
	 */
	public Room()
	{
		
	}
	
	/**
	 * Creates a new room with the name passed through
	 * @param roomName
	 */
	public Room(String roomName)
	{
		this.roomName = roomName;
	}
	
	/**
	 * Returns the name of the room
	 * @return room
	 */
	public String getRoomName()
	{
		return roomName;
	}
	
	/**
	 * Sets the name of the room to the String passed through
	 * @param name
	 */
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	public String toString()
	{
		return roomName;
	}
	
}
