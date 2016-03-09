package webcam;
import org.apache.commons.lang3.SystemUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        /** Nimbus look **/
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        /** Tray icon **/
        if (SystemTray.isSupported()) {
            final Toolkit toolkit = Toolkit.getDefaultToolkit();
            final SystemTray tray = SystemTray.getSystemTray();
            Image image = toolkit.getImage("src/resources/sun.png");
            PopupMenu menu = new PopupMenu();

            MenuItem closeItem = new MenuItem("Close");
            closeItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            menu.add(closeItem);
            final TrayIcon icon = new TrayIcon(image, "AutoBrightness", menu);
            icon.setImageAutoSize(true);

            try {
                tray.add(icon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

        MainController mainController= null;
        if(SystemUtils.IS_OS_WINDOWS) {
            BrightnessManager brightnessManager = new WindowsBrightnessManager();
            mainController = new MainController(brightnessManager);
        } else if(SystemUtils.IS_OS_UNIX){
            BrightnessManager brightnessManager = new LinuxBrightnessManager();
            mainController = new MainController(brightnessManager);
        } else {
            System.out.println("OS not found");
            System.exit(0);
        }
        mainController.startGui();
    }
}
