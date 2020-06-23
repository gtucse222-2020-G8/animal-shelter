package cse222.group8.server;

public class Disease implements Comparable<Disease>{
    private Animal animal;
    private int priority;

    public Disease(Animal animal, int priority){
        this.animal = animal;
    	this.priority = priority;
    }

    @Override
    public int compareTo(Disease o) {
        return Integer.compare(priority, o.getPriority());
    }

    public Animal getAnimal() {
        return animal;
    }

    public int getPriority() {
        return priority;
    }
}
