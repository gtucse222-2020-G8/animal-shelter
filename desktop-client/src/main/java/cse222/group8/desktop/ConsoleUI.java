package cse222.group8.desktop;

import cse222.group8.desktop.client.models.Token;

public class ConsoleUI {
    Token token;
    private void login(){

    }
    private void printMainMenu(){
        System.out.println("0: Get adoption requests");
        System.out.println("1: Approve adoption requests");
        System.out.println("2: Capacity change request");
        System.out.println("3: Remove shelter request");
        System.out.println("4: Capacity change request");
        System.out.println("5: Change name");
        System.out.println("6: Change password");
        System.out.println("7: Change name");
        System.out.println("8: Get most diseased animal");
        System.out.println("9: Add diseased animal");
        System.out.println("10: Cure most diseased animal");
    }
    public int getMenuChoice(){
        int choice = -1;
        while(choice<0||choice>4){
            printMainMenu();
        }
    }
}
