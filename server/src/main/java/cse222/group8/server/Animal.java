package cse222.group8.server;

import java.io.File;
import java.util.Date;
import java.util.Queue;

/**
 * The type Animal.
 */
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

	/**
	 * Instantiates a new Animal.
	 *
	 * @param id the id
	 */
	public Animal(int id) {
		this.id = id;
	}

	/**
	 * Instantiates a new Animal.
	 *
	 * @param name  the name
	 * @param kind  the kind
	 * @param age   the age
	 * @param isCat the is cat
	 */
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

	/**
	 * Instantiates a new Animal.
	 *
	 * @param name     the name
	 * @param kind     the kind
	 * @param age      the age
	 * @param isCat    the is cat
	 * @param increase the increase
	 */
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

	/**
	 * Update animal boolean.
	 *
	 * @param animal the animal
	 * @return the boolean
	 */
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

	/**
	 * Gets diseased.
	 *
	 * @return the diseased
	 */
	public int getDiseased() {
		return diseased;
	}

	/**
	 * Sets diseased.
	 *
	 * @param diseased the diseased
	 */
	public void setDiseased(int diseased) {
		this.diseased = diseased;
	}

	/**
	 * Make a request adoption request.
	 *
	 * @param requester the requester
	 * @return the adoption request
	 */
	public AdoptionRequest makeARequest(User requester) {
		adoptionRequest = new AdoptionRequest(null, requester, this);
		adoptionRequest.setRequestDate(new Date());
		return adoptionRequest;
	}

	@Override
	public int compareTo(Animal o) {
		return Integer.compare(id, o.getId());
	}

	/**
	 * Update requests.
	 */
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

	/**
	 * Gets city.
	 *
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Sets city.
	 *
	 * @param city the city
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * Gets town.
	 *
	 * @return the town
	 */
	public Town getTown() {
		return town;
	}

	/**
	 * Sets town.
	 *
	 * @param town the town
	 */
	public void setTown(Town town) {
		this.town = town;
	}

	/**
	 * Gets shelter.
	 *
	 * @return the shelter
	 */
	public Shelter getShelter() {
		return shelter;
	}

	/**
	 * Sets shelter.
	 *
	 * @param shelter the shelter
	 */
	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
	}

	/**
	 * Gets ıd.
	 *
	 * @return the ıd
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets ıd.
	 *
	 * @param id the id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Gets gender.
	 *
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Sets gender.
	 *
	 * @param gender the gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Gets age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets age.
	 *
	 * @param age the age
	 */
	public void setAge(int age) {
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
	 * Is adopted boolean.
	 *
	 * @return the boolean
	 */
	public boolean isAdopted() {
		return adopted;
	}

	/**
	 * Sets adopted.
	 *
	 * @param adopted the adopted
	 */
	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}

	/**
	 * Gets ımage string.
	 *
	 * @return the ımage string
	 */
	public String getImageString() {
		return imageString;
	}

	/**
	 * Sets ımage string.
	 *
	 * @param imageString the image string
	 */
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	/**
	 * Gets shelter date.
	 *
	 * @return the shelter date
	 */
	public Date getShelterDate() {
		return shelterDate;
	}

	/**
	 * Sets shelter date.
	 *
	 * @param shelterDate the shelter date
	 */
	public void setShelterDate(Date shelterDate) {
		this.shelterDate = shelterDate;
	}

	/**
	 * Gets adoption request.
	 *
	 * @return the adoption request
	 */
	public AdoptionRequest getAdoptionRequest() {
		return adoptionRequest;
	}

	/**
	 * Sets adoption request.
	 *
	 * @param mainRequest the main request
	 */
	public void setAdoptionRequest(AdoptionRequest mainRequest) {
		this.adoptionRequest = mainRequest;
	}
}
