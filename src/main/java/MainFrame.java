import javax.swing.*;

/**
 * Created by Pawel on 2016-02-28.
 */
public class MainFrame {
    private JButton startButton;
    private JButton stopButton;
    private JLabel actualBrightnessLabel;
    private JPanel mainPanel;
    private JLabel actualBrightnessDesc;

    public MainFrame() {
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void start() {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new MainFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setBrightnessLabel (String label){
        actualBrightnessLabel.setText(label);
    }

}
