package webcam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pawel on 2016-03-02.
 */
public class SettingsFrame  {
    private JFormattedTextField breakTimeField;
    private JFormattedTextField delayField;
    private JLabel delayLabel;
    private JLabel breakTimeLabel;
    private JPanel settPanel;
    private JButton okButton;
    private static JFrame frame = new JFrame("SettingsFrame");

    public SettingsFrame() {
        addActionListeners();
    }

    public static void createUIComponents() {
        frame.setContentPane(new SettingsFrame().settPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void addActionListeners(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int breakTime = Integer.parseInt(breakTimeField.getText());
                System.out.println(breakTime);
                int delay = Integer.parseInt(delayField.getText());
                System.out.println(delay);
                Settings.saveSettings(breakTime,delay);
                frame.dispose();
            }
        });

    }
}
