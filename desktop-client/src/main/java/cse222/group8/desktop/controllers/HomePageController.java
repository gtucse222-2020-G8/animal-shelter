package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.models.GeneralShelterData;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.HomePageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * The type Home page controller.
 */
public class HomePageController implements PageWithTokenController {
    /**
     * The Adoption requests circle.
     */
    public Circle adoptionRequestsCircle;
    /**
     * The Adoption requests text.
     */
    public Text adoptionRequestsText;
    /**
     * The Cats circle.
     */
    public Circle catsCircle;
    /**
     * The Cats text.
     */
    public Text catsText;
    /**
     * The Dogs circle.
     */
    public Circle dogsCircle;
    /**
     * The Dogs text.
     */
    public Text dogsText;
    /**
     * The Left menu.
     */
    public VBox leftMenu;
    /**
     * The Daily task v box.
     */
    public VBox dailyTaskVBox;
    /**
     * The Model.
     */
    HomePageModel model;

    /**
     * The Left menu controller.
     */
    @FXML public LeftMenuPanelController leftMenuController;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        model = new HomePageModel();
        leftMenuController.changeFocus(0);
        model.addListenerToTaskNames(change -> {
            LinkedList<Label> labels = new LinkedList<Label>();
            for(String text : model.getActiveTasks()){
                Label label = new Label(text);
                label.setPrefHeight(30);
                labels.add(label);
            }
            dailyTaskVBox.getChildren().setAll(labels);
        });
    }

    public void setToken(Token token){
        model.setToken(token);
        try {
            GeneralShelterData generalShelterData = Client.getGeneralShelterData(model.getToken());
            model.setAdoptionRequestsCount(generalShelterData.adoptionRequestsCount);
            model.setCatCapacity(generalShelterData.catCapacity);
            model.setDogCapacity(generalShelterData.dogCapacity);
            model.setCatCount(generalShelterData.catCount);
            model.setDogCount(generalShelterData.dogCount);
        } catch (ConnectionError connectionError) {
            System.out.println("Connection Error");
            System.exit(-1);
        }
        catsText.setText(model.getCatCount()+"/"+model.getCatCapacity());
        dogsText.setText(model.getDogCount()+"/"+model.getDogCapacity());
        adoptionRequestsText.setText(model.getAdoptionRequestsCount()+"");
        try{
            model.setTasks(Client.getTasks(token));
        } catch (ConnectionError connectionError) {
            System.out.println("Connection Error");
            System.exit(-1);
        }
        leftMenuController.setToken(model.getToken());
        postInit();
    }

    private void postInit(){
        String greyCircle = "#AAAAAA";
        String greenCircle = "#00CCCC";
        String redCircle = "#FF3333";
        String yellowCircle = "#DDDD00";
        double denseLowerLimit = 0.4;
        if(model.getCatCapacity()<=0){
            catsCircle.setFill(Paint.valueOf(greenCircle));
        }
        else if(((double)model.getCatCount()/(double)model.getCatCapacity()) > denseLowerLimit){
            catsCircle.setFill(Paint.valueOf(redCircle));
        }
        else{
            catsCircle.setFill(Paint.valueOf(greenCircle));
        }

        if(model.getDogCapacity()<=0){
            dogsCircle.setFill(Paint.valueOf(greyCircle));
        }
        else if(((double)model.getDogCount()/(double)model.getDogCapacity()) > denseLowerLimit){
            dogsCircle.setFill(Paint.valueOf(redCircle));
        }
        else{
            dogsCircle.setFill(Paint.valueOf(greenCircle));
        }

        if(model.getAdoptionRequestsCount()==0){
            adoptionRequestsCircle.setFill(Paint.valueOf(greenCircle));
        }
        else{
            adoptionRequestsCircle.setFill(Paint.valueOf(yellowCircle));
        }
    }

}
