package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AdoptionRequestsPageModel;
import cse222.group8.desktop.models.SettingsPageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The type Settings page controller.
 */
public class SettingsPageController implements PageWithTokenController {
    /**
     * The Change capacity button.
     */
    public Button changeCapacityButton;
    /**
     * The Change password button.
     */
    public Button changePasswordButton;
    /**
     * The Change shelter name button.
     */
    public Button changeShelterNameButton;
    /**
     * The Left menu controller.
     */
    @FXML
    public LeftMenuPanelController leftMenuController;
    /**
     * The Left menu.
     */
    public VBox leftMenu;

    private SettingsPageModel model;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        model = new SettingsPageModel();
        leftMenuController.changeFocus(6);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
