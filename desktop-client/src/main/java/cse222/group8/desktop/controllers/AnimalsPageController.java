package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AnimalsPageModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AnimalsPageController implements PageWithTokenController{
    public VBox catsVBox;
    public VBox dogsVBox;
    public Button addNewAnimalButton;
    public VBox leftMenu;
    private AnimalsPageModel model;
    @FXML public LeftMenuPanelController leftMenuController;

    @FXML
    public void initialize(){
        model = new AnimalsPageModel();
        leftMenuController.changeFocus(1);
    }
    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
    private void commonButtonAction(Event e, String path){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        PageWithTokenController controller = loader.<PageWithTokenController>getController();
        controller.setToken(model.getToken());
        stage.show();
    }
    public void onAddNewAnimalButtonAction(Event e){
        commonButtonAction(e, "views/AddDogPage.fxml");
    }
    public void onCatsVBoxClicked(Event e){
        commonButtonAction(e,"./views/CatsPage.fxml");
    }
    public void onDogsVBoxClicked(Event e){
        commonButtonAction(e,"./views/DogsPage.fxml");
    }
}
