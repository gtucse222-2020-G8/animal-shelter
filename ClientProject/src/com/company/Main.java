package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static void printInnerMenu(){
        System.out.println("1-) Get ownership Request List");
        System.out.println("2-) Create a Animal Ownership Request");
        System.out.println("3-) Update User Info");
        System.out.println("4-) Get Favorite Pets");
        System.out.println("5-) Get Filtered Animals");
        System.out.println("6-) Get all Ownership Animals");
        System.out.println("7-) Back");
        System.out.println("8-) Quit");
    }

    private static void printParentMenu(){
        System.out.println("1-) Login");
        System.out.println("2-) Register");
        System.out.println("3-) Get Animal List");
        System.out.println("8-) Quit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menuType = 1;  //parent
        RequestHandler requestHandler = new RequestHandler();
        User currentUser = null;

        while(true){
            if(menuType == 1) printParentMenu();
            else printInnerMenu();
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            if(choice == 9){
                System.out.println("See you next time...");
                break;
            }

            if(menuType == 1 && (choice < 1 || choice > 3)){
                System.out.println("Invalid input, try again!");
            }
            else if (menuType == 2 && choice == 7)
                menuType = 1;

            else if(menuType == 2  && (choice < 1 || choice > 7)){
                System.out.println("Invalid input, try again!");
            }

            if(menuType == 1 && choice == 1){
                menuType = 2;
                System.out.print("Enter username: ");
                String userName = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();
                currentUser = new User(userName, password, "", "");
                try {
                     String token = requestHandler.Login(currentUser);
                     if(token.isEmpty()) System.out.println("Invalid account parameters");
                 }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }
            else if(menuType == 1 && choice == 2){
                System.out.print("Enter username: ");
                String userName = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();
                System.out.print("Enter mail address: ");
                String mail = scanner.next();
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.next();

                User user = new User(userName, password, phoneNumber, mail);
                try {
                    Boolean response = requestHandler.Register(user);
                    if(response) System.out.println("Server return with an error! Try again later");
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }

            else if(menuType == 1 && choice == 3){
                try {
                    String[] list = requestHandler.GetAllAnimalsByFilter(new Filter("",""));
                    if(list.length == 0) System.out.println("Server return with an error! Try again later");
                    else{
                        System.out.println(Arrays.toString(list));
                    }
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }

            else if(menuType == 2 && choice == 1){
                try {
                    String[] list = requestHandler.getAllOwnershipRequestList(currentUser);
                    if(list.length == 0) System.out.println("Server return with an error! Try again later");
                    else{
                        System.out.println(Arrays.toString(list));
                    }
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }

            else if(menuType == 2 && choice == 2){
                try {
                    System.out.print("Enter animal id: ");
                    String animalId = scanner.next();
                    Animal animal = new Animal();
                    animal.setId(animalId);
                    Boolean response = requestHandler.requestOwnership(currentUser, animal);
                    if(response) System.out.println("Server return with an error! Try again later");
                    else{
                        System.out.println("Request created successfully");
                    }
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }
            else if(menuType == 2 && choice == 3){
                try {

                    System.out.print("Enter new username: ");
                    String userName = scanner.next();
                    System.out.print("Enter new password: ");
                    String password = scanner.next();
                    System.out.print("Enter new mail address: ");
                    String mail = scanner.next();
                    System.out.print("Enter new phone number: ");
                    String phoneNumber = scanner.next();
                    User updatedUser = new User(userName, password, phoneNumber, mail);
                    Boolean response = requestHandler.updateUser(currentUser, updatedUser);
                    if(response) System.out.println("Server return with an error! Try again later");
                    else{
                        System.out.println("User update operation completed successfully");
                    }
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }
            else if(menuType == 2 && choice == 4){
                try {
                    System.out.print("Enter : ");
                    String userName = scanner.next();
                    System.out.print("Enter new password: ");
                    String password = scanner.next();
                    String[] response = requestHandler.GetFavoritePets(currentUser);
                    if(response.length == 0) System.out.println("Server return with an error! Try again later");
                    else{
                        System.out.println(Arrays.toString(response));
                    }
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }

            else if(menuType == 2 && choice == 5){
                try {
                    System.out.print("Enter city: ");
                    String city = scanner.next();
                    System.out.print("Enter town: ");
                    String town = scanner.next();
                    String[] response = requestHandler.GetAllAnimalsByFilter(new Filter(city, town));
                    if(response.length == 0) System.out.println("Server return with an error! Try again later");
                    else{
                        System.out.println(Arrays.toString(response));
                    }
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }

            else if(menuType == 2 && choice == 6){
                try {
                    String[] response = requestHandler.getAllOwnership(currentUser);
                    if(response.length == 0) System.out.println("Server return with an error! Try again later");
                    else{
                        System.out.println(Arrays.toString(response));
                    }
                }catch (ConnectionError er){
                    System.out.println("Error occurred while connecting to the server");
                }
            }
        }
    }
}
