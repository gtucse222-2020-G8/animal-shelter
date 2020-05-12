package cse222.group8.server;


import  cse222.group8.server.DataStructures.BinarySearchTree;
import java.util.List;

public class City implements Comparable<City>{

    private String name;
    BinarySearchTree<Town> towns;

    public BinarySearchTree<Town> getTowns() {
        return towns;
    }

    public Town getTown(String townName){

    }

    public City(String cityName){

    }

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }
}
