package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.models.AnimalData;
import cse222.group8.desktop.client.models.AnimalDataWithImage;
import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AddCatPageModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddCatPageController implements PageWithTokenController {
    public TextField nameField;
    public ComboBox<Character> genderCombo;
    public ComboBox<String> ageCombo;
    public CheckBox neuteredCheck;
    public TextArea vacInfoField;
    public TextArea infoField;
    public TextField speciesField;
    public ImageView animalImageView;
    public Button addChangeImageButton;
    public Button updateButton;
    public VBox leftMenu;

    @FXML public LeftMenuPanelController leftMenuController;

    private AddCatPageModel model;

    @FXML
    public void initialize(){
        model = new AddCatPageModel();
        leftMenuController.changeFocus(1);
        List<Character> genders = new ArrayList<>(2);
        genders.add('m');
        genders.add('f');
        genderCombo.setItems(FXCollections.observableArrayList(genders));
        List<String> ages = new ArrayList<>(4);
        ages.add("<1");
        ages.add("1-3");
        ages.add("3-5");
        ages.add("+5");
        ageCombo.setItems(FXCollections.observableArrayList(ages));
        genderCombo.valueProperty().addListener((observableValue, character, t1) -> {
            model.setGender(t1);
        });
        ageCombo.valueProperty().addListener(((observableValue, s, t1) -> {
            model.setAge(t1);
        }));
        genderCombo.valueProperty().setValue(genders.get(0));
        ageCombo.valueProperty().setValue(ages.get(0));
        nameField.textProperty().addListener((observableValue, s, t1) -> {
            model.setName(t1);
        });
        neuteredCheck.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            model.setNeutered(t1);
        });
        vacInfoField.textProperty().addListener((observableValue, s, t1) -> {
            model.setVaccination(t1);
        });
        infoField.textProperty().addListener((observableValue, s, t1) -> {
            model.setInfo(t1);
        });
        speciesField.textProperty().addListener((observableValue, s, t1) -> {
            model.setKind(t1);
        });
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }

    public void setAnimalID(int id) {
        try {
            AnimalDataWithImage data = Client.getAnimal(model.getToken(),id);
            model.setAnimalData(data);
        } catch (ConnectionError connectionError) {
            System.out.println("Connection Error");
            System.exit(-1);
        }
    }

    public void onAddChangeImageButton(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        List<String> extensions = new ArrayList<>(3);
        extensions.add("*.jpg");
        extensions.add("*.png");
        extensions.add("*.bmp");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",extensions));
        File selectedFile = fileChooser.showOpenDialog(stage);
        try{
            FileInputStream imageInFile = new FileInputStream(selectedFile);
            byte[] imageData = new byte[(int) selectedFile.length()];
            imageInFile.read(imageData);
            Image image = new Image(imageInFile);
            animalImageView.setImage(image);
            model.setImage(Base64.getEncoder().encodeToString(imageData));
            if(model.isEditing()){
                Client.updateAnimalPicture(model.getToken(),model.getAnimalId(),model.getImage());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"File Not Found", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } catch (IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"File Cannot Be Opened", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } catch (ConnectionError connectionError) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Connection Error", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        }
    }
    private boolean checkFields() {
        boolean valid = true;
        if(model.getName()==null || model.getName().length()<1){
            nameField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            nameField.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getAge()==null){
            ageCombo.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            ageCombo.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getGender()==null){
            genderCombo.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            genderCombo.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getVaccination()==null || model.getVaccination().length()<1){
            vacInfoField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            vacInfoField.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getInfo()==null || model.getInfo().length()<1){
            infoField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            infoField.setStyle("-fx-background-color: #FFFFFF;");
        }
        if(model.getKind()==null || model.getKind().length()<1){
            speciesField.setStyle("-fx-background-color: #FF4444;");
            valid = false;
        }
        else{
            speciesField.setStyle("-fx-background-color: #FFFFFF;");
        }
        return valid;
    }
    public void onUpdateButton(Event e){
        Node node=(Node) e.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        if(checkFields()) {
            try {
                if (model.isEditing()) {
                    Client.updateAnimalData(model.getToken(), model.getAnimalData());
                } else {
                    Client.addCat(model.getToken(),model.getAnimalData(),model.getImage());
                }
            } catch (ConnectionError connectionError) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Connection Error", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./views/HomePage.fxml"));
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
    }
}
