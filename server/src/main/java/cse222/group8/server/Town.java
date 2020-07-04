package cse222.group8.server;


import cse222.group8.server.DataStructures.MergeSort;
import cse222.group8.server.DataStructures.SkipList;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Town.
 */
public class Town implements Comparable<Town> {

    private String name;
    private City city;
    private ArrayList<Shelter> shelters;

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
        boolean found = false;
        int current_index = shelters.size() / 2;
        int gap = current_index;
        while(!found){
            int cmp = shelters.get(current_index).getName().compareTo(name);
            if(cmp < 0){
                gap /= 2;
                current_index = current_index-gap;
            }
            else if(cmp > 0){
                gap /= 2;
                current_index = current_index+gap;
            }
            else{
                return shelters.get(current_index);
            }
            if(gap == 0){
                found = true;
            }
        }
        return null;
    }

    /**
     * Adds new shelter.
     *
     * @param name the name
     * @param catCapacity the cat capacity
     * @param dogCapacity the dog capacity
     * @param address the address
     * @param phoneNumber the phone number
     * @param password the password
     * @return the shelter
     */
    public Shelter addShelter(String name, int catCapacity, int dogCapacity, String address, String phoneNumber, String password){
        for(Shelter shelter: shelters){
            if(shelter.getName().equals(name)){
                return null;
            }
        }
        Shelter shelter = new Shelter(name,city,this,catCapacity,dogCapacity,address,phoneNumber,password);
        shelters.add(shelter);
        MergeSort<Shelter> sorter = new MergeSort<Shelter>(shelters);
        sorter.sortGivenArray();
        this.shelters = sorter.getSortedArray();
        return shelter;
    }

    /**
     * Adds new shelter.
     *
     * @param shelter the shelter that will be added
     * @return the shelter
     */
    public Shelter addShelter(Shelter shelter){
        return addShelter(shelter.getName(),shelter.getCatCapacity(),shelter.getDogCapacity(),shelter.getAddress(),shelter.getPhoneNumber(),shelter.getPassword());
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
