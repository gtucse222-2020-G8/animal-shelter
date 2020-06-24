package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.models.AnimalDataWithImage;
import cse222.group8.desktop.client.models.TaskData;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.EditTasksPageModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The type Edit tasks page controller.
 */
public class EditTasksPageController implements PageWithTokenController {
    /**
     * The Add task button.
     */
    public Button addTaskButton;
    /**
     * The Left menu.
     */
    public VBox leftMenu;
    /**
     * The Daily tasks v box.
     */
    public VBox dailyTasksVBox;
    private EditTasksPageModel model;
    /**
     * The Left menu controller.
     */
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
        try {
            TaskData[] data = Client.getTasks(token);
            for(TaskData td:data){
                dailyTasksVBox.getChildren().add(createTaskListComponent(td));
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
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/EditTasksPage.fxml"));
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
     * On add task button action.
     *
     * @param e the e
     */
    public void onAddTaskButtonAction(Event e){
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText(null);
        td.setGraphic(null);
        td.setContentText("Enter new task");
        td.showAndWait();
        String text = td.getEditor().getText();
        if(text.length()<3){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid text", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        }
        else{
            TaskData newTask = new TaskData();
            newTask.text = text;
            newTask.status = false;
            newTask.id = -1;
            try {
                Client.createTask(model.getToken(),newTask);
            } catch (ConnectionError connectionError) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Connection Error", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            }
        }
    }
    private Node createTaskListComponent(TaskData data) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        HBox node = loader.load(getClass().getClassLoader().getResource("./views/DailyTasksListComponent.fxml").openStream());
        //FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/AnimalGridComponent.fxml"));
        //StackPane node = loader.load();
        Label taskText = (Label) node.getChildren().get(0);
        taskText.setText(data.text);
        CheckBox taskCheck = (CheckBox) node.getChildren().get(1);
        taskCheck.selectedProperty().setValue(data.status);
        taskCheck.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            try {
                Client.updateTask(model.getToken(),data);
                data.status = t1;
            } catch (ConnectionError connectionError) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Connection Error", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
                taskCheck.selectedProperty().setValue(aBoolean);
            }
        });
        Button taskEditButton = (Button) node.getChildren().get(2);
        taskEditButton.setOnAction(e -> {
            TextInputDialog td = new TextInputDialog();
            td.setContentText("Enter new text");
            td.setHeaderText(null);
            td.setGraphic(null);
            td.getEditor().setText(data.text);
            td.showAndWait();
            try {
                Client.updateTask(model.getToken(),data);
                data.text = td.getEditor().getText();
                taskText.setText(data.text);
            } catch (ConnectionError connectionError) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Connection Error", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            }
        });
        Button taskDeleteButton = (Button) node.getChildren().get(3);
        taskDeleteButton.setOnAction(e -> {
            try {
                Client.deleteTask(model.getToken(),data.id);
                reload(e);
            } catch (ConnectionError connectionError) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Connection Error", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            }
        });
        return node;
    }
}
