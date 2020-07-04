package cse222.group8.server;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cse222.group8.server.DataStructures.*;


/**
 * The type Main.
 */
public class Main {


	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		ShelterSystem system = new ShelterSystem();

		readCityInfo(system);
		printCitiesInfo(system);
		insertTownsTests(system);

		shelterTests(system);

		BinarySearchTree<City> cities 	= system.getCitiesBST();
		City istanbul = cities.find(new City("Istanbul", 34));
		Town kagithane = istanbul.getTown("Kagithane");

		Shelter dogaevi = new Shelter("dogaevi", istanbul, kagithane, 23, 11, "Sultan selim mah. No 3", "+902122222415", "stockpass");
		kagithane.getShelters().add(dogaevi);
		dogaevi.register();
		dogaevi.addCat(new Animal("korpe","tekir",3,true,dogaevi));
		dogaevi.addCat(new Animal("sari","sarman",6,true,dogaevi));
		dogaevi.addDog(new Animal("pasa","kangal",12,false,dogaevi));
		User user = new User();
		user.setUsername("ismltpn");
		user.setName("ismail tapan");
		user.createARequest(dogaevi.getCat(1));

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.scheduleAtFixedRate(new DailyExecutions(system),0,1, TimeUnit.DAYS);
		JavalinServer javalinServer = new JavalinServer(system);
//		Thread javalinThread = new Thread(javalinServer);
//		javalinThread.start();
//		AdminUI ui = new AdminUI(system);
//		ui.run();
//		javalinThread.stop();
	}

	private static void testAddAnimal(){
		ShelterSystem system = new ShelterSystem();
		City istanbul = new City("Istanbul", 34);
		Town pendik = new Town("Pendik", istanbul);
		Shelter myShelter = new Shelter("Pendik Barinagi",istanbul,pendik,10,10,"yeni mah","3759630","123456");

		myShelter.addCat(new Animal("Tyson","Scottishfold",2,true,myShelter));
		myShelter.addCat(new Animal("Mahmut","Tekir",4,true,myShelter));
		myShelter.addCat(new Animal("Mia","Ankara",12,true,myShelter));
		myShelter.addCat(new Animal("Yumi","Van",1,true,myShelter));
		Animal animal=new Animal("Leia","Corgi",2,false,myShelter);
		myShelter.addDog(animal);
		myShelter.addDog(new Animal("Baron","Rottweiler",8,false,myShelter));

		for(Animal cat: myShelter.getCats()){
			System.out.println(cat.getName());
		}
		System.out.println("-----");
		for(Animal dog: myShelter.getDogs()){
			System.out.println(dog.getName());
		}
		System.out.println("-----");
		for(Animal dog: myShelter.getDogs()){
			if(dog.getName().equals("Leia")) {
				dog.updateAnimal(new Animal("Pamuk", "abc", 13, false,myShelter));
			}
		}
		for(Animal dog: myShelter.getDogs()){
			System.out.println(dog.getName());
		}
	}
	
	private static void testAdminUI() {
		
		// TEST
		ShelterSystem system = new ShelterSystem();
		Shelter shelter = new Shelter("Test1", null, null, 10, 10,
									 "","","pass1");
		system.addCapChangeRequest(new CapacityChangeRequest("Ist", "Krtl", shelter, 120,130));
		system.addNewShelterRequest(new ShelterRequest(new City("Ist", 34), "Krtl2", shelter));
		system.addRemoveShelterRequest(new ShelterRequest(new City("Ist3", 34), "Krtl3", shelter));
		AdminUI ui = new AdminUI(system);
		ui.run();
		
	}


	private static void testCities() {
		
		//its a dummy system for test	
		ShelterSystem system = new ShelterSystem();
		BinarySearchTree<City> cities 	= system.getCitiesBST();
		TreeMap<Integer, City> cityIds	= system.getCityIdsMap();
		ListGraph borderCities 			= system.getBorderCities();
		
		

		readCityInfo(system);
		
		
		City city = cities.find(new City("Mersin", 0));
		System.out.println(city.getCityId());
		
		System.out.println(cityIds.get(city.getCityId()).getName());
		
		System.out.println(" ");
		
		
		Iterator<Edge> iter = borderCities.edgeIterator(city.getCityId());
		while(iter.hasNext()) {
			
			int id = iter.next().getDest();
			
			System.out.println(cityIds.get(id).getName());
			
		}
	}

	protected static void printCitiesInfo(ShelterSystem system){
		BinarySearchTree<City> cities 	= system.getCitiesBST();
		System.out.println(cities.toString());
	}

	protected static void insertTownsTests(ShelterSystem system){

		File file = new File("src/main/Constants/Towns.txt");

		try {

			Scanner sc = new Scanner(file);

			while( sc.hasNextLine() ) {

				String str = sc.nextLine();
				String[] townsInfo = str.split(" ");
				if(townsInfo.length < 2){
					System.out.println("The file of towns missing info!");
					break;
				}
 				String id = townsInfo[1];
				City city = system.getCity(Integer.parseInt(id));

				city.towns.add(new Town(townsInfo[0], city));

			}

			sc.close();

		}
		catch ( Exception e ) {
			System.out.println(e.getMessage());
			System.err.println("Something went wrong while reading towns from file");
			System.exit(0);

		}

	}


	private static Shelter getShelterFromStrByPrefix(ShelterSystem system, String input, String prefix){
		if(input.isEmpty() || input.isBlank()) return null;
		String[] lineStr = input.split(prefix);
		Shelter shelter = new Shelter();
		String test = lineStr[0].trim();
		shelter.setCity(system.getCity(Integer.parseInt(test)));
		shelter.setName(lineStr[1].trim());
		shelter.setAddress(lineStr[2].trim());
		shelter.setPhoneNumber(lineStr[3].trim());
		return shelter;
	}

	protected static void shelterTests(ShelterSystem system){
		File file = new File("src/main/Constants/Shelters.txt");

		try {

			Scanner sc = new Scanner(file);

			while(sc.hasNextLine()){
				String str = sc.nextLine();
				int citySize, randNum;
				Town town;

				Shelter shelter = getShelterFromStrByPrefix(system, str, ",");
				City city = new City(shelter.getCity().getName(), shelter.getCity().getCityId());
				citySize = shelter.getCity().towns.size();
				city.towns = shelter.getCity().towns;
				randNum = new Random().nextInt(citySize);
				town = shelter.getCity().getTownByNumber(randNum);

				ShelterRequest shelterRequest = new ShelterRequest(city, town.getName(), shelter);
				system.addShelter(shelterRequest);
			}

			sc.close();
		}
		catch ( IOException e ) {
			System.out.println(e.getMessage());
			System.err.println("Error occurred while opening or reading given file!");
			System.exit(0);
		}
		catch (NullPointerException e ) {
			System.out.println(e.getMessage());
			System.err.println("Error occurred while getting town from City");
			System.exit(0);
		}
	}


	/**
	 * Read city ınfo.
	 *
	 * @param system the system
	 */
	protected static void readCityInfo(ShelterSystem system) {
		
		BinarySearchTree<City> cities 	= system.getCitiesBST();
		TreeMap<Integer, City> cityIds	= system.getCityIdsMap();
		ListGraph borderCities 			= system.getBorderCities();

		File file = new File("src/main/Constants/Cities.txt");

		try {
			
			Scanner sc = new Scanner(file);
			
			while( sc.hasNextLine() ) {
				
				String str = sc.nextLine();
				String[] keys = str.split(" ");
				
				int cityID = Integer.parseInt(keys[0]);
				City city = new City( keys[1], cityID );

				cities.add(city);
				cityIds.put(cityID, city);

				for(int i=2; i < keys.length; i++ ) {
					
					Edge edge = new Edge(cityID, Integer.parseInt(keys[i]));
					borderCities.insert(edge);
				}
			}
			
			sc.close();
		}
		catch ( Exception e ) {
			System.out.println(e.getMessage());
			System.err.println("Something went wrong while reading cities from file");
			System.exit(0);
		}
		
		
	}
	
	
	

}
