package cse222.group8.desktop.models;

public class AnimalData {
    public int id;
    public String name;
    public String kind;
    public char gender;
    public int age;
    public String vaccination;
    public boolean neutered;
    public String info;
    public boolean adoptRequested;

    public AnimalData(int id, String name, String kind, char gender, int age, String vaccination, boolean neutered, String info, boolean adoptRequested){
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.gender = gender;
        this.age = age;
        this.vaccination = vaccination;
        this.neutered = neutered;
        this.info = info;
        this.adoptRequested = adoptRequested;
    }

}
