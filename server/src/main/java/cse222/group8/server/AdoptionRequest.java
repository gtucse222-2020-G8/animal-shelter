package cse222.group8.server;


import java.util.Calendar;
import java.util.Date;

public class AdoptionRequest {
    private User requester;
    private Animal requestedAnimal;
    private Date requestDate;
    private Date expirationDate;

    /* Result of adoption */
    private boolean result;

    public AdoptionRequest(Date requestDate, User requester, Animal requestedAnimal){
        this.requestDate = requestDate;
        this.requester = requester;
        this.requestedAnimal = requestedAnimal;
        Calendar c = Calendar.getInstance();
        c.setTime(requestDate);
        c.add(Calendar.DATE, 7);
        expirationDate = c.getTime();
    }
    public AdoptionRequest(User requester, Animal requestedAnimal){
        this(new Date(),requester,requestedAnimal);
    }

    public AdoptionRequest(Date requestDate, Date expirationDate, User requester, Animal requestedAnimal){
        this.requestDate = requestDate;
        this.requester = requester;
        this.requestedAnimal = requestedAnimal;
        this.expirationDate = expirationDate;
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
