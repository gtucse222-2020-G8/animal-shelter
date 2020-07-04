package cse222.group8.server;


import  cse222.group8.server.DataStructures.BinarySearchTree;

/**
 * The type City.
 */
public class City implements Comparable<City>{

	private int cityId;
    private String name;
    private ShelterSystem shelterSystem;
    /**
     * The Towns.
     */
    BinarySearchTree<Town> towns;

    /**
     * Instantiates a new City.
     *
     * @param cityName the city name
     * @param cityId   the city ıd
     * @param system   the system
     */
    public City(String cityName, int cityId, ShelterSystem system){
        this.name = cityName;
        this.cityId = cityId;
        shelterSystem = system;
        towns = new BinarySearchTree<Town>();
    }

    /**
     * Gets city ıd.
     *
     * @return the city ıd
     */
    public int getCityId() {
		return cityId;
	}

    /**
     * Sets city ıd.
     *
     * @param cityId the city ıd
     */
    public void setCityId(int cityId) {
		this.cityId = cityId;
	}

    /**
     * Gets towns.
     *
     * @return the towns
     */
    public BinarySearchTree<Town> getTowns() {
        return towns;
    }

    /**
     * Get town town.
     *
     * @param townName the town name
     * @return the town
     */
    public Town getTown(String townName){
    	Town town = towns.find(new Town(townName,this,shelterSystem));
    	if(town == null)
    		return null;
    	return town;
    }

    @Override
    public int compareTo(City o) {
        if(cityId == o.cityId )
            return 0;
        else if(cityId > o.cityId) return 1;
        else
            return -1;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", towns=" + towns +
                '}';
    }
}
