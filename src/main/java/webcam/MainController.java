package webcam;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Kamil on 2016-02-28.
 */
public class MainController {
    private InnerController innerController;
    private MainFrame mainFrame;
    private BrightnessManager brightnessManager;
    private BufferedImage image;
    private WebCamManager webCamManager;
    private Settings settings = new Settings();

    public MainController(BrightnessManager brightnessManager){
        this.brightnessManager = brightnessManager;
        innerController = new InnerController();
    }

    //Inner class
    public class InnerController implements Runnable{
        private boolean running = false;

        InnerController(){
            webCamManager = new WebCamManager();
        }

        public void run() {
            running=true;
            while (running) {
                image = webCamManager.getCameraImage();
                int calculatedBrightness = brightnessManager.calculateLuminance(image);
                mainFrame.setBrightnessLabel(Integer.toString(calculatedBrightness));
                try {
                    brightnessManager.setBrightness(calculatedBrightness, settings.getDelay());
                    Thread.sleep(settings.getBreakTime());
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

        public boolean isRunning(){
            return running;
        }

        public void setBrightnessFactor(int val){
            double factor = (2.55*2)*((double)val/100); //50% slider is neutral
            brightnessManager.setBrightnessFactor(5.1-factor);
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

