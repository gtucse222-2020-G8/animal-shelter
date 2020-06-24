package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.WrongPasswordException;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.WaitingRegisterationPageModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Waiting registeration page controller.
 */
public class WaitingRegisterationPageController {
    /**
     * The Refresh button.
     */
    public Button refreshButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;

    private WaitingRegisterationPageModel model;

    @FXML
    private void initialize(){
        model = new WaitingRegisterationPageModel();
    }

    /**
     * Set fields.
     *
     * @param token       the token
     * @param city        the city
     * @param town        the town
     * @param shelterName the shelter name
     */
    public void setFields(Token token, String city, String town, String shelterName){
        model.setCity(city);
        model.setShelterName(shelterName);
        model.setTown(town);
        model.setToken(token);
    }

    @FXML
    private void onCancelButtonAction(Event e){
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
    @FXML
    private void onRefreshButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        try{
            boolean res = Client.getShelterStatus(model.getCity(),model.getTown(),model.getShelterName());
            if(res){
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/HomePage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                HomePageController homePageController = loader.<HomePageController>getController();
                homePageController.setToken(model.getToken());
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
