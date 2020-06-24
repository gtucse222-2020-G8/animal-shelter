package cse222.group8.desktop.client.models;

/**
 * The type Login shelter data.
 */
public class LoginShelterData extends ShelterAddressData{

    /**
     * The Password.
     */
    public String password;

    /**
     * Instantiates a new Login shelter data.
     *
     * @param city        the city
     * @param town        the town
     * @param shelterName the shelter name
     * @param password    the password
     */
    public LoginShelterData(String city, String town, String shelterName, String password) {
        super(city, town, shelterName);
        this.password = password;
    }
}
