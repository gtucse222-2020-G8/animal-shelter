package cse222.group8.server.clientModels;

public class AnimalDataWithImage extends AnimalData{
    public String image;

    public AnimalDataWithImage(int id, String name, String breed, String kind, char gender, int age, String vaccination, boolean neutered, String info, boolean adoptRequested, String image) {
        super(id, name, breed, kind, gender, age, vaccination, neutered, info, adoptRequested);
        this.image = image;
    }
    public AnimalDataWithImage(AnimalData animalData, String image) {
        super(animalData.id, animalData.name, animalData.breed, animalData.kind, animalData.gender, animalData.age, animalData.vaccination, animalData.neutered, animalData.info, animalData.adoptRequested);
        this.image = image;
    }
}
