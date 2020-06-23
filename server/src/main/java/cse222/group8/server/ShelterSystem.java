package cse222.group8.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import cse222.group8.server.DataStructures.*;

public class ShelterSystem {

    public class Admin{

        void addShelter() {}

        void removeShelter() {}

        void changeShelterCap() {}

    }
    
    //location fields.
    /**  Binary search tree to keep cities.  */
    private BinarySearchTree<City> cities;
    
    /**  A map to keep city ids of Turkey.  */
    private TreeMap<Integer, City> cityIds;
    
    /**  A graph to keep borders of cities  */
    private ListGraph borderCities;
    
    
    
    // user fields.
    /**  Binary search tree to keep users.  */
    private BinarySearchTree<User> users;
    
    
    // request fields.
    /**  Queue to keep CapacityChangeRequests.  */
    private Queue<CapacityChangeRequest> capacityChangeRequests;
    
    /**  Queue to keep ShelterRequest to add.  */
    private Queue<ShelterRequest> newShelterRequests;
    
    /**  Queue to keep ShelterRequests to remove.  */
    private Queue<ShelterRequest> removeShelterRequests;
    
    
    
    /* SERVICE METHODS */
    
    /**
     * Returns list of cities which are has a border
     * to specified city.
     * 
     * @param cityName Name of city
     * @return List of cities
     */
    public List<City> getBorderCities(String cityName){
    	City city = cities.find(new City(cityName, 0));	
    	if( city == null) {
    		return null;
    	}
    	
    	List<City> borderCities = new ArrayList<City>();
    	Iterator<Edge> iter = this.borderCities.edgeIterator(city.getCityId());
    	
    	while(iter.hasNext()) {
    		City borderCity = cityIds.get(iter.next().getDest());
    		borderCities.add(borderCity);
    	}
    	
    	return borderCities;
    }
    
    /**
     * Returns next CapacityChangeRequest
     * 
     * @return CapacityChangeRequest
     */
    public CapacityChangeRequest getNextCapChangeRequest() {
    	return capacityChangeRequests.poll();
    }
    
    
    /**
     * Returns next NewShelterRequest
     * 
     * @return NewShelterRequest
     */
    public ShelterRequest getNextNewShelterRequest() {
    	return newShelterRequests.poll();
    }
    
    
    
    /**
     * Returns next RemoveShelterRequest
     * 
     * @return RemoveShelterRequest
     */
    public ShelterRequest getNextRemoveShelterRequest() {
    	return removeShelterRequests.poll();
    }

    
    /**
     * Adds a shelter to to specified location
     * 
     * @param requestedShelter Shelter info
     * @return true if succeed
     */
    public boolean addShelter(ShelterRequest requestedShelter) {
    	
    	City city = cities.find(new City(requestedShelter.city, 0));
    	if(city == null) {
    		return false;
    	}
    	
    	Town town = city.getTown(requestedShelter.town);
    	if(town == null) {
    		return false;
    	}
    	
    	return town.getShelters().add(requestedShelter.shelter);
    }
    
    
    /**
     * Changes capacity of specified shelter
     * 
     * @param request Change info
     */
    public void changeShelterCap(CapacityChangeRequest request) {
    	Shelter shelter = request.shelter;
    	shelter.setDogCapacity(request.dogCapacity);
    	shelter.setCatCapacity(request.catCapacity);
    }
    
    
    
    
    /**
     * Removes a shelter from system
     * 
     * @param shelter Info of shelter to remove
     * @return true if succeed
     */
    public boolean removeShelter(ShelterRequest shelter) {
    	City city = cities.find(new City(shelter.city, 0));
    	if(city == null) {
    		return false;
    	}
    	
    	Town town = city.getTown(shelter.town);
    	if(town == null) {
    		return false;
    	}
    	
    	return town.getShelters().remove(shelter.shelter);
    }
    
    
    
    /**
     * Returns a shelter with specifed infos
     * 
     * @param cityName Name of city
     * @param townName Name of town
     * @param shelterName Name of shelter
     * @return Shelter reference if succeed, else null.
     */
    public Shelter getShelter(String cityName, String townName, String shelterName) {
    	City city = cities.find(new City(cityName, 0));
    	if(city == null) {
    		return null;
    	}
    	
    	Town town = city.getTown(townName);
    	if(town == null) {
    		return null;
    	}
    	
    	return town.getShelter(shelterName);
    }
    
    
    
    /**
     * Adds a user to system
     * 
     * @param user User to add
     * @return True if succeed
     */
    public boolean addUser(User user) {
    	return users.add(user);
    }
    
    
    
    /**
     * Updates a user of system
     * 
     * @param user User to update
     * @return true if succeed
     */
    public boolean updateUser(User user) {
    	User updatedUser = users.find(user);
    	
    	if(updatedUser != null) {
    		return updatedUser.updateUser(user);
    	}
    	else {
    		return false;
    	}
    	
    }
    
    
    
    /**
     * Removes user from system
     * 
     * @param user User to remove
     * @return True if succeed
     */
    public boolean removeUser(User user) {
    	return users.delete(user) != null;
    }
    
    
    
    
    /**
     * Adds cat to the system
     * 
     * @param animal Cat to add
     */
    public void addCat(Animal animal) {
    	Shelter shelter = animal.getShelter();
    	shelter.getDogs().add(animal);
    }
    
    
    
    /**
     * Adds dog to the system
     * 
     * @param animal Dog to add
     */
    public void addDog(Animal animal) {
    	Shelter shelter = animal.getShelter();
    	shelter.getDogs().add(animal);
    }
    
    
    /**
     * Updates a animal.
     * Removes that animal and add updated version
     * 
     * @param animal Animal to update
     * @return True if succeed
     */
    public boolean updateAnimal(Animal animal) {
    	Shelter shelter = animal.getShelter();
    	
    	if( shelter.getCats().delete(animal) != null) {
    		return shelter.getCats().add(animal);
    	}
    	else if( shelter.getDogs().delete(animal) != null ) {
    		 return shelter.getDogs().add(animal);
    	}
    	
    	return false;
    	
    }
    
    
    
    
    /**
     * Removes a animal from system
     * It can be cat or dog
     * 
     * @param animal Animal to remove
     * @return True if succeed
     */
    public boolean removeAnimal(Animal animal) {
    	Shelter shelter = animal.getShelter();
    	
    	return shelter.getCats().remove(animal) 
    			|| shelter.getDogs().remove(animal);
    }
    
    
    
    
    /**
     * Adds a task to specified shelter
     * 
     * @param task Task to add
     * @param shelter Shelter
     */
    public void addTask(Task task, Shelter shelter) {
    	shelter.addTask(task);
    }
    
    
    
    /**
     * Updates a task as specified status
     * 
     * @param task Task to update
     * @param status New status
     */
    public void updateTask(Task task, boolean status) {
    	task.setStatus(status);
    }
    
    
    
    /**
     * Removes a task from specified shelter
     * 
     * @param task Task to remove
     * @param shelter Shelter
     */
    public void removeTask(Task task, Shelter shelter) {
    	shelter.getTasks().remove(task);
    }
    
    /**
     * Add specified animal to queue
     * 
     * @param diseasedAnimal Animal info
     */
    public void addDiseasedAnimal(Disease diseasedAnimal) {
    	Animal animal = diseasedAnimal.getAnimal();
    	
    	animal.getShelter().addDiseasedAnimal(animal.getId(), animal.getDiseased());
    }
    
    /**
     * Returns next animal which has diseased
     * 
     * @param shelter Shelter which has that animal
     */
    public void pollDiseasedAnimal(Shelter shelter) {
    	shelter.getDiseasedAnimals().poll();
    }
    
    
    
    
    /**
     * Makes a adoption request
     * Adds to queue
     * Calls users method
     * 
     * @param animal Requested animal
     * @param user Requester
     * @return True if succeed
     */
    public boolean makeAdoptionRequest(Animal animal, User user) {
    	return user.createARequest(animal);
    }
    
    
    
    /**
     * Removes adoption request from system
     * Removes from both user and animal class
     * 
     * @param request Request to be removed
     * @return True if succeed
     */
    public boolean removeAdoptionRequest(AdoptionRequest request) {
    	return request.getRequester().getRequests().remove(request)
    			&& request.getRequestedAnimal().getRequestQueue().remove(request);
    }

}