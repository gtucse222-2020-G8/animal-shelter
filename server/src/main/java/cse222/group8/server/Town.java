package cse222.group8.server;


import java.util.List;


public class Town implements Comparable<Town> {

    private String name;

    private List<Shelter> shelters;

    public Shelter getShelter(String name){
    	//TODO
    	return null;
    }

    public List<Shelter> getShelters() {
            return shelters;
    }

    @Override
    public int compareTo(Town o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }
}
