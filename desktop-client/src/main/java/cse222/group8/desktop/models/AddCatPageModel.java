package cse222.group8.desktop.models;

import cse222.group8.desktop.client.models.AnimalData;
import cse222.group8.desktop.client.models.AnimalDataWithImage;

public class AddCatPageModel extends PageWithTokenModel{
    private Character gender;
    private String kind;
    private String image;
    private String name;
    private String age;
    private String vaccination;
    private String info;
    private boolean neutered;
    private int animalId;
    private boolean editing = false;

    public void setAnimalData(AnimalDataWithImage data) {
        this.gender = data.gender;
        this.kind = data.kind;
        this.image = data.image;
        this.name = data.name;
        this.age = data.age;
        this.vaccination = data.vaccination;
        this.info = data.info;
        this.neutered = data.neutered;
        this.animalId = data.id;
        this.editing = true;
    }
    public String getImage(){
        return image;
    }
    public AnimalData getAnimalData(){
        return new AnimalData(animalId,name,"cat",kind,gender,age,vaccination,neutered,info,false);
    }

    public boolean isEditing(){
        return editing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
