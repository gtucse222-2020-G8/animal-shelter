package cse222.group8.server;

/**
 * The type Task.
 */
public class Task {

    private String task;
    private boolean status;

    /**
     * Instantiates a new Task.
     *
     * @param task   the task
     * @param status the status
     */
    public Task(String task, boolean status){
        this.task = task;
        this.status = status;
    }

    /**
     * Instantiates a new Task.
     */
    public Task(){
        this.task = null;
        this.status = false;
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets task.
     *
     * @param task the task
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
