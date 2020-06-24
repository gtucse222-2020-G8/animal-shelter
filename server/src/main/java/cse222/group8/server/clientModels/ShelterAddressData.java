package cse222.group8.server.clientModels;

/**
 * The type Shelter address data.
 */
public class ShelterAddressData {
    /**
     * The City.
     */
    public String city;
    /**
     * The Town.
     */
    public String town;
    /**
     * The Shelter name.
     */
    public String shelterName;

    /**
     * Instantiates a new Shelter address data.
     *
     * @param city        the city
     * @param town        the town
     * @param shelterName the shelter name
     */
    public ShelterAddressData(String city, String town, String shelterName){
        this.city = city;
        this.town = town;
        this.shelterName = shelterName;
    }
}
