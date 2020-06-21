package cse222.group8.desktop.models;

public class RegisterPageModel {
    private String password;
    private String city;
    private String town;
    private int catCapacity;
    private int dogCapacity;
    private String phoneNumber;
    private String address;

    public int getCatCapacity() {
        return catCapacity;
    }

    public void setCatCapacity(int catCapacity) {
        this.catCapacity = catCapacity;
    }

    public int getDogCapacity() {
        return dogCapacity;
    }

    public void setDogCapacity(int dogCapacity) {
        this.dogCapacity = dogCapacity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown() {
        return town;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
