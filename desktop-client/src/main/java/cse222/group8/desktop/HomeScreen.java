package cse222.group8.desktop;

import javax.swing.*;

public class HomeScreen {
    private TopBarPanel topBarPanel1;
    private JPanel MainPanel;
    private LeftMenuPanel leftMenuPanel1;
    private DailyTaskPanel dailyTaskPanel1;
    private SummaryPanel summaryPanel1;

    public JPanel getMainPanel(){
        return MainPanel;
    }

    private void createUIComponents() {
        dailyTaskPanel1 = new DailyTaskPanel();
        topBarPanel1 = new TopBarPanel();
        leftMenuPanel1 = new LeftMenuPanel();
        summaryPanel1 = new SummaryPanel();
    }
}
