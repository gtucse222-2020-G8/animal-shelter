package cse222.group8.desktop.models;

import cse222.group8.desktop.client.models.AnimalData;
import cse222.group8.desktop.client.models.AnimalDataWithImage;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.util.Base64;

/**
 * The type Add cat page model.
 */
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

    /**
     * Sets animal data.
     *
     * @param data the data
     */
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

    /**
     * Get ımage string.
     *
     * @return the string
     */
    public String getImage(){
        return image;
    }

    /**
     * Get animal data animal data.
     *
     * @return the animal data
     */
    public AnimalData getAnimalData(){
        return new AnimalData(animalId,name,"cat",kind,gender,age,vaccination,neutered,info,false);
    }

    /**
     * Get ımage as ımage ımage.
     *
     * @return the ımage
     */
    public Image getImageAsImage(){
        byte[] img = Base64.getDecoder().decode(image);
        ByteArrayInputStream bais = new ByteArrayInputStream(img);
        return new Image(bais);
    }

    /**
     * Is editing boolean.
     *
     * @return the boolean
     */
    public boolean isEditing(){
        return editing;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Gets vaccination.
     *
     * @return the vaccination
     */
    public String getVaccination() {
        return vaccination;
    }

    /**
     * Sets vaccination.
     *
     * @param vaccination the vaccination
     */
    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    /**
     * Gets ınfo.
     *
     * @return the ınfo
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets ınfo.
     *
     * @param info the info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Is neutered boolean.
     *
     * @return the boolean
     */
    public boolean isNeutered() {
        return neutered;
    }

    /**
     * Sets neutered.
     *
     * @param neutered the neutered
     */
    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    /**
     * Gets animal ıd.
     *
     * @return the animal ıd
     */
    public int getAnimalId() {
        return animalId;
    }

    /**
     * Sets animal ıd.
     *
     * @param animalId the animal ıd
     */
    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Character getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(Character gender) {
        this.gender = gender;
    }

    /**
     * Gets kind.
     *
     * @return the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets kind.
     *
     * @param kind the kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Sets ımage.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
