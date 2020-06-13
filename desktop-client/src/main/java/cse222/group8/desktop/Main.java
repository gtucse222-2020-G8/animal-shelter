package cse222.group8.desktop;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        System.out.println("Hebebe");
        AddAnimalScreen log = new AddAnimalScreen();
        System.out.println("Hebebe");
        frame.setContentPane(log.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
