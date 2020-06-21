package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AddAnimalPageModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AddAnimalPageController implements PageWithTokenController {
    public TextField nameField;
    public ComboBox genderCombo;
    public ComboBox kindCombo;
    public ComboBox ageCombo;
    public CheckBox neuteredCheck;
    public TextArea vacInfoField;
    public TextArea infoField;
    public TextField speciesField;
    public ImageView animalImageView;
    public Button addChangeImageButton;
    public Button updateButton;
    public VBox leftMenu;
    @FXML public LeftMenuPanelController leftMenuController;

    private AddAnimalPageModel model;

    @FXML
    public void initialize(){
        model = new AddAnimalPageModel();
        leftMenuController.changeFocus(1);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
