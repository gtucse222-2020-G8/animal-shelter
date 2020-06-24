package cse222.group8.server;

import java.util.Scanner;


/**
 * The type Admin uı.
 */
public class AdminUI {

	/**
	 * The System.
	 */
	ShelterSystem system_;

	/**
	 * The Scan.
	 */
	Scanner scan;


	/**
	 * Instantiates a new Admin uı.
	 */
	public AdminUI() {
		
		System.err.println("UI needs a system.");
		System.exit(0);
		
	}


	/**
	 * Instantiates a new Admin uı.
	 *
	 * @param system the system
	 */
	public AdminUI( ShelterSystem system ) {
		
		this.system_ = system;
		this.scan = new Scanner(System.in);
		
	}


	/**
	 * Run.
	 */
	public void run() {
		mainMenu();
	}
	
	
	
	
	
	/**
	 * Calls features according to input
	 */
	private void mainMenu() {
		boolean exit = false;
		printMainMenu();
		do {
			String input = ConsoleHelpers.getIntegerInput(scan);
			switch (input) {
			case "1":
				addShelter();
				printMainMenu();
				break;
			case "2":
				updateShelter();
				printMainMenu();
				break;
			case "3":
				removeShelter();
				printMainMenu();
				break;
			case "0":
				exit = true;
				break;
			default:
				System.out.println("Invalid input!");
			}
		} while (!exit);
	}
	
	
	
	
	
	
	private void addShelter() {
		
		ShelterRequest request = system_.getNextNewShelterRequest();
		
		if( request == null) {
			System.out.println("There is no request for add shelter!\n\n");
			return;
		}
		
		printRequest(request);
		
		boolean confirmed = askForYesNo();
		
		if(confirmed) {
			if( system_.addShelter(request) )
				System.out.println("Success!");
			else
				System.out.println("Invalid request inputs!");
		}
		else {
			System.out.println("Request declined!");
		}
		System.out.println("\n\n");
		
	}
	
	
	
	
	
	private void updateShelter() {
		
		CapacityChangeRequest request = system_.getNextCapChangeRequest();
		
		if( request == null) {
			System.out.println("There is no request for update shelter!\n\n");
			return;
		}
		
		printCapReq(request);
		
		boolean confirmed = askForYesNo();
		
		if(confirmed) {
			system_.changeShelterCap(request);
			System.out.println("Success!");
		}
		else {
			System.out.println("Request declined!");
		}
		System.out.println("\n\n");
		
	}


	
	
	
	private void removeShelter() {
	
		ShelterRequest request = system_.getNextRemoveShelterRequest();
		
		if( request == null) {
			System.out.println("There is no request for add shelter!\n\n");
			return;
		}
		
		printRequest(request);
		
		boolean confirmed = askForYesNo();
		
		if(confirmed) {
			if( system_.removeShelter(request) )
				System.out.println("Success!");
			else
				System.out.println("Invalid request inputs!");

		}
		else {
			System.out.println("Request declined!");
		}
		System.out.println("\n\n");
		
	}
	
	
	
	
	
	private void printRequest(ShelterRequest request) {
		
		Shelter shelter = request.shelter;
		
		System.out.println("\n\nNext request >>\n");
		System.out.printf("Name: %s\n"
				+ "City: %s\n"
				+ "Town: %s\n"
				+ "Cat capacity: %s\n"
				+ "Dog capacity: %s\n"
				, shelter.getName(), request.city, request.town, 
				shelter.getCatCapacity(), shelter.getDogCapacity());
		
	}
	
	
	
	
	private void printCapReq(CapacityChangeRequest request) {
		
		Shelter shelter = request.shelter;
		
		System.out.println("\n\nNext request >>\n");
		System.out.printf("Name: %s\n"
				+ "City: %s\n"
				+ "Town: %s\n"
				+ "New Cat capacity: %s\n"
				+ "New Dog capacity: %s\n"
				, shelter.getName(), request.city, request.town, 
				request.catCapacity, request.dogCapacity);
		
	}
	
	
	
	
	private boolean askForYesNo() {
		
		do {
			
			printYesNo();
			String input = ConsoleHelpers.getIntegerInput(scan);
			
			switch (input) {
			case "1":	
				return true;	
			case "2":	
				return false;	
			default:
				System.out.println("Invalid input!");
			}
			
		} while (true);
		
	}
	
	
	
	private void printMainMenu() {
		
		System.out.println("######################\n"
				+ "Shelter System\n"
				+ "Admin Panel\n"
				+ "######################\n"
				+ "\n"
				+ "1- Add shelter\n"
				+ "2- Update shelter\n"
				+ "3- Remove shelter\n"
				+ "0- Exit\n" );
		
	}
	
	
	
	private void printYesNo() {
		
		System.out.println( "\nDo you confirm?\n"
				+ "1- YES\n"
				+ "2- NO\n");
		
	}
	
	

}


/**
 * Console helpers for Admin UI.
 *
 * @author Berk Pekgoz
 */
class ConsoleHelpers{


	/**
	 * Gets Integer input with specified scanner
	 *
	 * @param scan Scanner
	 * @return Integer ınteger ınput
	 */
	public static String getIntegerInput(Scanner scan) {
		while (true)
			try {
				System.out.print("Input: ");
				String inputStr = scan.nextLine();
				Integer input = Integer.parseInt(inputStr);
				return input.toString();
			} catch (Exception e) {
				System.out.println("Please enter a Integer number.");
			}
	}

	
}
