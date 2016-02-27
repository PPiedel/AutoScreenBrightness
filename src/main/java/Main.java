import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WebCamManager webCamManager = new WebCamManager();
        BrightnessManager brightnessManager = new BrightnessManager();
        BufferedImage image;

        boolean running = true;
        while (running){
            int i=0;
            image = webCamManager.getCameraImage();
            int calculatedBrightness =  brightnessManager.calculateLuminance(image);
            System.out.println("Calculated brightness " + i + " :  "+calculatedBrightness);
            try {
                brightnessManager.setBrightness(calculatedBrightness, 0);
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }







    }
}
