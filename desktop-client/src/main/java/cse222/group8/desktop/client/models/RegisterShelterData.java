package cse222.group8.desktop.client.models;

public class RegisterShelterData extends ShelterAddressData{
    public String phoneNumber;
    public String password;
    public String address;
    public int catCapacity;
    public int dogCapacity;
    public RegisterShelterData(String city, String town, String shelterName, String phoneNumber, String password, String address, int catCapacity, int dogCapacity){
        super(city,town,shelterName);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.catCapacity = catCapacity;
        this.dogCapacity = dogCapacity;
    }
}
