import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Kamil on 2016-02-28.
 */
public class MainController {
    private static final int BREAK_TIME = 2000;
    private static final int DELAY_TIME = 0;

    private MainFrame mainFrame;



    public void startWorking() {
        WebCamManager webCamManager = new WebCamManager();
        BrightnessManager brightnessManager = new BrightnessManager();
        BufferedImage image;

        boolean running =  true;
        while (running) {
            image = webCamManager.getCameraImage();
            int calculatedBrightness = brightnessManager.calculateLuminance(image);
            mainFrame.setBrightnessLabel(Integer.toString(calculatedBrightness));
            try {
                brightnessManager.setBrightness(calculatedBrightness, DELAY_TIME);
                Thread.sleep(BREAK_TIME);
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

    public void stopWorking(){

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

