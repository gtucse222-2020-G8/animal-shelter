package cse222.group8.desktop;

import cse222.group8.desktop.client.ConnectionError;

public class ConsoleMain {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        try {
            ui.run();
        } catch (ConnectionError connectionError) {
            System.out.println("Connection Error");
            System.exit(-1);
        }
    }
}
