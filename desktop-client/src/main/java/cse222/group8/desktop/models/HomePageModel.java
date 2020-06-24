package cse222.group8.desktop.models;

import cse222.group8.desktop.AppLogicError;
import cse222.group8.desktop.client.models.TaskData;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Home page model.
 */
public class HomePageModel extends PageWithTokenModel {
    private IntegerProperty adoptionRequestsCount;
    private IntegerProperty catCapacity;
    private IntegerProperty dogCapacity;
    private IntegerProperty catCount;
    private IntegerProperty dogCount;

    /**
     * The Tasks.
     */
    ObservableList<TaskData> tasks = FXCollections.observableArrayList();
    /**
     * The Task names.
     */
    ObservableList<String> taskNames = FXCollections.observableArrayList();

    /**
     * Sets tasks.
     *
     * @param tasks the tasks
     */
    public void setTasks(TaskData[] tasks) {
        this.tasks.setAll(tasks);
        for(TaskData task : tasks){
            taskNames.add(task.text);
        }
    }

    /**
     * Get task names observable list.
     *
     * @return the observable list
     */
    public ObservableList<String> getTaskNames(){
        return taskNames;
    }

    /**
     * Get active tasks list.
     *
     * @return the list
     */
    public List<String> getActiveTasks(){
        List<String> ret = new LinkedList<String>();
        for(TaskData task : tasks){
            if(!task.status){
                ret.add(task.text);
            }
        }
        return ret;
    }

    /**
     * Add listener to task names.
     *
     * @param listener the listener
     */
    public void addListenerToTaskNames(ListChangeListener<String> listener){
        taskNames.addListener(listener);
    }

    /**
     * Gets adoption requests count.
     *
     * @return the adoption requests count
     */
    public int getAdoptionRequestsCount() {
        if(adoptionRequestsCount==null) throw new AppLogicError();
        return adoptionRequestsCount.getValue();
    }

    /**
     * Sets adoption requests count.
     *
     * @param adoptionRequestsCount the adoption requests count
     */
    public void setAdoptionRequestsCount(int adoptionRequestsCount) {
        if(this.adoptionRequestsCount==null){
            this.adoptionRequestsCount = new SimpleIntegerProperty(adoptionRequestsCount);
        }
        else{
            this.adoptionRequestsCount.setValue(adoptionRequestsCount);
        }
    }

    /**
     * Gets cat capacity.
     *
     * @return the cat capacity
     */
    public int getCatCapacity() {
        if(catCapacity==null) throw new AppLogicError();
        return catCapacity.getValue();
    }

    /**
     * Sets cat capacity.
     *
     * @param catCapacity the cat capacity
     */
    public void setCatCapacity(int catCapacity) {
        if(this.catCapacity==null){
            this.catCapacity = new SimpleIntegerProperty(catCapacity);
        }
        else{
            this.catCapacity.setValue(catCapacity);
        }
    }

    /**
     * Gets dog capacity.
     *
     * @return the dog capacity
     */
    public int getDogCapacity() {
        if(dogCapacity==null) throw new AppLogicError();
        return dogCapacity.getValue();
    }

    /**
     * Sets dog capacity.
     *
     * @param dogCapacity the dog capacity
     */
    public void setDogCapacity(int dogCapacity) {
        if(this.dogCapacity==null){
            this.dogCapacity = new SimpleIntegerProperty(dogCapacity);
        }
        else{
            this.dogCapacity.setValue(dogCapacity);
        }
    }

    /**
     * Gets cat count.
     *
     * @return the cat count
     */
    public int getCatCount() {
        if(catCount==null) throw new AppLogicError();
        return catCount.getValue();
    }

    /**
     * Sets cat count.
     *
     * @param catCount the cat count
     */
    public void setCatCount(int catCount) {
        if(this.catCount==null){
            this.catCount = new SimpleIntegerProperty(catCount);
        }
        else{
            this.catCount.setValue(catCount);
        }
    }

    /**
     * Gets dog count.
     *
     * @return the dog count
     */
    public int getDogCount() {
        if(dogCount==null) throw new AppLogicError();
        return dogCount.getValue();
    }

    /**
     * Sets dog count.
     *
     * @param dogCount the dog count
     */
    public void setDogCount(int dogCount) {
        if(this.dogCount==null){
            this.dogCount = new SimpleIntegerProperty(dogCount);
        }
        else{
            this.dogCount.setValue(dogCount);
        }
    }
}
