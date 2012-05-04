package systemModel;

public class Room {
	private String id;
	private String roomName;
	
	public Room(String id, String roomName)
	{
		this.id = id;
		this.roomName = roomName;
	}
	
	public Room(String roomName)
	{
		this.roomName = roomName;
	}
	
	public String toString()
	{
		return roomName;
	}
}
