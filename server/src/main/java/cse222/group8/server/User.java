package cse222.group8.server;

import java.util.Date;
import java.util.List;

public class User implements Comparable<User> {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private City city;
    private Town town;
    private List<AdoptionRequest> requests;
    private List<Animal> favorites;
    private Date signUpDate;
    
    
    public AdoptionRequest createARequest(Animal animal) {
    	//TODO
    	return null;
    }

    public void updateUser(User user) {
    	/* param can change */
    	//TODO
    }
    
    public void addToFavs(Animal animal) {
    	//TODO
    }
    
    @Override
    public int compareTo(User o) {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<AdoptionRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<AdoptionRequest> requests) {
        this.requests = requests;
    }

	public List<Animal> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Animal> favorites) {
		this.favorites = favorites;
	}

	public Date getSignUpDate() {
		return signUpDate;
	}

}
