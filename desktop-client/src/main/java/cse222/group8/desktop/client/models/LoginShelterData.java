package cse222.group8.desktop.client.models;

public class LoginShelterData extends ShelterAddressData{

    public String password;

    public LoginShelterData(String city, String town, String shelterName, String password) {
        super(city, town, shelterName);
        this.password = password;
    }
}
