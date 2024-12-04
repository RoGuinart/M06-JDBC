package JDBC_RGuinart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SportDAO implements DAO<Sport>
{	

	public SportDAO()
	{

	}

	@Override
	public void add(Sport sp) {
		Connection c;
		try {
			c = DBConnection.open();
		
			Statement s = c.createStatement();
			
			s.executeUpdate("INSERT INTO SPORTS (NAME) VALUES ('" + sp.getName() + "');");
				        
			DBConnection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}		
	}

	@Override
	public ArrayList<Sport> getAll() {
		ArrayList<Sport> spList = new ArrayList<Sport>();
		Connection c;
		try {
			c = DBConnection.open();
		
			Statement s = c.createStatement();
			
	        ResultSet r = s.executeQuery("SELECT code, name FROM sports;");

			DBConnection.close();

			while (r.next()) {
				long code = r.getLong(1);
				String name = r.getString(2);
				
				Sport sp = new Sport(code, name);
				
				spList.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return spList;
	}
	
}
