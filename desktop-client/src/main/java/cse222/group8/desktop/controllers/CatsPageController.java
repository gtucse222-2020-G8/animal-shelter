package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.CatsPageModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CatsPageController implements PageWithTokenController {
    public GridPane gridPane;
    public VBox leftMenu;
    private CatsPageModel model;
    @FXML
    public LeftMenuPanelController leftMenuController;

    @FXML
    public void initialize(){
        model = new CatsPageModel();
        leftMenuController.changeFocus(2);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
