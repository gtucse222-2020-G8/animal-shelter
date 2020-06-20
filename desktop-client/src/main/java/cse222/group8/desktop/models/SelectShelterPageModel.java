package cse222.group8.desktop.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelectShelterPageModel {
    private final ObservableList<String> cityList = FXCollections.observableArrayList();
    private final ObservableList<String> townList = FXCollections.observableArrayList();

    private String currentCity;
    private String currentTown;
    private String currentShelterName;

    public ObservableList<String> getCityList() {
        return cityList;
    }
    public ObservableList<String> getTownList() {
        return townList;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public String getCurrentShelterName() {
        return currentShelterName;
    }

    public String getCurrentTown() {
        return currentTown;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public void setCurrentShelterName(String currentShelterName) {
        this.currentShelterName = currentShelterName;
    }

    public void setCurrentTown(String currentTown) {
        this.currentTown = currentTown;
    }
}
