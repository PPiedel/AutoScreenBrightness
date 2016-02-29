import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Kamil on 2016-02-28.
 */
public class MainController {
    private MainFrame mainFrame;

    public void startWorking() {
        WebCamManager webCamManager = new WebCamManager();
        BrightnessManager brightnessManager = new BrightnessManager();
        BufferedImage image;

        boolean running = true;
        int i = 0;
        while (running) {
            image = webCamManager.getCameraImage();
            int calculatedBrightness = brightnessManager.calculateLuminance(image);
            //System.out.println("Calculated brightness " + i + " :  " + calculatedBrightness);
            mainFrame.setBrightnessLabel(Integer.toString(calculatedBrightness));
            i++;
            try {
                brightnessManager.setBrightness(calculatedBrightness, 0);
                Thread.sleep(2000);
            }catch (BrightnessSettingException e){
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();}
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGui(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame = new MainFrame();
                mainFrame.start();
            }
        });

    }
}

