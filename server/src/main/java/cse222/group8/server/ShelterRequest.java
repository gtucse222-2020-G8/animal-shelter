package cse222.group8.server;

/**
 * The type Shelter request.
 */
public class ShelterRequest {


    City city;
    /**
     * The Town.
     */
    String town;
    /**
     * The Shelter.
     */
    Shelter shelter;

    /**
     * Instantiates a new Shelter request.
     *
     * @param city    the city
     * @param town    the town
     * @param shelter the shelter
     */
    public ShelterRequest(City city, String town, Shelter shelter) {
        this.city = city;
        this.town = town;
        this.shelter = shelter;
    }

    
}
