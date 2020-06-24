package cse222.group8.server.clientModels;

/**
 * The type Register shelter data.
 */
public class RegisterShelterData extends ShelterAddressData{
    /**
     * The Phone number.
     */
    public String phoneNumber;
    /**
     * The Password.
     */
    public String password;
    /**
     * The Address.
     */
    public String address;
    /**
     * The Cat capacity.
     */
    public int catCapacity;
    /**
     * The Dog capacity.
     */
    public int dogCapacity;

    /**
     * Instantiates a new Register shelter data.
     *
     * @param city        the city
     * @param town        the town
     * @param shelterName the shelter name
     * @param phoneNumber the phone number
     * @param password    the password
     * @param address     the address
     * @param catCapacity the cat capacity
     * @param dogCapacity the dog capacity
     */
    public RegisterShelterData(String city, String town, String shelterName, String phoneNumber, String password, String address, int catCapacity, int dogCapacity){
        super(city,town,shelterName);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.catCapacity = catCapacity;
        this.dogCapacity = dogCapacity;
    }
}
