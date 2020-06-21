package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.RegisterPageModel;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterPageController {
    public Text registerShelterName;
    public Button submitButton;
    public Button cancelButton;
    public TextField catCapacityField;
    public TextField dogCapacityField;
    public PasswordField passwordField;
    public TextField phoneNumberField;
    public TextArea addressField;
    private RegisterPageModel model;
    @FXML
    private void initialize(){
        model = new RegisterPageModel();
        catCapacityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                newValue = newValue.replaceAll("[^\\d]", "");
                catCapacityField.setText(newValue);
            }
            try {
                model.setCatCapacity(Integer.parseInt(newValue));
            }catch (NumberFormatException ignore){}
        });
        dogCapacityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                newValue = newValue.replaceAll("[^\\d]", "");
                dogCapacityField.setText(newValue);
            }
            try {
                model.setDogCapacity(Integer.parseInt(newValue));
            }catch (NumberFormatException ignore){}
        });
        passwordField.textProperty().addListener((observableValue, s, t1) -> model.setPassword(t1));
        phoneNumberField.textProperty().addListener((observableValue, s, t1) -> model.setPhoneNumber(t1));
        addressField.textProperty().addListener((observableValue, s, t1) -> model.setAddress(t1));
    }
    public void setFields(String shelterName, String city, String town){
        registerShelterName.setText(shelterName);
        model.setCity(city);
        model.setTown(town);
    }
    private boolean checkFields(){
        boolean valid = true;
        if(model.getAddress()==null || model.getAddress().length()<3){
            addressField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            addressField.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getPhoneNumber()==null || model.getPhoneNumber().length()<10){
            phoneNumberField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            phoneNumberField.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getPassword()==null || model.getPassword().length()<4){
            passwordField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            passwordField.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getCatCapacity()<0){
            catCapacityField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            catCapacityField.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getDogCapacity()<0){
            dogCapacityField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            dogCapacityField.setStyle("-fx-background-color: #FFFFFF;");
        }
        return valid;
    }
    public void onSubmitButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        if(checkFields()){
            try {
                Token token = Client.createShelter(model.getCity(), model.getTown(), registerShelterName.getText(), model.getPhoneNumber(), model.getPassword(), model.getCatCapacity(), model.getDogCapacity(), model.getAddress());
                int res = Client.getShelterStatus(model.getCity(),model.getTown(),registerShelterName.getText());
                if(res==1){
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
                    waitingRegisterationPageController.setFields(token,model.getCity(),model.getTown(),registerShelterName.getText());
                    stage.show();
                }
            } catch (ConnectionError ignore) {
                System.out.println("Connection error");
                System.exit(-1);
            } catch (IOException ioException) {
                System.out.println("File not found");
                System.exit(-1);
            }
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
