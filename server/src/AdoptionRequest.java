public class AdoptionRequest {
    private User requester;
    private Animal requestedAnimal;
    // request date field needed.
    // expiration date field needed.

    /* Result of adoption */
    private boolean result;
    
    
    
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
