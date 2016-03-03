package webcam;

import javax.swing.*;

/**
 * Created by Pawel on 2016-03-02.
 */
public class SettingsFrame  {
    private JFormattedTextField breakTimeTextField;
    private JFormattedTextField delayTextField;
    private JLabel delayLabel;
    private JLabel breakTimeLabel;
    private JPanel settPanel;
    static final int xPosition = 30, yPosition = 30;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main() {
        JFrame frame = new JFrame("SettingsFrame");
        frame.setContentPane(new SettingsFrame().settPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
