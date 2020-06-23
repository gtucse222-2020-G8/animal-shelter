package cse222.group8.server;

public class CapacityChangeRequest {
    String city;
    String town;
    Shelter shelter;
    int dogCapacity;
    int catCapacity;
    
    public CapacityChangeRequest(String city, String town, Shelter shelter, int dogCap, int catCap) {
        this.city = city;
        this.town = town;
        this.shelter = shelter;
        this.dogCapacity = dogCap;
        this.catCapacity = catCap;
	}
    
}
