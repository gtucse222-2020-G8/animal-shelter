package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.models.TaskData;
import cse222.group8.desktop.client.models.Token;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DailyTasksListComponentController implements PageWithTokenController{
    public VBox dailyTasksVBox;

    @FXML
    private void initialize(){
    }

    public void setToken(Token token){
    }

}
