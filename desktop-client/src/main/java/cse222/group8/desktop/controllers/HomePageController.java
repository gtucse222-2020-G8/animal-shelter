package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.HomePageModel;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class HomePageController {
    public VBox dailyTaskVBox;
    public Circle adoptionRequestsCircle;
    public Text adoptionRequestsText;
    public Circle catsCircle;
    public Text catsText;
    public Circle dogsCircle;
    public Text dogsText;

    private HomePageModel model;

    @FXML
    public void initialize(){
        model = new HomePageModel();
    }

    public void setToken(Token token){
        model.setToken(token);
    }

}
