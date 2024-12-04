package JDBC_RGuinart;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{

	private static String host;
	private static int port;
	private static String database;
	private static String user;
	private static String passwd;

	private static Connection c;

	private static boolean active = false;
	private static boolean initted = false;
	
	/**
	 * Read database details from XML file
	 * 
	 * @param config_file Configuration file to read database credentials from
	 */
	public static void init(String config_file)
	{
		File file = new File(config_file);
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			host = doc.getElementsByTagName("host").item(0).getTextContent();
			port = Integer.parseInt(doc.getElementsByTagName("port").item(0).getTextContent());
			user = doc.getElementsByTagName("user").item(0).getTextContent();
			passwd = doc.getElementsByTagName("password").item(0).getTextContent();
			database = doc.getElementsByTagName("database").item(0).getTextContent();

			initted = true;
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static Connection open() throws SQLException
	{
		if(!initted)
			throw new SQLException("Database has not been initiated!");

		// Utilitzo un bool per evitar crear més d'una connexió alhora (memleaks)
		if(!active) {
			final String server = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
			c = DriverManager.getConnection(server, user, passwd);
			active = true;
		}
		
		return c;
	}
	
	public static void close()
	{
		if(active) {
			try {
				c.close(); // Why would a close function ever fail?
			} catch (SQLException e) {
				e.printStackTrace();
			}
			active = false;
		}
	}
}
