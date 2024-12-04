package JDBC_RGuinart;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	
	private static AthleteDAO athDAO;
	private static SportDAO sptDAO;
	
	/**
	 * Initialize necessary components.
	 */
	private static void setup()
	{
		athDAO = new AthleteDAO();
		sptDAO = new SportDAO();
		
		// Give a reference to the Athlete DAO to quickly access the sports list
		// There would be no need for this if the DAO methods were static
		Athlete.setupDAO(sptDAO);
		
		DBConnection.init("database.xml");
	}

	public static void main(String[] args) {
		
		int choice;
		boolean loop = true;
		Scanner in = new Scanner(System.in);

		setup();
		
		while(loop) {

			choice = View.menu(in);
	        switch (choice) {
	        case 1: // Add new sport
	        	Sport spt = View.SportForm(in);
	        	sptDAO.add(spt);
	        	break;

	        case 2: // Add new athlete
	        	Athlete ath = View.AthleteForm(in, sptDAO.getAll());
	        	if(ath != null)
	        		athDAO.add(ath);
	        	break;

	        case 3: // Search athletes by name
	        	String name = View.AskAthleteName(in);
	        	
	        	ArrayList<Athlete> found = athDAO.findAthlete(name);
	        	
	        	View.AthleteList(found);
	        	break;

	        case 4: // List athletes by sport
	        	long sport = View.getSport(in, sptDAO.getAll());
	        	View.AthleteList(athDAO.findAthleteWithSport(sport));
	        	break;

	        case 5: // Show all sports
	        	View.SportsList(sptDAO.getAll(), false);
	        	break;
	        	
	        case 6: // Show all athletes and the sports they practise
	        	View.AthleteList(athDAO.getAll());
	        	break;

	        case 0: // Quit
	        	System.out.println("Shutting down...");
	        	loop = false;
	        	break;

	        default:
	        	System.err.println("Invalid input.");
	        }
		};
	}
}
