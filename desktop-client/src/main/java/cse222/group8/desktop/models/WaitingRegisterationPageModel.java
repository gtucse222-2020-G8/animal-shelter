package cse222.group8.desktop.models;

import cse222.group8.desktop.client.models.Token;

/**
 * The type Waiting registeration page model.
 */
public class WaitingRegisterationPageModel {
    private String shelterName;
    private String city;
    private String town;
    private Token token;

    /**
     * Gets token.
     *
     * @return the token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(Token token) {
        this.token = token;
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
     * Gets shelter name.
     *
     * @return the shelter name
     */
    public String getShelterName() {
        return shelterName;
    }

    /**
     * Sets shelter name.
     *
     * @param password the password
     */
    public void setShelterName(String password) {
        this.shelterName = password;
    }
}
