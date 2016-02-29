import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pawel on 2016-02-28.
 */
public class MainFrame {
    private JButton startButton;
    private JButton stopButton;
    private JLabel actualBrightnessLabel;
    private JPanel mainPanel;
    private JLabel actualBrightnessDesc;

    public MainFrame() {}

    private void createUIComponents() {
    }

    public void start() {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void setBrightnessLabel(String value) {
        actualBrightnessLabel.setText(value);
    }


}
