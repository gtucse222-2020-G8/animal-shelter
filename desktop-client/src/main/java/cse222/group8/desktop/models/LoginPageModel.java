package cse222.group8.desktop.models;

/**
 * The type Login page model.
 */
public class LoginPageModel{
    private String password;
    private String city;
    private String town;

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
