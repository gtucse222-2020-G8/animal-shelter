package cse222.group8.server;

import cse222.group8.server.DataStructures.AVLTree;
import cse222.group8.server.DataStructures.BinarySearchTree;
import cse222.group8.server.DataStructures.SkipList;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The type Shelter.
 */
public class Shelter implements Comparable<Shelter> {

    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private City city;
    private Town town;
    private ShelterSystem shelterSystem;
    private int catCapacity;
    private int dogCapacity;
    private AVLTree<Animal> dogs;
    private AVLTree<Animal> cats;
    private SkipList<Animal> adopteds;
    private List<AdoptionRequest> adoptionRequests;
    private List<Task> tasks;
    private PriorityQueue<Disease> diseasedAnimals;
    private int catSize;
    private int dogSize;
    private boolean registered;


    /**
     * Instantiates a new Shelter.
     */
    public Shelter(){
    	this.catCapacity = 10;
    	this.dogCapacity = 10;
    	this.catSize = 0;
    	this.dogSize = 0;
    	cats = new AVLTree<Animal>();
        dogs = new AVLTree<Animal>();
        adopteds = new SkipList<Animal>();
        adoptionRequests = new LinkedList<AdoptionRequest>();
        tasks = new LinkedList<Task>();
        registered = false;

    }

    /**
     * Instantiates a new Shelter.
     *
     * @param name        the name
     * @param city        the city
     * @param town        the town
     * @param catCapacity the cat capacity
     * @param dogCapacity the dog capacity
     * @param address     the address
     * @param phoneNumber the phone number
     * @param password    the password
     * @param system      the system
     */
    public Shelter(String name, City city, Town town, int catCapacity, int dogCapacity, String address, String phoneNumber, String password, ShelterSystem system){
        this.name = name;
        this.city = city;
        this.town = town;
	    this.catSize = 0;
        this.dogSize = 0;
        this.catCapacity = catCapacity;
        this.dogCapacity = dogCapacity;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        shelterSystem = system;
        cats = new AVLTree<Animal>();
        dogs = new AVLTree<Animal>();
        adopteds = new SkipList<Animal>();
        adoptionRequests = new LinkedList<AdoptionRequest>();
        tasks = new LinkedList<Task>();
        registered = false;
    }

    /**
     * Approve adoption request.
     *
     * @param requestID the request ıd
     */
    public void approveAdoptionRequest(int requestID) {

        if(requestID>=0 && requestID<getAdoptionRequests().size()) {

            Animal reqAnimal = getAdoptionRequests().get(requestID).getRequestedAnimal(); // requested animal
            getAdoptionRequests().remove(requestID); //remove element at requestID index
            reqAnimal.setAdopted(true); // animal is adopted now
            getAdopteds().add(reqAnimal); // add this animal in adopteds list

            //Remove that animal from animal list
            if(reqAnimal.getId()%2 == 1)
                getCats().remove(reqAnimal);

            if(reqAnimal.getId()%2 == 0)
                getDogs().remove(reqAnimal);
        }
    }

    /**
     * Register.
     */
    public void register(){
        registered = true;
    }

    /**
     * Is registered boolean.
     *
     * @return the boolean
     */
    public boolean isRegistered(){
        return registered;
    }

    /**
     * Add cat boolean.
     *
     * @param cat the cat
     * @return the boolean
     */
    public boolean addCat(Animal cat){
       if(catSize < catCapacity) {
    	   cats.add(cat);
           catSize++;
           return true;
       }
       return false;
    }

    /**
     * Add dog boolean.
     *
     * @param dog the dog
     * @return the boolean
     */
    public boolean addDog(Animal dog){
       if(dogSize < dogCapacity) {
            dogs.add(dog);
            dogSize++;
            return true;
        }
        return false;
    }

    /**
     * Gets cat size.
     *
     * @return the cat size
     */
    public int getCatSize() {
    	return catSize;
    }

    /**
     * Gets dog size.
     *
     * @return the dog size
     */
    public int getDogSize() {
    	return dogSize;
    }

    /**
     * Sets dog size.
     *
     * @param size the size
     */
    public void setDogSize(int size) {
    	dogSize = size;
    }

    /**
     * Sets cat size.
     *
     * @param size the size
     */
    public void setCatSize(int size) {
    	catSize = size;
    }

    /**
     * Remove cat boolean.
     *
     * @param cat the cat
     * @return the boolean
     */
    public boolean removeCat(Animal cat) {
    	Animal animal = cats.find(cat);
    	
    	if(animal != null) {
    		cats.remove(cat);
    		catSize--;
    		return true;
    	}
    	return false;
    }

    /**
     * Remove dog boolean.
     *
     * @param dog the dog
     * @return the boolean
     */
    public boolean removeDog(Animal dog) {
    	Animal animal = dogs.find(dog);
    	
    	if(animal != null) {
    		dogs.remove(dog);
    		dogSize--;
    		return true;
    	}
    	return false;
    }

    /**
     * Make cap change request capacity change request.
     *
     * @param catCap the cat cap
     * @param dogCap the dog cap
     * @return the capacity change request
     */
    public CapacityChangeRequest makeCapChangeRequest(int catCap, int dogCap) {
        CapacityChangeRequest newChangeRequest = new CapacityChangeRequest(city.getName(),town.getName(),this,dogCap,catCap);
        shelterSystem.addCapChangeRequest(newChangeRequest);
    	return newChangeRequest;
    }

    /**
     * Add diseased animal.
     *
     * @param animalId the animal ıd
     * @param diseased the diseased
     */
    public void addDiseasedAnimal(int animalId, int diseased) {
    	Animal dog = getDog(animalId);
    	if(dog != null) {
            dog.setDiseased(diseased);
            diseasedAnimals.add(new Disease(dog, diseased));
        }
    	else {
    		Animal cat = getCat(animalId);
    		if(cat != null) {
                cat.setDiseased(diseased);
    			diseasedAnimals.add(new Disease(cat, diseased));
    		}
    	}
    }

    /**
     * Add task.
     *
     * @param task the task
     */
    public void addTask(Task task) {
        if(!(getTasks().contains(task)))
            tasks.add(task);
    }

    /**
     * Get dog animal.
     *
     * @param animalId the animal ıd
     * @return the animal
     */
    public Animal getDog(int animalId){
    	Animal animal = dogs.find(new Animal(animalId));
    	return animal;
    }

    /**
     * Get cat animal.
     *
     * @param animalId the animal ıd
     * @return the animal
     */
    public Animal getCat(int animalId){
    	Animal animal = cats.find(new Animal(animalId));
    	return animal;
    }

    /**
     * Gets total animal.
     *
     * @return the total animal
     */
    public int getTotalAnimal() {
         return getDogSize() + getCatSize();
    }
    
    @Override
    public int compareTo(Shelter o) {
    	return name.compareTo(o.name);
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets addresss.
     *
     * @return the addresss
     */
    public String getAddresss() {
        return address;
    }

    /**
     * Sets addresss.
     *
     * @param address the address
     */
    public void setAddresss(String address) {
        this.address = address;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets adopteds.
     *
     * @return the adopteds
     */
    public SkipList<Animal> getAdopteds() {
        return adopteds;
    }

    /**
     * Gets adoption requests.
     *
     * @return the adoption requests
     */
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequests;
    }


    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
		return phoneNumber;
	}

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
		return address;
	}

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
		this.address = address;
	}

    /**
     * Gets cat capacity.
     *
     * @return the cat capacity
     */
    public int getCatCapacity() {
		return catCapacity;
	}

    /**
     * Sets cat capacity.
     *
     * @param catCapacity the cat capacity
     */
    public void setCatCapacity(int catCapacity) {
		this.catCapacity = catCapacity;
	}

    /**
     * Gets dog capacity.
     *
     * @return the dog capacity
     */
    public int getDogCapacity() {
		return dogCapacity;
	}

    /**
     * Sets dog capacity.
     *
     * @param dogCapacity the dog capacity
     */
    public void setDogCapacity(int dogCapacity) {
		this.dogCapacity = dogCapacity;
	}

    /**
     * Gets dogs.
     *
     * @return the dogs
     */
    public BinarySearchTree<Animal> getDogs() {
		return dogs;
	}

    /**
     * Gets cats.
     *
     * @return the cats
     */
    public BinarySearchTree<Animal> getCats() {
		return cats;
	}

    /**
     * Gets diseased animals.
     *
     * @return the diseased animals
     */
    public PriorityQueue<Disease> getDiseasedAnimals() {
		return diseasedAnimals;
	}

    /**
     * Sets diseased animals.
     *
     * @param diseasedAnimals the diseased animals
     */
    public void setDiseasedAnimals(PriorityQueue<Disease> diseasedAnimals) {
		this.diseasedAnimals = diseasedAnimals;
	}
}
