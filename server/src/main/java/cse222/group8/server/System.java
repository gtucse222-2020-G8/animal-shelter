package cse222.group8.server;

import java.util.List;
import java.util.Queue;
import cse222.group8.server.DataStructures.*;

public class System {

    public class Admin{

        void addShelter() {}

        void removeShelter() {}

        void changeShelterCap() {}

    }
    private BinarySearchTree<City> cities;
    private BinarySearchTree<User> users;
    private Queue<CapacityChangeRequest> capacityChangeRequests;
    private Queue<ShelterRequest> newShelterRequests;
    private Queue<ShelterRequest> removeShelterRequests;
    
    
    
    /* SERVICE METHODS */
    
    public void addShelter(ShelterRequest requestedShelter) {
    	//TODO
    }
    
    public void changeShelterCap(CapacityChangeRequest request) {
    	//TODO
    }
    
    public void updateShelter(Shelter shelter) {
    	//TODO
    }
    
    public void removeShelter(Shelter shelter) {
    	//TODO
    }
    
    public Shelter getShelter(String cityName, String townName, String shelterName) {
    	//TODO
    	return null;
    }
    
    public void addUser(User user) {
    	//TODO
    }
    
    public void updateUser(User user) {
    	// TODO
    }
    
    public void removeUser(User user) {
    	//TODO
    }
    
    public void addAnimal(Animal animal) {
    	//TODO
    }
    
    public void updateAnimal(Animal animal) {
    	// TODO
    }
    
    public void removeAnimal(Animal animal) {
    	//TODO
    }
    
    public void addTask(Task task) {
    	//TODO
    }
    
    public void updateTask(Task task) {
    	// TODO
    }
    
    public void removeTask(Task task) {
    	//TODO
    }
    
    public void addDiseasedAnimal(Disease diseasedAnimal) {
    	//TODO
    }
    
    public void popDiseasedAnimal(Shelter shelter) {
    	//TODO
    }
    
    public void makeAdoptionRequest(Animal animal, User user) {
    	//TODO
    }
    
    public void removeAdoptionRequest(AdoptionRequest request) {
    	//TODO
    }

}