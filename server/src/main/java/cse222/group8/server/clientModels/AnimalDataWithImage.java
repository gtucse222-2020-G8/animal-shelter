package cse222.group8.server.clientModels;

/**
 * The type Animal data with ımage.
 */
public class AnimalDataWithImage extends AnimalData{
    /**
     * The Image.
     */
    public String image;

    /**
     * Instantiates a new Animal data with ımage.
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
     * @param image          the image
     */
    public AnimalDataWithImage(int id, String name, String breed, String kind, char gender, int age, String vaccination, boolean neutered, String info, boolean adoptRequested, String image) {
        super(id, name, breed, kind, gender, age, vaccination, neutered, info, adoptRequested);
        this.image = image;
    }

    /**
     * Instantiates a new Animal data with ımage.
     *
     * @param animalData the animal data
     * @param image      the image
     */
    public AnimalDataWithImage(AnimalData animalData, String image) {
        super(animalData.id, animalData.name, animalData.breed, animalData.kind, animalData.gender, animalData.age, animalData.vaccination, animalData.neutered, animalData.info, animalData.adoptRequested);
        this.image = image;
    }
}
