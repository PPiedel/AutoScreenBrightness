import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Kamil on 2016-02-28.
 */
public class MainController {
    private static final int BREAK_TIME = 2000;
    private static final int DELAY_TIME = 0;

    private WebCamManager webCamManager;
    private BrightnessManager brightnessManager;
    private boolean running = false;
    private BufferedImage image;
    private MainFrame mainFrame;

    MainController(){
        webCamManager = new WebCamManager();
        brightnessManager = new BrightnessManager();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
        if (running){
            startWorking();
        }
    }

    public void startWorking() {
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

    public void startGui() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame = new MainFrame();
                mainFrame.start();
            }
        });

    }
}

