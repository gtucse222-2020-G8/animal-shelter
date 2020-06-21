package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AnimalsPageModel;
import cse222.group8.desktop.models.HomePageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AnimalsPageController implements PageWithTokenController{
    public VBox catsVBox;
    public VBox dogsVBox;
    public Button addNewAnimalButton;
    public VBox leftMenu;
    private AnimalsPageModel model;
    @FXML public LeftMenuPanelController leftMenuController;

    @FXML
    public void initialize(){
        model = new AnimalsPageModel();
        leftMenuController.changeFocus(1);
    }
    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
