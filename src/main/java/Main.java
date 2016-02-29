import org.apache.commons.lang3.SystemUtils;

public class Main {
    public static void main(String[] args) {
        if(SystemUtils.IS_OS_WINDOWS) {
            MainController mainController = new MainController();
            mainController.startGui();
            mainController.startWorking();
        }
    }
}
