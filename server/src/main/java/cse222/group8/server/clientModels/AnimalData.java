package cse222.group8.server.clientModels;

public class AnimalData {
    public int id;
    public String name;
    public String breed;
    public String kind;
    public char gender;
    public String age;
    public String vaccination;
    public boolean neutered;
    public String info;
    public boolean adoptRequested;
    public String date;

    public AnimalData(int id, String name, String breed, String kind, char gender, String age, String vaccination, boolean neutered, String info, boolean adoptRequested){
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
