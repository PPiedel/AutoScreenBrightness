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
    }

    public void start() {
        JFrame frame = new JFrame("MainFrame");
        actualBrightnessLabel = new JLabel();
        actualBrightnessDesc = new JLabel();
        frame.setContentPane(new MainFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void setBrightnessLabel(String value) {
        actualBrightnessLabel.setText("Cokolwiek innego");
    }

}
