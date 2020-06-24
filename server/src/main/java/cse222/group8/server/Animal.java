package cse222.group8.server;

import java.io.File;
import java.util.Date;
import java.util.Queue;

public class Animal implements Comparable<Animal> {
	private static int catCount = 0;
	private static int dogCount = 0;
	private int id;
	private String name;
	private String kind;
	private char gender;
	private int age;
	private String vaccination;
	private boolean neutered;
	private String info;
	private boolean adopted;
	private String imageString;
	private Date shelterDate;
	private int diseased;

	private AdoptionRequest adoptionRequest;

	/* address infos */
	private City city;
	private Town town;
	private Shelter shelter;

	public Animal(int id) {
		this.id = id;
	}

	public Animal(String name, String kind, int age, boolean isCat) {
		if(isCat) {
			this.id = 2 * catCount + 1;
			++catCount;
		}
		else {
			this.id = 2 * dogCount;
			++dogCount;
		}
		this.name = name;
		this.kind = kind;
		this.age = age;
		this.diseased = 0;
	}
	public Animal(String name, String kind, int age, boolean isCat, boolean increase) {
		if(isCat) {
			this.id = 2 * catCount + 1;
			if (increase) ++catCount;
		}
		else {
			this.id = 2 * dogCount;
			if (increase) ++dogCount;
		}
		this.name = name;
		this.kind = kind;
		this.age = age;
		this.diseased = 0;
	}

	public boolean updateAnimal(Animal animal) {
		if(animal.id == id){
			this.name = animal.name;
			this.kind = animal.kind;
			this.gender = animal.gender;
			this.age = animal.age;
			this.vaccination = animal.vaccination;
			this.neutered = animal.neutered;
			this.info = animal.info;
			this.imageString = animal.imageString;
			this.shelterDate = animal.shelterDate;
			this.diseased = animal.diseased;
			return true;
		}
		return false;
	}

	public int getDiseased() {
		return diseased;
	}

	public void setDiseased(int diseased) {
		this.diseased = diseased;
	}

	public AdoptionRequest makeARequest(User requester) {
		adoptionRequest = new AdoptionRequest(null, requester, this);
		adoptionRequest.setRequestDate(new Date());
		return adoptionRequest;
	}

	@Override
	public int compareTo(Animal o) {
		return Integer.compare(id, o.getId());
	}

	public void updateRequests() {
		if(isAdopted()) {

		Animal animal = new Animal(this.id);
		setAdopted(true);

		if(this.id%2 == 1) {
			getShelter().removeCat(animal);
		}
		else{
			getShelter().removeDog(animal);
		}
		}
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Shelter getShelter() {
		return shelter;
	}

	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
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

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	public Date getShelterDate() {
		return shelterDate;
	}

	public void setShelterDate(Date shelterDate) {
		this.shelterDate = shelterDate;
	}

	public AdoptionRequest getAdoptionRequest() {
		return adoptionRequest;
	}

	public void setAdoptionRequest(AdoptionRequest mainRequest) {
		this.adoptionRequest = mainRequest;
	}
}
