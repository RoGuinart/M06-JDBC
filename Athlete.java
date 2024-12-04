package JDBC_RGuinart;


public class Athlete {
	
	private static long nextCode = 0;
	private long code;
	private String name;
	private long sport_code;
	
	// Save DAO pointer here to quickly access sport names
	private static SportDAO DAO = null;
	
	public static void setupDAO(SportDAO spDAO)
	{
		// TODO: get highest code from DB
		Athlete.DAO = spDAO;
	}
	
	public Athlete(String name) {
		this.code = nextCode++;
		this.name = name;
		this.sport_code = -1; // TODO: fix this
	}
	
	// User input
	public Athlete(String name, String sport) {
		this.code = nextCode++;
		this.name = name;
		this.sport_code = findSportCode(sport);
	}
	
	public Athlete(String name, long sport_code)
	{
		this.code = nextCode++;
		this.name = name;
		this.sport_code = sport_code;
	}
	
	// DB SELECT
	public Athlete(long code, String name, long sport_code) {
		this.code = code;
		this.name = name;
		this.sport_code = sport_code;
	}

	public long getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public long getSportCode()
	{
		return sport_code;
	}
	
	public String getSportName() {
		if(DAO == null || sport_code == -1)
			return "";
		
		for (Sport sp : DAO.getAll()) {
			if(sp.getCode() == sport_code)
					return sp.getName();
		}
		
		// Sport does not exist.
		return "";
	}

	private long findSportCode(String sport)
	{
		if(DAO == null)
			return -1;
		
		sport = sport.toLowerCase();
		
		for (Sport sp : DAO.getAll()) {
			if(sport.equals( sp.getName().toLowerCase() ))
					return sp.getCode();
		}
		
		// Sport does not exist.
		return -1;
	}

}
