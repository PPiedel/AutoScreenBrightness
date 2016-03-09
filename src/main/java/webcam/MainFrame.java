package webcam;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
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
        JFrame frame = new JFrame("AutoBrightness");
        frame.setContentPane(mainPanel);
        ImageIcon img = new ImageIcon("src/resources/sun.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        createOptionsMenuComponents();
        frame.setJMenuBar(menuBar);

        //Listener when minimize button clicked
        /*frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent arg0) {
                if (arg0.getNewState() == Frame.ICONIFIED) {
                    // do stuff
                }
            }
        });*/

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
                new SettingsFrame().createUIComponents();
            }
        });

        optionsMenu.add(settingsItem);

        menuBar.add(optionsMenu);

    }

    protected void createSettingFrame() {
        SettingsFrame frame = new SettingsFrame();
    }
}
