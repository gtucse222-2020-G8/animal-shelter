package cse222.group8.server;

/**
 * The type Capacity change request.
 */
public class CapacityChangeRequest {
    /**
     * The City.
     */
    String city;
    /**
     * The Town.
     */
    String town;
    /**
     * The Shelter.
     */
    Shelter shelter;
    /**
     * The Dog capacity.
     */
    int dogCapacity;
    /**
     * The Cat capacity.
     */
    int catCapacity;

    /**
     * Instantiates a new Capacity change request.
     *
     * @param city    the city
     * @param town    the town
     * @param shelter the shelter
     * @param dogCap  the dog cap
     * @param catCap  the cat cap
     */
    public CapacityChangeRequest(String city, String town, Shelter shelter, int dogCap, int catCap) {
        this.city = city;
        this.town = town;
        this.shelter = shelter;
        this.dogCapacity = dogCap;
        this.catCapacity = catCap;
	}
    
}
