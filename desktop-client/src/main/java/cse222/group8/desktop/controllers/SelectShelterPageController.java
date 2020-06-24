package cse222.group8.desktop.controllers;

import cse222.group8.desktop.Helpers;
import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.models.RegisterPageModel;
import cse222.group8.desktop.models.SelectShelterPageModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Select shelter page controller.
 */
public class SelectShelterPageController {
    /**
     * The City select combo.
     */
    public ComboBox<String> citySelectCombo;
    /**
     * The Town select combo.
     */
    public ComboBox<String> townSelectCombo;
    /**
     * The Shelter name field.
     */
    public TextField shelterNameField;
    /**
     * The Submit button.
     */
    public Button submitButton;

    private SelectShelterPageModel model;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        this.model = new SelectShelterPageModel();
        citySelectCombo.setItems(model.getCityList());
        townSelectCombo.setItems(model.getTownList());
        try {
            model.getCityList().setAll(Client.getCities());
        } catch (ConnectionError connectionError) {
            connectionError.printStackTrace();
            System.exit(-1);
        }
        citySelectCombo.valueProperty().addListener((observableValue, s, t1) -> {
            model.setCurrentCity(t1);
            if(s == null && t1 != null) {
                townSelectCombo.setDisable(false);
            }
            try {
                model.getTownList().setAll(Client.getTowns(t1));
            } catch (ConnectionError connectionError) {
                connectionError.printStackTrace();
                System.exit(-1);
            }
        });
        townSelectCombo.valueProperty().addListener((observableValue, s, t1) -> {
            model.setCurrentTown(t1);
            if(s == null && t1 != null) {
                shelterNameField.setDisable(false);
            }
        });
        shelterNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            model.setCurrentShelterName(newValue);
            submitButton.setDisable(newValue.length() < 5);
        });
    }

    /**
     * On submit button action.
     *
     * @param e the e
     */
    public void onSubmitButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        try {
            String[] shelters = Client.getShelters(model.getCurrentCity(),model.getCurrentTown());
            Parent root;
            if(Helpers.arrayContains(shelters,model.getCurrentShelterName())) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/LoginPage.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                LoginPageController controller = loader.<LoginPageController>getController();
                controller.setCity(model.getCurrentCity());
                controller.setTown(model.getCurrentTown());
                controller.setLoginShelterName(model.getCurrentShelterName());
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/RegisterPage.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                RegisterPageController controller = loader.<RegisterPageController>getController();
                controller.setFields(model.getCurrentShelterName(),model.getCurrentCity(),model.getCurrentTown());
            }
            stage.show();
        }catch (IOException ignore){
            System.out.println("FXML path error");
            System.exit(-1);
        } catch (ConnectionError ignore) {
            System.out.println("Connection error");
            System.exit(-1);
        }
    }
}
