package cse222.group8.server;


import cse222.group8.server.DataStructures.SkipList;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Town.
 */
public class Town implements Comparable<Town> {

    private String name;
    private City city;
    private List<Shelter> shelters;

    /**
     * Instantiates a new Town.
     *
     * @param name          the name
     * @param city          the city
     */
    public Town(String name, City city) {
        this.name = name;
        this.city = city;
        shelters = new ArrayList<Shelter>();
    }

    /**
     * Get shelter shelter.
     *
     * @param name the name
     * @return the shelter
     */
    public Shelter getShelter(String name){
        for(int i=0; i<getShelters().size(); ++i) {
            if (getShelters().get(i).getName().equals(name) )
                return getShelters().get(i);
        }

        return null;
    }

    /**
     * Gets shelters.
     *
     * @return the shelters
     */
    public List<Shelter> getShelters() {
            return shelters;
    }

    @Override
    public int compareTo(Town o) {
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
        return "Town{" +
                "name='" + name + '\'' +
                ", city=" + city.getName() +
                ", shelters=" + shelters +
                '}';
    }
}
