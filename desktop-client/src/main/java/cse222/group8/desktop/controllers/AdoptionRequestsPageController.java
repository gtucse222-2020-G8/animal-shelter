package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AdoptionRequestsPageModel;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AdoptionRequestsPageController implements PageWithTokenController {
    public VBox adoptionRequestsListVBox;
    public VBox leftMenu;
    AdoptionRequestsPageModel model;
    @FXML
    public LeftMenuPanelController leftMenuController;
    @FXML
    public void initialize(){
        model = new AdoptionRequestsPageModel();
        leftMenuController.changeFocus(5);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
