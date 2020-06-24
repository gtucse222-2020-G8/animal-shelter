package cse222.group8.desktop;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.WrongPasswordException;
import cse222.group8.desktop.client.models.AdoptionRequestData;
import cse222.group8.desktop.client.models.AnimalData;
import cse222.group8.desktop.client.models.GeneralShelterData;
import cse222.group8.desktop.client.models.Token;

import java.util.Scanner;

/**
 * The type Console uı.
 */
public class ConsoleUI {
    private Scanner scanner;
    private Token token;
    private String cityName;
    private String townName;
    private String shelterName;
    private GeneralShelterData generalShelterData;

    private boolean getYN(String message){
        System.out.print(message);
        String choice = scanner.nextLine();
        if(choice.equals("y")) return true;
        if(choice.equals("n")) return false;
        return getYN(message);
    }
    private void login() throws ConnectionError {
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        if(Client.cityExists(city)){
            System.out.print("Enter town: ");
            String town = scanner.nextLine();
            if(Client.townExists(city,town)){
                System.out.print("Enter shelter: ");
                String shelter = scanner.nextLine();
                if(Client.shelterExists(city,town,shelter)){
                    boolean tryAgain = false;
                    do {
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        try {
                            token = Client.login(city,town,shelter,password);
                            cityName = city;
                            townName = town;
                            shelterName = shelter;
                            return;
                        } catch (WrongPasswordException ignore) {
                            System.out.println("Wrong password");
                            tryAgain = getYN("Do you want to try again?(y/n): ");
                        }
                    }while(tryAgain);
                }
            }
        }
        login();
    }
    private void printMainMenu(){
        System.out.println("0: Get adoption requests");
        System.out.println("1: Approve adoption request");
        System.out.println("2: Capacity change request");
        System.out.println("3: Change name");
        System.out.println("4: Change password");
        System.out.println("5: Get most diseased animal");
        System.out.println("6: Add diseased animal");
        System.out.println("7: Cure most diseased animal");
        System.out.println("8: Logout");
        System.out.println("9: Quit");
    }

    private int getMenuChoice(){
        int choice = -1;
        while(choice<0||choice>9){
            printMainMenu();
            choice = scanner.nextInt();
        }
        return choice;
    }
    private void getAdoptionRequests() throws ConnectionError {
        AdoptionRequestData[] adoptionRequests = Client.getAdoptionRequests(token);
        System.out.println("Active Adoption Requests:");
        for(AdoptionRequestData adoptionRequest : adoptionRequests){
            System.out.println("ID: " + adoptionRequest.requestId + " Requester: " + adoptionRequest.requester +" Animal ID: "+adoptionRequest.animalId+" Animal Name: "+Client.getAnimal(token,adoptionRequest.animalId).name);
        }
    }
    private void approveAdoptionRequest() throws ConnectionError {
        getAdoptionRequests();
        System.out.println("Enter adoption request id:");
        Client.approveAdoption(token, scanner.nextInt());

    }
    private void capacityChangeRequest() throws ConnectionError {
        generalShelterData = Client.getGeneralShelterData(token);
        if(getYN("Do you want to change cat capacity?")) catCapacityChangeRequest();
        if(getYN("Do you want to change dog capacity?")) dogCapacityChangeRequest();
    }
    private void catCapacityChangeRequest() throws ConnectionError {
        System.out.println("Enter new capacity for the cats, current is"+generalShelterData.catCapacity+":");
        int newCapacity = scanner.nextInt();
        Client.updateCapacity(token,newCapacity,"cats");
    }
    private void dogCapacityChangeRequest() throws ConnectionError {
        System.out.println("Enter new capacity for the cats, current is"+generalShelterData.catCapacity+":");
        int newCapacity = scanner.nextInt();
        Client.updateCapacity(token,newCapacity,"dogs");
    }
    private void changeName() throws ConnectionError {
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        if(Client.shelterExists(cityName,townName,newName)){
            System.out.println("This shelter already exits.");
            return;
        }
        Client.updateName(token,newName);
        this.shelterName = newName;
        generalShelterData = Client.getGeneralShelterData(token);
    }
    private void changePassword() throws ConnectionError {
        System.out.print("Enter new password: ");
        String newPass = scanner.nextLine();
        Client.updatePassword(token, newPass);
        login();
    }
    private void getMostDiseasedAnimal() throws ConnectionError {
        AnimalData animal = Client.getMostDiseasedAnimal(token);
        System.out.print("Most diseased animal is: ");
        System.out.println("ID: "+animal.id+" Name "+animal.name+" Animal Type: "+animal.breed+ " Kind: "+animal.kind);
    }
    private void addDiseasedAnimal() throws ConnectionError {
        System.out.print("Diseased animal's id: ");
        int animalId = scanner.nextInt();
        System.out.print("Disease level:");
        int diseaseLevel = scanner.nextInt();
        Client.addDiseasedAnimal(token,animalId,diseaseLevel);
    }
    private void cureMostDiseasedAnimal() throws ConnectionError {
        Client.cureDiseasedAnimal(token);
    }
    private void logout() throws ConnectionError {
        token = null;
        run();
    }

    private void menu() throws ConnectionError {
        switch (getMenuChoice()){
            case 0:
                getAdoptionRequests();
                return;
            case 1:
                approveAdoptionRequest();
                return;
            case 2:
                capacityChangeRequest();
                return;
            case 3:
                changeName();
                return;
            case 4:
                changePassword();
                return;
            case 5:
                getMostDiseasedAnimal();
                return;
            case 6:
                addDiseasedAnimal();
                return;
            case 7:
                cureMostDiseasedAnimal();
                return;
            case 8:
                logout();
        }
    }

    public ConsoleUI(){
        scanner = new Scanner(System.in);
    }
    /**
     * Run.
     */
    public void run() throws ConnectionError {
        login();
        menu();
    }
}
