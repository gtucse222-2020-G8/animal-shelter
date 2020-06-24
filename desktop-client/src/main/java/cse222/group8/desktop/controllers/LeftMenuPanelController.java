package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.LeftMenuPanelModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import java.util.Objects;

/**
 * The type Left menu panel controller.
 */
public class LeftMenuPanelController implements PageWithTokenController {

    /**
     * The Menu v box.
     */
    public VBox menuVBox;
    /**
     * The Menu home button.
     */
    public Button menuHomeButton;
    /**
     * The Menu animals button.
     */
    public Button menuAnimalsButton;
    /**
     * The Menu cat button.
     */
    public Button menuCatButton;
    /**
     * The Menu dog button.
     */
    public Button menuDogButton;
    /**
     * The Menu edit tasks button.
     */
    public Button menuEditTasksButton;
    /**
     * The Menu adoption requests button.
     */
    public Button menuAdoptionRequestsButton;
    /**
     * The Menu settings button.
     */
    public Button menuSettingsButton;

    private LeftMenuPanelModel model;

    private int focused;

    private Button getButtonByIndex(int index){
        switch (index){
            case 0: return menuHomeButton;
            case 1: return menuAnimalsButton;
            case 2: return menuCatButton;
            case 3: return menuDogButton;
            case 4: return menuEditTasksButton;
            case 5: return menuAdoptionRequestsButton;
            case 6: return menuSettingsButton;
        }
        return null;
    }

    private final String focusedBackgroundStyle = "-fx-background-color: #FFE32D; -fx-border-color: #CF9B01; -fx-border-width: 1; -fx-border-style: hidden hidden solid hidden;";
    private final String unfocusedBackgroundStyle = "-fx-background-color: #FFCB31; -fx-border-color: #CF9B01; -fx-border-width: 1; -fx-border-style: hidden hidden solid hidden;";

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        model = new LeftMenuPanelModel();
        focused = 0;
        menuHomeButton.setStyle(focusedBackgroundStyle);
    }

    public void setToken(Token token){
        model.setToken(token);
    }

    /**
     * Change focus.
     *
     * @param i the
     */
    public void changeFocus(int i){
        if(getButtonByIndex(focused)!=null && getButtonByIndex(i)!=null){
            Objects.requireNonNull(getButtonByIndex(focused)).setStyle(unfocusedBackgroundStyle);
            Objects.requireNonNull(getButtonByIndex(i)).setStyle(focusedBackgroundStyle);
            focused = i;
            if(i!=1 && i!=2 && i!=3){
                menuVBox.getChildren().remove(2);
                menuVBox.getChildren().remove(2);
            }
        }
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
     * On menu home button action.
     *
     * @param e the e
     */
    public void onMenuHomeButtonAction(Event e){
        commonButtonAction(e,"./views/HomePage.fxml");
    }

    /**
     * On menu animals button action.
     *
     * @param e the e
     */
    public void onMenuAnimalsButtonAction(Event e){
        commonButtonAction(e,"./views/AnimalsPage.fxml");
    }

    /**
     * On menu cat button action.
     *
     * @param e the e
     */
    public void onMenuCatButtonAction(Event e){
        commonButtonAction(e,"./views/CatsPage.fxml");
    }

    /**
     * On menu dog button action.
     *
     * @param e the e
     */
    public void onMenuDogButtonAction(Event e){
        commonButtonAction(e,"./views/DogsPage.fxml");
    }

    /**
     * On menu edit tasks button action.
     *
     * @param e the e
     */
    public void onMenuEditTasksButtonAction(Event e){
        commonButtonAction(e,"./views/EditTasksPage.fxml");
    }

    /**
     * On menu adoption requests button action.
     *
     * @param e the e
     */
    public void onMenuAdoptionRequestsButtonAction(Event e){
        commonButtonAction(e,"./views/AdoptionRequestsPage.fxml");
    }

    /**
     * On menu settings button action.
     *
     * @param e the e
     */
    public void onMenuSettingsButtonAction(Event e){
        commonButtonAction(e,"./views/SettingsPage.fxml");
    }
    /*
    public void onMenuHomeButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/HomePage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        HomePageController homePageController = loader.<HomePageController>getController();
        homePageController.setToken(model.getToken());
        stage.show();
    }
    public void onMenuAnimalsButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/AnimalsPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AnimalsPageController animalPageController = loader.<AnimalsPageController>getController();
        animalPageController.setToken(model.getToken());
        stage.show();
    }
    public void onMenuCatButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/CatsPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        CatsPageController catsPageController = loader.<CatsPageController>getController();
        catsPageController.setToken(model.getToken());
        stage.show();
    }
    public void onMenuDogButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/CatsPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        DogsPageController dogsPageController = loader.<DogsPageController>getController();
        dogsPageController.setToken(model.getToken());
        stage.show();
    }
    public void onMenuEditTasksButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/CatsPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        EditTasksPageController editTasksPageController = loader.<EditTasksPageController>getController();
        editTasksPageController.setToken(model.getToken());
        stage.show();
    }
    public void onMenuAdoptionRequestsButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/CatsPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        AdoptionRequestsPageController adoptionRequestsPageController = loader.<AdoptionRequestsPageController>getController();
        adoptionRequestsPageController.setToken(model.getToken());
        stage.show();
    }
    public void onMenuSettingsButtonAction(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/CatsPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ioException) {
            System.out.println("File not found");
            System.exit(-1);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        SettingsPageController settingsPageController = loader.<SettingsPageController>getController();
        settingsPageController.setToken(model.getToken());
        stage.show();
    }

     */
}
