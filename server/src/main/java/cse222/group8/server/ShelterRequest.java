package cse222.group8.server;

public class ShelterRequest {
    String city;
    String town;
    Shelter shelter;
    
    public ShelterRequest(String city, String town, Shelter shelter) {
        this.city = city;
        this.town = town;
        this.shelter = shelter;
    }
    
}
