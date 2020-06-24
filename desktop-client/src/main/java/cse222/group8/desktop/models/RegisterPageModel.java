package cse222.group8.desktop.models;

/**
 * The type Register page model.
 */
public class RegisterPageModel {
    private String password;
    private String city;
    private String town;
    private int catCapacity;
    private int dogCapacity;
    private String phoneNumber;
    private String address;

    /**
     * Gets cat capacity.
     *
     * @return the cat capacity
     */
    public int getCatCapacity() {
        return catCapacity;
    }

    /**
     * Sets cat capacity.
     *
     * @param catCapacity the cat capacity
     */
    public void setCatCapacity(int catCapacity) {
        this.catCapacity = catCapacity;
    }

    /**
     * Gets dog capacity.
     *
     * @return the dog capacity
     */
    public int getDogCapacity() {
        return dogCapacity;
    }

    /**
     * Sets dog capacity.
     *
     * @param dogCapacity the dog capacity
     */
    public void setDogCapacity(int dogCapacity) {
        this.dogCapacity = dogCapacity;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets town.
     *
     * @param town the town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Gets town.
     *
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
