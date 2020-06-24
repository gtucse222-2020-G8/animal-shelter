package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.WrongPasswordException;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.LoginPageModel;
import cse222.group8.desktop.models.PageWithTokenModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController{
    public Text loginShelterName;
    public PasswordField passwordField;
    public Button loginButton;
    public Button cancelButton;

    private LoginPageModel model;

    public void setLoginShelterName(String shelterName){
        loginShelterName.setText(shelterName);
    }
    public void setCity(String city){
        model.setCity(city);
    }
    public void setTown(String town){
        model.setTown(town);
    }

    @FXML public void initialize(){
        model = new LoginPageModel();
        passwordField.textProperty().addListener((observableValue, s, t1) -> {
            if(t1==null || t1.length()==0){
                loginButton.setDisable(true);
            }
            else if(s==null || s.length()==0){
                loginButton.setDisable(false);
            }
            model.setPassword(t1);
        });
    }

    public void onLoginButtonAction(Event e){
        String shelterName = loginShelterName.getText();
        String password = model.getPassword();
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        try{
            Token token;
            token = Client.login(model.getCity(),model.getTown(),shelterName,password);
            boolean res = Client.getShelterStatus(model.getCity(),model.getTown(),loginShelterName.getText());
            if(res){
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/HomePage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                HomePageController homePageController = loader.<HomePageController>getController();
                homePageController.setToken(token);
                stage.show();
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/WaitingRegisterationPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                WaitingRegisterationPageController waitingRegisterationPageController = loader.<WaitingRegisterationPageController>getController();
                waitingRegisterationPageController.setFields(token,model.getCity(),model.getTown(),loginShelterName.getText());
                stage.show();
            }
        } catch (WrongPasswordException wrongPasswordException) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Wrong Password", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } catch (ConnectionError ignore) {
            System.out.println("Connection error");
            System.exit(-1);
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
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
