package cse222.group8.desktop.models;

public class AnimalDataWithImage extends AnimalData{
    String image;

    public AnimalDataWithImage(int id, String name, String kind, char gender, int age, String vaccination, boolean neutered, String info, boolean adoptRequested, String image) {
        super(id, name, kind, gender, age, vaccination, neutered, info, adoptRequested);
        this.image = image;
    }
    public AnimalDataWithImage(AnimalData animalData, String image) {
        super(animalData.id, animalData.name, animalData.kind, animalData.gender, animalData.age, animalData.vaccination, animalData.neutered, animalData.info, animalData.adoptRequested);
        this.image = image;
    }
}
