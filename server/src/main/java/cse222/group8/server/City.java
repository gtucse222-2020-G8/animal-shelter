package cse222.group8.server;


import  cse222.group8.server.DataStructures.BinarySearchTree;

import java.util.Iterator;

/**
 * The type City.
 */
public class City implements Comparable<City>{

	private int cityId;
    private String name;


    /**
     * The Towns.
     */
    BinarySearchTree<Town> towns;

    /**
     * Instantiates a new City.
     *
     * @param cityName the city name
     * @param cityId   the city ıd
     */
    public City(String cityName, int cityId){
        this.name = cityName;
        this.cityId = cityId;
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
        return towns.find(new Town(townName, this));
    }


    public Town getTownByNumber(int number){
        if(number < 0 || number > towns.size()){
            throw new NullPointerException();
        }
        Iterator<Town> iterator =  towns.iterator();
        Town town;
        int count = 0;
        while(iterator.hasNext()){
            town = iterator.next();
            if(count == number) return town;
            count++;
        }
        return null;
    }

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.getName());
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
