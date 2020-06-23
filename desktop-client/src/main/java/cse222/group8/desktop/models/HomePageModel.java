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

public class HomePageModel extends PageWithTokenModel {
    private IntegerProperty adoptionRequestsCount;
    private IntegerProperty catCapacity;
    private IntegerProperty dogCapacity;
    private IntegerProperty catCount;
    private IntegerProperty dogCount;

    ObservableList<TaskData> tasks = FXCollections.observableArrayList();
    ObservableList<String> taskNames = FXCollections.observableArrayList();

    public void setTasks(TaskData[] tasks) {
        this.tasks.setAll(tasks);
        for(TaskData task : tasks){
            taskNames.add(task.text);
        }
    }
    public ObservableList<String> getTaskNames(){
        return taskNames;
    }
    public List<String> getActiveTasks(){
        List<String> ret = new LinkedList<String>();
        for(TaskData task : tasks){
            if(!task.status){
                ret.add(task.text);
            }
        }
        return ret;
    }
    public void addListenerToTaskNames(ListChangeListener<String> listener){
        taskNames.addListener(listener);
    }

    public int getAdoptionRequestsCount() {
        if(adoptionRequestsCount==null) throw new AppLogicError();
        return adoptionRequestsCount.getValue();
    }

    public void setAdoptionRequestsCount(int adoptionRequestsCount) {
        if(this.adoptionRequestsCount==null){
            this.adoptionRequestsCount = new SimpleIntegerProperty(adoptionRequestsCount);
        }
        else{
            this.adoptionRequestsCount.setValue(adoptionRequestsCount);
        }
    }

    public int getCatCapacity() {
        if(catCapacity==null) throw new AppLogicError();
        return catCapacity.getValue();
    }

    public void setCatCapacity(int catCapacity) {
        if(this.catCapacity==null){
            this.catCapacity = new SimpleIntegerProperty(catCapacity);
        }
        else{
            this.catCapacity.setValue(catCapacity);
        }
    }

    public int getDogCapacity() {
        if(dogCapacity==null) throw new AppLogicError();
        return dogCapacity.getValue();
    }

    public void setDogCapacity(int dogCapacity) {
        if(this.dogCapacity==null){
            this.dogCapacity = new SimpleIntegerProperty(dogCapacity);
        }
        else{
            this.dogCapacity.setValue(dogCapacity);
        }
    }

    public int getCatCount() {
        if(catCount==null) throw new AppLogicError();
        return catCount.getValue();
    }

    public void setCatCount(int catCount) {
        if(this.catCount==null){
            this.catCount = new SimpleIntegerProperty(catCount);
        }
        else{
            this.catCount.setValue(catCount);
        }
    }

    public int getDogCount() {
        if(dogCount==null) throw new AppLogicError();
        return dogCount.getValue();
    }

    public void setDogCount(int dogCount) {
        if(this.dogCount==null){
            this.dogCount = new SimpleIntegerProperty(dogCount);
        }
        else{
            this.dogCount.setValue(dogCount);
        }
    }
}
