package cse222.group8.server.clientModels;

/**
 * The type Animal data.
 */
public class AnimalData {
    /**
     * The Id.
     */
    public int id;
    /**
     * The Name.
     */
    public String name;
    /**
     * The Breed.
     */
    public String breed;
    /**
     * The Kind.
     */
    public String kind;
    /**
     * The Gender.
     */
    public char gender;
    /**
     * The Age.
     */
    public int age;
    /**
     * The Vaccination.
     */
    public String vaccination;
    /**
     * The Neutered.
     */
    public boolean neutered;
    /**
     * The Info.
     */
    public String info;
    /**
     * The Adopt requested.
     */
    public boolean adoptRequested;
    /**
     * The Date.
     */
    public String date;

    /**
     * Instantiates a new Animal data.
     *
     * @param id             the id
     * @param name           the name
     * @param breed          the breed
     * @param kind           the kind
     * @param gender         the gender
     * @param age            the age
     * @param vaccination    the vaccination
     * @param neutered       the neutered
     * @param info           the info
     * @param adoptRequested the adopt requested
     */
    public AnimalData(int id, String name, String breed, String kind, char gender, int age, String vaccination, boolean neutered, String info, boolean adoptRequested){
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.kind = kind;
        this.gender = gender;
        this.age = age;
        this.vaccination = vaccination;
        this.neutered = neutered;
        this.info = info;
        this.adoptRequested = adoptRequested;
    }

}
