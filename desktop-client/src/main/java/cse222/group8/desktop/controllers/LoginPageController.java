package cse222.group8.desktop.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    public Text loginShelterName;
    public PasswordField passwordField;
    public Button loginButton;
    public Button cancelButton;

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
