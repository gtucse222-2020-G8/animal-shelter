package cse222.group8.desktop.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The type Select shelter page model.
 */
public class SelectShelterPageModel {
    private final ObservableList<String> cityList = FXCollections.observableArrayList();
    private final ObservableList<String> townList = FXCollections.observableArrayList();

    private String currentCity;
    private String currentTown;
    private String currentShelterName;

    /**
     * Gets city list.
     *
     * @return the city list
     */
    public ObservableList<String> getCityList() {
        return cityList;
    }

    /**
     * Gets town list.
     *
     * @return the town list
     */
    public ObservableList<String> getTownList() {
        return townList;
    }

    /**
     * Gets current city.
     *
     * @return the current city
     */
    public String getCurrentCity() {
        return currentCity;
    }

    /**
     * Gets current shelter name.
     *
     * @return the current shelter name
     */
    public String getCurrentShelterName() {
        return currentShelterName;
    }

    /**
     * Gets current town.
     *
     * @return the current town
     */
    public String getCurrentTown() {
        return currentTown;
    }

    /**
     * Sets current city.
     *
     * @param currentCity the current city
     */
    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    /**
     * Sets current shelter name.
     *
     * @param currentShelterName the current shelter name
     */
    public void setCurrentShelterName(String currentShelterName) {
        this.currentShelterName = currentShelterName;
    }

    /**
     * Sets current town.
     *
     * @param currentTown the current town
     */
    public void setCurrentTown(String currentTown) {
        this.currentTown = currentTown;
    }
}
