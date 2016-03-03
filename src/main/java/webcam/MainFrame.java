package webcam;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Pawel on 2016-02-28.
 */
public class MainFrame {
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem settingsItem;

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

        addActionListeners();
    }

    private void addActionListeners() {
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!innerController.isRunning()){
                exService = Executors.newSingleThreadExecutor();
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

        settingsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createSettingFrame();
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

        settingsItem = new JMenuItem("Settings",
                KeyEvent.VK_T);
        settingsItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        settingsItem.getAccessibleContext().setAccessibleDescription(
                "Settings");
        settingsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new SettingsFrame().main();
            }
        });

        optionsMenu.add(settingsItem);

        menuBar.add(optionsMenu);

    }

    protected void createSettingFrame() {
        SettingsFrame frame = new SettingsFrame();
    }
}
