package webcam;

import webcam.MainController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private ExecutorService exService;

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
                if (innerController.isRunning() == false){
                exService =Executors.newSingleThreadExecutor();
                exService.execute(innerController);
                }
            }

        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exService.shutdown();
                innerController.stopWorking();
            }
        });

        factorSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                innerController.setBrightnessFactor(factorSlider.getValue());
            }
        });
    }

    public void setBrightnessLabel(String value) {
        actualBrightnessLabel.setText(value);
    }


}
