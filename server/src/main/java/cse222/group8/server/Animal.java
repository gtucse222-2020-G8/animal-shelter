package cse222.group8.server;

import java.io.File;
import java.util.Queue;

public class Animal implements Comparable<Animal> {
    /* city town shelter*/
    private int id;
    private String name;
    private String kind;
    private char gender;
    private int age;
    private String vaccination;
    private boolean neutered;
    private String info;
    private boolean adopted;
    private File imgPath;

    //Shelter date field needed

    AdoptionRequest mainRequest;
    Queue<AdoptionRequest> requestQueue;

    public AdoptionRequest makeARequest(User requester) {
        //TODO
        return null;
    }

    @Override
    public int compareTo(Animal o) {
        return Integer.compare(id, o.getId());
    }

    public void updateRequests() {}

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public AdoptionRequest getMainRequest() {
        return mainRequest;
    }

    public void setMainRequest(AdoptionRequest mainRequest) {
        this.mainRequest = mainRequest;
    }

    public Queue<AdoptionRequest> getRequestQueue() {
        return requestQueue;
    }

    public void setReuqestQueue(Queue<AdoptionRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
