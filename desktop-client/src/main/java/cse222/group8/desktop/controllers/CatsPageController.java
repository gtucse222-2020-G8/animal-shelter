package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.models.AnimalDataWithImage;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.CatsPageModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * The type Cats page controller.
 */
public class CatsPageController implements PageWithTokenController {
    /**
     * The Grid pane.
     */
    public GridPane gridPane;
    /**
     * The Left menu.
     */
    public VBox leftMenu;
    private CatsPageModel model;
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
        model = new CatsPageModel();
        leftMenuController.changeFocus(2);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
        try {
            AnimalDataWithImage[] animalData = Client.getCats(token);
            System.out.println(animalData.length);
            if(!(animalData.length <1)){
                for(int i = 0; i<(animalData.length/3)-1;++i){
                    RowConstraints rowConstraints = new RowConstraints();
                    // <RowConstraints minHeight="260.0" prefHeight="260.0" vgrow="ALWAYS" />
                    rowConstraints.setMinHeight(260.0);
                    rowConstraints.setPrefHeight(260.0);
                    rowConstraints.setVgrow(Priority.ALWAYS);
                    gridPane.getRowConstraints().add(rowConstraints);
                }
                int i=0;
                for(AnimalDataWithImage data : animalData){
                    gridPane.add(createAnimalGridComponent(data),i%3,i/3);
                    ++i;
                }
            }
        } catch (ConnectionError connectionError) {
            System.out.println("Connection Error");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("File Not Found");
            System.exit(-1);
        }
    }
    private Node createAnimalGridComponent(AnimalDataWithImage data) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        StackPane node = loader.load(getClass().getClassLoader().getResource("./views/AnimalGridComponent.fxml").openStream());
        //FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/AnimalGridComponent.fxml"));
        //StackPane node = loader.load();
        node.setOnMouseClicked(mouseEvent -> {
            Node _node=(Node) mouseEvent.getSource();
            Stage stage=(Stage) _node.getScene().getWindow();
            FXMLLoader _loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/AddCatPage.fxml"));
            Parent root = null;
            try {
                root = _loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("File not found");
                System.exit(-1);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            AddCatPageController controller = _loader.<AddCatPageController>getController();
            controller.setToken(model.getToken());
            controller.setAnimalID(data.id);
            stage.show();
        });
        VBox vbox = (VBox) node.getChildren().get(0);
        List<Node> children = vbox.getChildren();
        ImageView imageView = (ImageView) children.get(0);
        byte[] img = Base64.getDecoder().decode(data.image);
        ByteArrayInputStream bais = new ByteArrayInputStream(img);
        Image image = new Image(bais);
        imageView.setImage(image);
        Text breed = (Text) children.get(1);
        breed.setText(data.name);
        Text age = (Text) children.get(2);
        age.setText(String.valueOf(data.age));
        Text date = (Text) children.get(3);
        date.setText(data.date);
        return node;
    }
}
