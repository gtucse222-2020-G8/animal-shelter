package cse222.group8.server;


import java.util.Calendar;
import java.util.Date;

/**
 * The type Adoption request.
 */
public class AdoptionRequest {
    private User requester;
    private Animal requestedAnimal;
    private Date requestDate;
    private Date expirationDate;

    /* Result of adoption */
    private boolean result;

    /**
     * Instantiates a new Adoption request.
     *
     * @param requestDate     the request date
     * @param requester       the requester
     * @param requestedAnimal the requested animal
     */
    public AdoptionRequest(Date requestDate, User requester, Animal requestedAnimal){
        this.requestDate = requestDate;
        this.requester = requester;
        this.requestedAnimal = requestedAnimal;
        Calendar c = Calendar.getInstance();
        c.setTime(requestDate);
        c.add(Calendar.DATE, 7);
        expirationDate = c.getTime();
    }

    /**
     * Instantiates a new Adoption request.
     *
     * @param requester       the requester
     * @param requestedAnimal the requested animal
     */
    public AdoptionRequest(User requester, Animal requestedAnimal){
        this(new Date(),requester,requestedAnimal);
    }

    /**
     * Instantiates a new Adoption request.
     *
     * @param requestDate     the request date
     * @param expirationDate  the expiration date
     * @param requester       the requester
     * @param requestedAnimal the requested animal
     */
    public AdoptionRequest(Date requestDate, Date expirationDate, User requester, Animal requestedAnimal){
        this.requestDate = requestDate;
        this.requester = requester;
        this.requestedAnimal = requestedAnimal;
        this.expirationDate = expirationDate;
    }

    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Gets request date.
     *
     * @return the request date
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Sets request date.
     *
     * @param requestDate the request date
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * Gets requester.
     *
     * @return the requester
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Sets requester.
     *
     * @param requester the requester
     */
    public void setRequester(User requester) {
        this.requester = requester;
    }

    /**
     * Gets requested animal.
     *
     * @return the requested animal
     */
    public Animal getRequestedAnimal() {
        return requestedAnimal;
    }

    /**
     * Sets requested animal.
     *
     * @param requestedAnimal the requested animal
     */
    public void setRequestedAnimal(Animal requestedAnimal) {
        this.requestedAnimal = requestedAnimal;
    }

    /**
     * Is result boolean.
     *
     * @return the boolean
     */
    public boolean isResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

}
