package cse222.group8.server;


import  cse222.group8.server.DataStructures.BinarySearchTree;

public class City implements Comparable<City>{

	private int cityId;
    private String name;
    BinarySearchTree<Town> towns;
    
    public City(String cityName, int cityId){
    	// TODO
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
    	//TODO
    	return null;
    }

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }
}
