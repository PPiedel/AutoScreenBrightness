package webcam;

import com.sun.glass.events.KeyEvent;

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
    JMenuBar menuBar;
    JMenu optionsMenu;
    JMenuItem breakTimeItem, delayItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;

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

        createOptionsMenuComponents();

        frame.setJMenuBar(menuBar);

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


    private void createOptionsMenuComponents() {
        menuBar = new JMenuBar();

        optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_A);
        optionsMenu.getAccessibleContext().setAccessibleDescription(
                "Options");

        breakTimeItem = new JMenuItem("Time interval",
                KeyEvent.VK_T);
        breakTimeItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        breakTimeItem.getAccessibleContext().setAccessibleDescription(
                "Set time breaks before image sampling.");
        optionsMenu.add(breakTimeItem);

        delayItem = new JMenuItem("Delay",KeyEvent.VK_T);
        breakTimeItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        breakTimeItem.getAccessibleContext().setAccessibleDescription(
                "set the time, which will elapse before new brightness setting.");
        optionsMenu.add(delayItem);

        menuBar.add(optionsMenu);

    }
}
