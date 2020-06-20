package cse222.group8.desktop.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterPageController {
    public Text registerShelterName;
    public Button loginButton;
    public Button cancelButton;
    public TextField catCapacityField;
    public TextField dogCapacityField;
    public PasswordField passwordField;
    public TextField phoneNumberField;
    public TextArea addressField;
    @FXML
    private void initialize(){
        catCapacityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                catCapacityField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        dogCapacityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                dogCapacityField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void onCancelButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./views/SelectShelterPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ignore){
            System.out.println("FXML path error");
            System.exit(-1);
        }
    }
}
