import org.apache.commons.lang3.SystemUtils;
import webcam.BrightnessManager;
import webcam.LinuxBrightnessManager;
import webcam.WindowsBrightnessManager;

import javax.swing.*;
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
