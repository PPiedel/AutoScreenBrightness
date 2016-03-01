import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Pawel on 2016-02-28.
 */
public class MainFrame {
    private JButton startButton;
    private JButton stopButton;
    private JLabel actualBrightnessLabel;
    private JPanel mainPanel;
    private JLabel actualBrightnessDesc;
    private JSlider factorSlider;
    private MainController.InnerController innerController;
    private final ExecutorService exService = Executors.newSingleThreadExecutor();

    public MainFrame(MainController.InnerController innerController) {
        this.innerController = innerController;
    }

    public void start() {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exService.submit(innerController);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                innerController.stopWorking();
            }
        });

    }

    public void setBrightnessLabel(String value) {
        actualBrightnessLabel.setText(value);
    }


}
