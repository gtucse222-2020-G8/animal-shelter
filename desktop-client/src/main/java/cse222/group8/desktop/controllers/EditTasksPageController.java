package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.EditTasksPageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EditTasksPageController implements PageWithTokenController {
    public Button addTaskButton;
    public VBox adoptionRequestListVBox;
    public VBox leftMenu;
    private EditTasksPageModel model;
    @FXML
    public LeftMenuPanelController leftMenuController;

    @FXML
    private void initialize(){
        System.out.println("Initialized edit tasks");
        model = new EditTasksPageModel();
        leftMenuController.changeFocus(4);
    }

    @Override
    public void setToken(Token token) {
        System.out.println("Set token edit tasks");
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
