package cse222.group8.desktop.models;

import cse222.group8.desktop.client.models.Token;

public class WaitingRegisterationPageModel {
    private String shelterName;
    private String city;
    private String town;
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
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

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String password) {
        this.shelterName = password;
    }
}
