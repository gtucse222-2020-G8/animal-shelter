package cse222.group8.server;

import java.util.Date;
import java.util.List;

/**
 * The type User.
 */
public class User implements Comparable<User> {

    private String name;
    private String username;
    private String password;
    private String email;
    private City city;
    private Town town;
    private List<AdoptionRequest> requests;
    private List<Animal> favorites;
    private Date signUpDate;

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Instantiates a new User.
     *
     * @param name       the name
     * @param username   the username
     * @param password   the password
     * @param city       the city
     * @param town       the town
     * @param signUpDate the sign up date
     */
    public User(String name, String username, String password, City city, Town town, Date signUpDate) {
    	this.name = name;
    	this.username = username;
    	this.password = password;
    	this.city = city;
    	this.town = town;
    	this.signUpDate = signUpDate;
    }

    /**
     * Instantiates a new User.
     *
     * @param userName the user name
     */
    protected User(String userName) {
    	this.username = userName;
    }


    /**
     * Create a request boolean.
     *
     * @param animal the animal
     * @return the boolean
     */
    public boolean createARequest(Animal animal) {
    	
    	AdoptionRequest request = new AdoptionRequest(this,animal);
    	if(!requests.contains(request)) {    		
		animal.getShelter().getAdoptionRequests().add(request);
		animal.setAdopted(true);
		requests.add(request);
		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean updateUser(User user) {
        this.setName(user.name);
        this.setUsername(user.username);
        this.setPassword(user.password);
        this.setEmail(user.email);
        this.setCity(user.city);
        this.setTown(user.town);
        this.setRequests(user.requests);
        this.setFavorites(user.favorites);

        return true;
    }

    /**
     * Add to favs.
     *
     * @param animal the animal
     */
    public void addToFavs(Animal animal) {
    	if(!favorites.contains(animal))
    		favorites.add(animal);
    }
    
    @Override
    public int compareTo(User o) {
    	  return username.compareTo(o.username);
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
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Gets requests.
     *
     * @return the requests
     */
    public List<AdoptionRequest> getRequests() {
        return requests;
    }

    /**
     * Sets requests.
     *
     * @param requests the requests
     */
    public void setRequests(List<AdoptionRequest> requests) {
        this.requests = requests;
    }

    /**
     * Gets favorites.
     *
     * @return the favorites
     */
    public List<Animal> getFavorites() {
		return favorites;
	}

    /**
     * Sets favorites.
     *
     * @param favorites the favorites
     */
    public void setFavorites(List<Animal> favorites) {
		this.favorites = favorites;
	}

    /**
     * Gets sign up date.
     *
     * @return the sign up date
     */
    public Date getSignUpDate() {
		return signUpDate;
	}

}
