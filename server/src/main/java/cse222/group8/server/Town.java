package cse222.group8.server;


import java.util.List;


public class Town implements Comparable<Town> {

    private String name;

    private List<Shelter> shelters;

    public Town(String name) {
        this.name = name;
    }

    public Shelter getShelter(String name){
        for(int i=0; i<getShelters().size(); ++i) {
            if (getShelters().get(i).getName().equals(name) )
                return getShelters().get(i);
        }

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
