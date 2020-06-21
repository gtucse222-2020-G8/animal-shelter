package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AddAnimalPageModel;
import cse222.group8.desktop.models.DogsPageModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DogsPageController implements PageWithTokenController {
    public VBox leftMenu;
    public GridPane gridPane;
    DogsPageModel model;
    @FXML
    public LeftMenuPanelController leftMenuController;

    @FXML
    public void initialize(){
        model = new DogsPageModel();
        leftMenuController.changeFocus(3);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
