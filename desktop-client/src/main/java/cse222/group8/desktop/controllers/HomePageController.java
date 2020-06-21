package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.models.GeneralShelterData;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.HomePageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class HomePageController implements PageWithTokenController {
    public Circle adoptionRequestsCircle;
    public Text adoptionRequestsText;
    public Circle catsCircle;
    public Text catsText;
    public Circle dogsCircle;
    public Text dogsText;
    public VBox leftMenu;
    public VBox dailyTaskVBox;
    HomePageModel model;

    @FXML public LeftMenuPanelController leftMenuController;

    @FXML
    public void initialize(){
        System.out.println("Initialized home");
        model = new HomePageModel();
        leftMenuController.changeFocus(0);
        model.addListenerToTaskNames(change -> {
            for(String text : model.getTaskNames()){
                dailyTaskVBox.getChildren().add(new Label(text));
            }
        });
    }

    public void setToken(Token token){
        System.out.println("Set token home");
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
    }

}
