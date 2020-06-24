package cse222.group8.server;

/**
 * The type Disease.
 */
public class Disease implements Comparable<Disease>{
    private Animal animal;
    private int priority;

    /**
     * Instantiates a new Disease.
     *
     * @param animal   the animal
     * @param priority the priority
     */
    public Disease(Animal animal, int priority){
        this.animal = animal;
    	this.priority = priority;
    }

    @Override
    public int compareTo(Disease o) {
        return Integer.compare(priority, o.getPriority());
    }

    /**
     * Gets animal.
     *
     * @return the animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }
}
