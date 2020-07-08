package cse222.group8.server;

import cse222.group8.server.DataStructures.BinarySearchTree;
import cse222.group8.server.DataStructures.Edge;
import cse222.group8.server.DataStructures.ListGraph;

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tests {
    public static void main(String[] args){
        testAll();
    }

    public static void testAll(){
        shelterAddCat();
        shelterAddDog();
        shelterDeleteAnimal();
        shelterAddDiseasedAnimal();
        shelterAddMultipleDiseasedAnimals();
        shelterApproveAdoptionRequest();
        shelterAddTask();
        shelterApproveTask();
        shelterUpdateTask();
        shelterDeleteTask();
        systemTest();
        testCities();
        testAdminUI();
        userTest();
        testAddAnimal();
    }
    public static void shelterAddCat(){
        System.out.println("Testing add cat functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,
                10,"asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        if(testShelter.getCatSize()==1){
            for(Animal _cat : testShelter.getCats()){
                if(_cat.getName().equals(cat.getName())){
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterAddDog(){
        System.out.println("Testing add dog functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,
                "asd","+90","pass");
        Animal dog = new Animal("dog1","catKind",4,false,testShelter);
        testShelter.addDog(dog);
        if(testShelter.getDogSize()==1){
            for(Animal _dog : testShelter.getDogs()){
                if(_dog.getName().equals(dog.getName())){
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterDeleteAnimal(){
        System.out.println("Testing delete animal functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,
                "asd","+90","pass");
        Animal dog = new Animal("dog1","catKind",4,false,testShelter);
        testShelter.addDog(dog);
        testShelter.removeDog(dog);
        if(testShelter.getDogSize()==0){
            System.out.println("Passed!");
            return;
        }
        System.out.println("Failed!");
        System.exit(-1);

    }
    public static void shelterAddDiseasedAnimal(){
        System.out.println("Testing add diseased animal functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,
                "asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        for(Animal _cat : testShelter.getCats()){
            testShelter.addDiseasedAnimal(_cat.getId(),1);
        }
        if(testShelter.getDiseasedAnimals().size()==1) {
            if (testShelter.getDiseasedAnimals().poll().getAnimal().getName().equals(cat.getName())) {
                if(testShelter.getDiseasedAnimals().size()==0) {
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterAddMultipleDiseasedAnimals(){
        System.out.println("Testing add diseased animal's priority functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,
                "asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        Animal cat2 = new Animal("cat2","catKind",4,true,testShelter);
        testShelter.addCat(cat2);
        int diseaseLevel = 1;
        for(Animal _cat : testShelter.getCats()){
            testShelter.addDiseasedAnimal(_cat.getId(),diseaseLevel);
            diseaseLevel+=5;
        }
        if(testShelter.getDiseasedAnimals().size()==2) {
            Disease disease = testShelter.getDiseasedAnimals().poll();
            if (disease.getAnimal().getName().equals(cat2.getName()) && disease.getPriority()==6) {
                System.out.println("Passed!");
                return;
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterApproveAdoptionRequest(){
        System.out.println("Testing approve adoption request functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        Animal cat2 = new Animal("cat2","catKind",4,true,testShelter);
        testShelter.addCat(cat2);
        User user = new User("name1","usnm","pass1",city,town,new Date());
        system.addUser(user);
        user.createARequest(cat);
        if(testShelter.getAdoptionRequests().size()==1){
            if(user.getRequests().size()==1){
                if(testShelter.approveAdoptionRequest(0)){
                    if(testShelter.getAdoptionRequests().size()==0 && user.getRequests().size()==0){
                        System.out.println("Passed!");
                        return;
                    }
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterAddTask(){
        System.out.println("Testing create task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        if(testShelter.getTasks().size()==1){
            if(testShelter.getTasks().get(0).getTask() == task.getTask()){
                Task task2 = new Task("task2",false);
                testShelter.addTask(task2);
                if(testShelter.getTasks().size()==2){
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterApproveTask(){
        System.out.println("Testing approve task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        testShelter.getTasks().get(0).setStatus(true);
        if(testShelter.getTasks().get(0).getStatus()){
            System.out.println("Passed!");
            return;
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterUpdateTask(){
        System.out.println("Testing update task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        testShelter.getTasks().get(0).setTask("newtasktext");
        if(testShelter.getTasks().get(0).getTask().equals("newtasktext")){
            System.out.println("Passed!");
            return;
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterDeleteTask(){
        System.out.println("Testing delete task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        Task task2 = new Task("task2",false);
        testShelter.addTask(task2);
        testShelter.getTasks().remove(0);
        if(testShelter.getTasks().size()==1){
            if(testShelter.getTasks().get(0).getTask().equals(task2.getTask())) {
                System.out.println("Passed!");
                return;
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }

    public static void systemTest(){
        ShelterSystem system = new ShelterSystem();

        readCityInfo(system);
        insertTownsTests(system);
        BinarySearchTree<City> cities 	= system.getCitiesBST();
        City istanbul = cities.find(new City("Istanbul", 34));
        Town tuzla = istanbul.getTown("Tuzla");
        Shelter shelter=new Shelter("shelter1", istanbul, tuzla, 10, 5, "tuzla yayla", "0234", "password");
        ShelterRequest shelterRequest=new ShelterRequest(istanbul,"Tuzla",shelter);

        system.addNewShelterRequest(shelterRequest);
        system.addShelter(shelterRequest);
        System.out.println(tuzla.getShelters().toString());

        CapacityChangeRequest capReq=new CapacityChangeRequest("istanbul","Tuzla",shelter,12,12);
        System.out.println("Dog capacity before request : "+shelter.getDogCapacity()+
                "\n"+"Cat capacity before request : "+shelter.getCatCapacity());
        system.addCapChangeRequest(capReq);
        system.changeShelterCap(capReq);
        System.out.println("Dog capacity after request : "+shelter.getDogCapacity()+
                "\n"+"Cat capacity after request : "+shelter.getCatCapacity());

        //System.out.println(system.getCitiesBST().find(istanbul).getTown("Tuzla").getShelters());

        Shelter shelter2=new Shelter("shelter2", istanbul, tuzla, 12, 12, "tuzla yayla", "0234", "password");
        ShelterRequest shelterRequest2=new ShelterRequest(istanbul,"Tuzla",shelter2);
        system.addRemoveShelterRequest(shelterRequest2);
        system.removeShelter(shelterRequest2);

        System.out.println(system.getCitiesBST().find(istanbul).getTown("Tuzla").getShelter("shelter2"));

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

    private static void testAddAnimal(){
        ShelterSystem system = new ShelterSystem();
        City istanbul = new City("Istanbul", 34);
        Town pendik = new Town("Pendik", istanbul);

        Shelter myShelter = new Shelter("Pendik Barinagi",istanbul,pendik,
                10,10,"yeni mah","3759630","123456");

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


    private static String getRandomDogKind(Random random){
        File file = new File("src/main/Constants/dogKinds.txt");
        try {
            Scanner sc = new Scanner(file);
            int destLine = random.nextInt(500);
            for(int i = 0; i < destLine; ++i, sc.nextLine());
            String kind = sc.nextLine();
            sc.close();
            return kind;
        }
        catch ( Exception e ) {
            return "DogKind_"+random.nextInt(50);
        }
    }

    private static String getRandomCatKind(Random random){
        File file = new File("src/main/Constants/cats.txt");
        try {
            Scanner sc = new Scanner(file);
            int destLine = random.nextInt(50);
            for(int i = 0; i < destLine; ++i, sc.nextLine());
            String kind = sc.nextLine();
            sc.close();
            return kind;
        }
        catch ( Exception e ) {
            return "CatKind_"+random.nextInt(50);
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

    protected static void printCitiesInfo(ShelterSystem system){
        BinarySearchTree<City> cities 	= system.getCitiesBST();
        System.out.println(cities.toString());
    }

    protected static void shelterTests(ShelterSystem system){
        File file = new File("src/main/Constants/Shelters.txt");
        Random randNum = new Random();
        try {

            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                String str = sc.nextLine();
                int citySize;
                Town town;

                Shelter shelter = getShelterFromStrByPrefix(system, str, ",");
                citySize = shelter.getCity().towns.size();
                int num = randNum.nextInt(citySize);
                town = shelter.getCity().getTownByNumber(num);

                ShelterRequest shelterRequest = new ShelterRequest(shelter.getCity(), town.getName(), shelter);
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

    public static void userTest() {
        ShelterSystem system = new ShelterSystem();

        readCityInfo(system);
        printCitiesInfo(system);
        systemTest();
        insertTownsTests(system);
        shelterTests(system);


		BinarySearchTree<City> cities 	= system.getCitiesBST();
		City istanbul = cities.find(new City("Istanbul", 34));
		Town kagithane = istanbul.getTown("Kagithane");
		Shelter dogaevi = kagithane.addShelter("dogaevi",
                23,11,"Sultan selim mah. No 3","+902122222415","stockpass");
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
		Thread javalinThread = new Thread(javalinServer);
		javalinThread.start();
		AdminUI ui = new AdminUI(system);
		ui.run();
		javalinThread.stop();
    }



}
