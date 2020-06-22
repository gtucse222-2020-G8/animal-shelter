package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.AnimalDataWithImage;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.EditTasksPageModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class EditTasksPageController implements PageWithTokenController {
    public Button addTaskButton;
    public VBox adoptionRequestListVBox;
    public VBox leftMenu;
    private EditTasksPageModel model;
    @FXML
    public LeftMenuPanelController leftMenuController;

    @FXML
    private void initialize(){
        model = new EditTasksPageModel();
        leftMenuController.changeFocus(4);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
    private Node createAnimalGridComponent(AnimalDataWithImage data) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        HBox node = loader.load(getClass().getClassLoader().getResource("./views/DailyTasksListComponent.fxml").openStream());
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
        Vbox vbox = (VBox) node.getChildren().get(0);
        List<Node> children = vbox.getChildren();
        ImageView imageView = (ImageView) children.get(0);
        byte[] img = Base64.getDecoder().decode(data.image);
        ByteArrayInputStream bais = new ByteArrayInputStream(img);
        Image image = new Image(bais);
        imageView.setImage(image);
        Text breed = (Text) children.get(1);
        breed.setText(data.name);
        Text age = (Text) children.get(2);
        age.setText(data.age);
        Text date = (Text) children.get(3);
        date.setText(data.date);
        return node;
    }
}
