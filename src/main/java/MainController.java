import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Kamil on 2016-02-28.
 */
public class MainController {
    //Outer class
    private static final int BREAK_TIME = 2000;
    private static final int DELAY_TIME = 0;
    private boolean running = false;
    private InnerController innerController;
    public boolean isRunning() {
        return running;
    }
    private MainFrame mainFrame;
    private BrightnessManager brightnessManager;
    private BufferedImage image;
    private WebCamManager webCamManager;

    MainController(){
        innerController = new InnerController();
    }

    //Inner class
    public class InnerController implements Runnable{
        public void run() {
            running = true;
            webCamManager = new WebCamManager();
            brightnessManager = new BrightnessManager();
            while (running) {
                image = webCamManager.getCameraImage();
                int calculatedBrightness = brightnessManager.calculateLuminance(image);
                mainFrame.setBrightnessLabel(Integer.toString(calculatedBrightness));
                try {
                    brightnessManager.setBrightness(calculatedBrightness, DELAY_TIME);
                    Thread.sleep(BREAK_TIME);
                } catch (BrightnessSettingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopWorking() {
            running = false;
        }
    }

    //Outer controller
    public void startGui() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame = new MainFrame(innerController);
                mainFrame.start();
            }
        });

    }
}

