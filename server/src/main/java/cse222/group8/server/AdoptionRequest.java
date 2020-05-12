package cse222.group8.server;


import java.util.Date;

public class AdoptionRequest {
    private User requester;
    private Animal requestedAnimal;
    private Date requestDate;
    private Date expirationDate;

    /* Result of adoption */
    private boolean result;

    public AdoptionRequest(Date requestDate, User requester, Animal requestedAnimal){

    }
    public AdoptionRequest(Date requestDate, Date expirationDate, User requester, Animal requestedAnimal){

    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Animal getRequestedAnimal() {
        return requestedAnimal;
    }

    public void setRequestedAnimal(Animal requestedAnimal) {
        this.requestedAnimal = requestedAnimal;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
