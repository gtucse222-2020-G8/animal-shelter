package cse222.group8.server;


import  cse222.group8.server.DataStructures.BinarySearchTree;

public class City implements Comparable<City>{

	private int cityId;
    private String name;
    BinarySearchTree<Town> towns;
    
    public City(String cityName, int cityId){
        this.name = cityName;
        this.cityId = cityId;
    }

    public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public BinarySearchTree<Town> getTowns() {
        return towns;
    }

    public Town getTown(String townName){
    	Town town = towns.find(new Town(townName));
    	if(town == null)
    		return null;
    	return town;
    }

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }
}
