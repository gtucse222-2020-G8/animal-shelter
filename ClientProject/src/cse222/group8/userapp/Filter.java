package cse222.group8.userapp;

/**
 * The type Filter.
 */
public class Filter {

    private String city;
    private String town;

    /**
     * Instantiates a new Filter.
     *
     * @param city the city
     * @param town the town
     */
    public Filter(String city, String town){
        this.city = city;
        this.town = town;
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
     * Gets town.
     *
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets town.
     *
     * @param town the town
     */
    public void setTown(String town) {
        this.town = town;
    }
}
