package JDBC_RGuinart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AthleteDAO implements DAO<Athlete>
{	
	
	
	public AthleteDAO()
	{

	}

	@Override
	public void add(Athlete ath)
	{
		Connection c;
		try {
			c = DBConnection.open();
		
			PreparedStatement ps = c.prepareStatement("INSERT INTO athletes(name, sport_code) VALUES(?, ?);");
			
			ps.setString(1, ath.getName());
			ps.setLong(2, ath.getSportCode());
			
	        ps.execute();
	        
			DBConnection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public ArrayList<Athlete> getAll() 
	{
		 ArrayList<Athlete> athList = new ArrayList<Athlete>();
			Connection c;
			try {
				c = DBConnection.open();
			
				Statement s = c.createStatement();
				
		        ResultSet r = s.executeQuery("SELECT code, name, sport_code FROM athletes;");
		        
				DBConnection.close();
				
				while (r.next()) {
					long code = r.getLong(1);
					String name = r.getString(2);
					long sport_code = r.getLong(3);
					
					Athlete ath = new Athlete(code, name, sport_code);
					
					athList.add(ath);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return athList;	
		}

	public ArrayList<Athlete> findAthlete(String name) {
		ArrayList<Athlete> athList = new ArrayList<Athlete>();
		Connection c;
		try {
			c = DBConnection.open();
		
			PreparedStatement ps = c.prepareStatement("SELECT code, name, sport_code FROM athletes WHERE UPPER(name) LIKE UPPER(?);");
			
			ps.setString(1, '%' + name + '%');
			
			ResultSet r = ps.executeQuery();
	        
			DBConnection.close();
			
			while (r.next()) {
				long athCode = r.getLong(1);
				String athName = r.getString(2);
				long athSport = r.getLong(3);
				
				Athlete ath = new Athlete(athCode, athName, athSport);
				
				athList.add(ath);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return athList;
	}
	
	public ArrayList<Athlete> findAthleteWithSport(long sport_code)
	{
		ArrayList<Athlete> athList = new ArrayList<Athlete>();
		Connection c;
		try {

		c = DBConnection.open();
			
			PreparedStatement ps = c.prepareStatement("SELECT code, name FROM athletes WHERE sport_code = ?;");
			
			ps.setLong(1, sport_code);
			
			ResultSet r = ps.executeQuery();
	        
			DBConnection.close();
			
			while (r.next()) {
				long athCode = r.getLong(1);
				String athName = r.getString(2);
				long athSport = sport_code;
				
				Athlete ath = new Athlete(athCode, athName, athSport);
				
				athList.add(ath);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return athList;
	}
}
