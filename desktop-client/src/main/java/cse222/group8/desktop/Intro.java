package cse222.group8.desktop;

import javax.swing.*;

public class Intro {
    private JPanel MainPanel;
    private JComboBox<String> CitySelector;
    private JComboBox<String> TownSelector;
    private JTextField ShelterName;
    private JButton Submit;
    private JLabel AppLogo;
    Intro(){}
    public JPanel getMainPanel() {
        return MainPanel;
    }
}
