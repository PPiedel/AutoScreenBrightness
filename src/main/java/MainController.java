import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Kamil on 2016-02-28.
 */
public class MainController {
    private static MainFrame mainFrame;

    public void startWorking() {

        WebCamManager webCamManager = new WebCamManager();
        BrightnessManager brightnessManager = new BrightnessManager();
        BufferedImage image;

        boolean running = true;
        int i = 0;
        while (running)

        {
            image = webCamManager.getCameraImage();

            /*File outputfile = new File("saved.png");
            try {
                ImageIO.write(image, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            int calculatedBrightness = brightnessManager.calculateLuminance(image);
            System.out.println("Calculated brightness " + i + " :  " + calculatedBrightness);
            mainFrame.setBrightnessLabel(new Integer(calculatedBrightness).toString());
            i++;
            try {
                brightnessManager.setBrightness(calculatedBrightness, 0);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGui(){
        mainFrame = new MainFrame();
        mainFrame.start();
    }
}

