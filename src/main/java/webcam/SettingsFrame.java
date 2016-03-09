package webcam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pawel on 2016-03-02.
 */
public class SettingsFrame  {
    private JFormattedTextField brightnessTextField;
    private JFormattedTextField delayTextField;
    private JLabel delayLabel;
    private JLabel breakTimeLabel;
    private JPanel settPanel;
    private JButton okButton;

    public static void createUIComponents() {
        JFrame frame = new JFrame("SettingsFrame");
        frame.setContentPane(new SettingsFrame().settPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void addActionListeners(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int brightness = Integer.parseInt(brightnessTextField.getText());
                System.out.println(brightness);
                int delay = Integer.parseInt(delayTextField.getText());
                System.out.println(delay);
                util.Utilities.saveSettings(brightness,delay);

            }
        });

    }
}
