package cse222.group8.server;

public class Task {

    private String task;
    private boolean status;

    public Task(String task, boolean status){
        this.task = task;
        this.status = status;
    }
    public Task(){
        this.task = null;
        this.status = false;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
