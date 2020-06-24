package cse222.group8.desktop;

import cse222.group8.desktop.client.Client;
import cse222.group8.desktop.client.ConnectionError;
import cse222.group8.desktop.client.WrongPasswordException;
import cse222.group8.desktop.client.models.Token;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private Token token;
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
                    do {
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        try {
                            Client.login(city,town,shelter,password);
                        } catch (WrongPasswordException ignore) {
                            System.out.println("Wrong password");
                        }
                    }while();
                }
            }
        }
        login();
    }
    private void printMainMenu(){
        System.out.println("0: Get adoption requests");
        System.out.println("1: Approve adoption request");
        System.out.println("2: Capacity change request");
        System.out.println("3: Remove shelter request");
        System.out.println("4: Change name");
        System.out.println("5: Change password");
        System.out.println("6: Get most diseased animal");
        System.out.println("7: Add diseased animal");
        System.out.println("8: Cure most diseased animal");
        System.out.println("9: Logout");
        System.out.println("10: Quit");
    }
    public int getMenuChoice(){
        int choice = -1;
        while(choice<0||choice>10){
            printMainMenu();
            choice = scanner.nextInt();
        }
        return choice;
    }
    private void getAdoptionRequests(){

    }
    private void approveAdoptionRequest(){

    }
    private void capacityChangeRequest(){

    }
    private void removeShelterRequest(){

    }
    private void changeName(){

    }
    private void changePassword(){

    }
    private void getMostDiseasedAnimal(){

    }
    private void addDiseasedAnimal(){

    }
    private void cureMostDiseasedAnimal(){

    }
    private void logout(){

    }
    public void menu(){
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
                removeShelterRequest();
                return;
            case 4:
                changeName();
                return;
            case 5:
                changePassword();
                return;
            case 6:
                getMostDiseasedAnimal();
                return;
            case 7:
                addDiseasedAnimal();
                return;
            case 8:
                cureMostDiseasedAnimal();
                return;
            case 9:
                logout();
        }
    }

    public void run() {
        login();
        menu();
    }
}
