import org.apache.commons.lang3.SystemUtils;

import javax.swing.*;
import javax.swing.UIManager.*;
public class Main {
    public static void main(String[] args) {
        if(SystemUtils.IS_OS_WINDOWS) {

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


            MainController mainController = new MainController();
            mainController.startGui();
        }
    }
}
