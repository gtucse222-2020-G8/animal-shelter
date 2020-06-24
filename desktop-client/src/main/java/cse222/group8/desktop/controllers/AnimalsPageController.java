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

/**
 * The type Animals page controller.
 */
public class AnimalsPageController implements PageWithTokenController{
    /**
     * The Cats v box.
     */
    public VBox catsVBox;
    /**
     * The Dogs v box.
     */
    public VBox dogsVBox;
    /**
     * The Left menu.
     */
    public VBox leftMenu;
    /**
     * The Add new cat button.
     */
    public Button addNewCatButton;
    /**
     * The Add new dog button.
     */
    public Button addNewDogButton;
    private AnimalsPageModel model;
    /**
     * The Left menu controller.
     */
    @FXML public LeftMenuPanelController leftMenuController;

    /**
     * Initialize.
     */
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

    /**
     * On add new cat button action.
     *
     * @param e the e
     */
    public void onAddNewCatButtonAction(Event e){
        commonButtonAction(e, "./views/AddCatPage.fxml");
    }

    /**
     * On add new dog button action.
     *
     * @param e the e
     */
    public void onAddNewDogButtonAction(Event e){
        commonButtonAction(e, "./views/AddDogPage.fxml");
    }

    /**
     * On cats v box clicked.
     *
     * @param e the e
     */
    public void onCatsVBoxClicked(Event e){
        commonButtonAction(e,"./views/CatsPage.fxml");
    }

    /**
     * On dogs v box clicked.
     *
     * @param e the e
     */
    public void onDogsVBoxClicked(Event e){
        commonButtonAction(e,"./views/DogsPage.fxml");
    }
}
