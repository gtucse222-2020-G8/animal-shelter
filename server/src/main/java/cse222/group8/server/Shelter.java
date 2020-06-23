package cse222.group8.server;

import cse222.group8.server.DataStructures.AVLTree;
import cse222.group8.server.DataStructures.BinarySearchTree;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
    private List<Animal> adopteds;
    private List<AdoptionRequest> adoptionRequests;
    private List<Task> tasks;
    private PriorityQueue<Disease> diseasedAnimals;

    public Shelter(String name, City city, Town town, ShelterSystem system){
        this.name = name;
        this.city = city;
        this.town = town;
        shelterSystem = system;
        cats = new AVLTree<Animal>();
        dogs = new AVLTree<Animal>();
        adopteds = new LinkedList<Animal>();
        adoptionRequests = new LinkedList<AdoptionRequest>();
        tasks = new LinkedList<Task>();
    }

    public void addCat(Animal cat){
        cats.add(cat);
    }

    public void addDog(Animal dog){
        dogs.add(dog);
    }
    public CapacityChangeRequest makeCapChangeRequest(int catCap, int dogCap) {
        CapacityChangeRequest newChangeRequest = new CapacityChangeRequest(city.getName(),town.getName(),this,dogCap,catCap);
        shelterSystem.addCapacityChangeRequest(newChangeRequest);
    	return newChangeRequest;
    }

    public void addDiseasedAnimal(int animalId, int diseased) {
    	Animal dog = getDog(animalId);
    	if(dog != null)
    		diseasedAnimals.add(new Disease(dog, diseased));
    	else {
    		Animal cat = getCat(animalId);
    		if(cat != null) {
    			diseasedAnimals.add(new Disease(cat, diseased));
    		}
    	}
    }
    
    public void addTask(Task task) {
        if(!(getTasks().contains(task)))
            tasks.add(task);
    }

    public Animal getDog(int animalId){
    	Animal animal = dogs.find(new Animal(animalId));
    	return animal;
    }
    
    public Animal getCat(int animalId){
    	Animal animal = cats.find(new Animal(animalId));
    	return animal;
    }

    public int getTotalAnimal() {
        //return dogs.size() + cats.size();
    	//tree does not have size method
    	//TODO
    	return -1;
    }
    
    @Override
    public int compareTo(Shelter o) {
    	//TODO
        return -1;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresss() {
        return address;
    }

    public void setAddresss(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Animal> getAdopteds() {
        return adopteds;
    }


    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequests;
    }


    public List<Task> getTasks() {
        return tasks;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCatCapacity() {
		return catCapacity;
	}

	public void setCatCapacity(int catCapacity) {
		this.catCapacity = catCapacity;
	}

	public int getDogCapacity() {
		return dogCapacity;
	}

	public void setDogCapacity(int dogCapacity) {
		this.dogCapacity = dogCapacity;
	}

	public BinarySearchTree<Animal> getDogs() {
		return dogs;
	}

	public BinarySearchTree<Animal> getCats() {
		return cats;
	}

	public PriorityQueue<Disease> getDiseasedAnimals() {
		return diseasedAnimals;
	}

	public void setDiseasedAnimals(PriorityQueue<Disease> diseasedAnimals) {
		this.diseasedAnimals = diseasedAnimals;
	}
}
