package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.NotFound;
import cse222.group8.desktop.client.models.AdoptionRequestData;
import cse222.group8.desktop.client.models.TaskData;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AdoptionRequestsPageModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

/**
 * The type Adoption requests page controller.
 */
public class AdoptionRequestsPageController implements PageWithTokenController {
    /**
     * The Adoption requests list v box.
     */
    public VBox adoptionRequestsListVBox;
    /**
     * The Left menu.
     */
    public VBox leftMenu;
    /**
     * The Model.
     */
    AdoptionRequestsPageModel model;
    /**
     * The Left menu controller.
     */
    @FXML
    public LeftMenuPanelController leftMenuController;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        model = new AdoptionRequestsPageModel();
        leftMenuController.changeFocus(5);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
        try {
            AdoptionRequestData[] adoptionRequestData = Client.getAdoptionRequests(model.getToken());
            for(AdoptionRequestData data: adoptionRequestData){
                adoptionRequestsListVBox.getChildren().add(createAdoptionRequestListComponent(data));
            }
        } catch (ConnectionError connectionError) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Connection Error", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
    }
    private void reload(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/AdoptionRequestsPage.fxml"));
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
    private Node createAdoptionRequestListComponent(AdoptionRequestData data) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        HBox node = loader.load(getClass().getClassLoader().getResource("./views/AdoptionRequestListComponent.fxml").openStream());
        ImageView imageView = (ImageView) node.getChildren().get(0);
        byte[] img = Base64.getDecoder().decode(data.image);
        ByteArrayInputStream bais = new ByteArrayInputStream(img);
        imageView.setImage(new Image(bais));
        Label label = (Label) node.getChildren().get(1);
        label.setText(data.requester);
        Button button = (Button) node.getChildren().get(2);
        button.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("You are approving an adoption request.");
            alert.setContentText("Are you sure?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.YES) {
                try {
                    Client.approveAdoption(model.getToken(), data.requestId);
                } catch (ConnectionError connectionError) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Connection Error", ButtonType.OK);
                    alert1.showAndWait();
                    if (alert1.getResult() == ButtonType.OK) {
                        alert1.close();
                    }
                } catch (NotFound notFound) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Something went wrong, adoption request not found", ButtonType.OK);
                    alert1.showAndWait();
                    if (alert1.getResult() == ButtonType.OK) {
                        alert1.close();
                    }
                }
                reload(e);
            }
        });
        return node;
    }
}
