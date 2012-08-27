package system.helper;


// Any class that implements SimpleKey must implement getId. this is required for the Search method in Helper class 

public interface SimpleKey
{
	public abstract int getId();

	public boolean equals(Object o);
}