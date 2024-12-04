package JDBC_RGuinart;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
	
	public static int menu(Scanner in)
	{
        return getInt(in,
        		"What operation do you want to perform?\n"
        	 +  "0. Quit\n"
			 +	"1. Add sport\n"
			 +	"2. Add athlete\n"
			 +	"3. Search athletes by name\n"
			 +	"4. List athletes that practise a specific sport\n"
			 +	"5. List all sports\n"
			 +  "6. List all athletes and the sports they practise\n"
		);
	}
	
	public static Sport SportForm(Scanner in)
	{
		return new Sport(getString(in, "Enter sport name: "));
	}
	
	public static Athlete AthleteForm(Scanner in, ArrayList<Sport> sports)
	{
		String name;
		long sport;
		name =  getString(in, "Enter athlete name: ");
		sport = getSport(in, sports);
		
		if(sport == -1)
			return null;

		return new Athlete(name, sport);
	}

	public static void AthleteList(ArrayList<Athlete> aths)
	{
		System.out.printf("\nAthlete list:\n");
		System.out.printf(" %25s | %s\n\n", "Athlete name", "Sport name");
		for (Athlete ath : aths) {
			System.out.printf(" %25s | %s\n", ath.getName(), ath.getSportName());
		}
		System.out.println(); // Blank line
	}
	
	public static void SportsList(ArrayList<Sport> sps, boolean numbered)
	{
		System.out.printf("Sports list:\n");
		int i = 0;
		for (Sport sport : sps) {
			if(numbered)
				System.out.printf("%d. %s\n", i++, sport.getName());
			else
				System.out.println(sport.getName());
		}
		System.out.println(); // Blank line
	}
	
	public static String AskAthleteName(Scanner in)
	{
		return getString(in, "Enter an athlete's name: ");
	}
	
	//
	// HELPER FUNCTIONS
	//

    /**
     * Asks for an integer number. Does not leave until it receives a valid input.
     * @param in Scanner
     * @param prompt Message to print before receiving input.
     * @return integer input
     */
    static int getInt(Scanner in, String prompt) 
    {
        int result = 0;
        boolean error;
        do {
            error = false;
            System.out.println(prompt);
            if(in.hasNextInt()) {
                result = in.nextInt();
            } else {
                System.err.println("Invalid input.");
                error = true;
            }
            in.nextLine();
        } while (error);
        return result;
    }

    /**
     * Asks for a string
     * @param in Scanner
     * @param prompt Prompt to print before receiving input.
     * @return String written by the user
     */
    static String getString(Scanner in, String prompt) 
    {
        String result = "";
        boolean error;
        do {
            error = false;
            System.out.println(prompt);
            if(in.hasNextLine()) {
                result = in.nextLine();
            } else {
                System.err.println("Invalid input.");
                error = true;
            }
        } while (error);
        return result;
    }
	
    /**
     * Asks for a sport
     * 
     * @param in Scanner
     * @param sports List of available sports
     * @return Code of the selected sport or -1 if cancelled.
     */
	static long getSport(Scanner in, ArrayList<Sport> sports)
	{
		int totalSports = sports.size();
		int result = -1;
		boolean error;
		
		System.out.println("Type \"quit\" to cancel, \"list\" to show the list again.");
		// Show available sports
		SportsList(sports, true);
		
        do {
            error = false;

            if(in.hasNextInt()) {
                result = in.nextInt();
                
                if(result >= totalSports || result < 0) {
                	System.err.println("Invalid input.");
                	error = true;
                }
            } else {
        		String tmp = in.nextLine();
        		if(tmp.equals("quit"))
        			return -1;
        		else if(tmp.equals("list"))
        			SportsList(sports, true);           		
            	else 
            		System.err.println("Invalid input.");
            	
                error = true;
                continue;
            }
            in.nextLine();
        } while (error);
        
        Sport sp = sports.get(result);
        return sp.getCode();
	}
}
	