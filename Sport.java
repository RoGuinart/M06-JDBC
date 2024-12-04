package JDBC_RGuinart;

public class Sport  {

	private static long nextCode = 0; // TODO: get highest number from DB
	private long code;
	private String name;
	
	// User input
	public Sport(String name)
	{
		this.code = nextCode++;
		this.name = name;
	}
	
	// DB SELECT
	public Sport(long code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public long getCode()
	{
		return code;
	}
}
