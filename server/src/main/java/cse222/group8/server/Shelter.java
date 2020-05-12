package cse222.group8.server;

import cse222.group8.server.DataStructures.BinarySearchTree;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.PriorityQueue;

public class Shelter implements Comparable<Shelter> {

    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private int catCapacity;
    private int dogCapacity;
    private BinarySearchTree<Animal> dogs;
    private BinarySearchTree<Animal> cats;
    private List<Animal> adopteds;
    private List<AdoptionRequest> adoptionRequests;
    private List<Task> tasks;
    private PriorityQueue<Disease> diseasedAnimals;
    
    // change cap request method needed

    public Animal getDog(int animalId){

    }
    public Animal getCat(int animalId){

    }

    public int getTotalAnimal() {
        return dogs.size() + cats.size();
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Animal> getAdopteds() {
        return adopteds;
    }

    public void setAdopteds(List<Animal> adopteds) {
        this.adopteds = adopteds;
    }

    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequests;
    }

    public void setAdoptionRequests(List<AdoptionRequest> adoptionRequests) {
        this.adoptionRequests = adoptionRequests;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


    @Override
    public int compareTo(Shelter o) {
        return 0;
    }
}
