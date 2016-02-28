import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WebCamManager webCamManager = new WebCamManager();
        BrightnessManager brightnessManager = new BrightnessManager();
        BufferedImage image;

        boolean running = true;
        int i=0;
        while (running){
            image = webCamManager.getCameraImage();

            /*File outputfile = new File("saved.png");
            try {
                ImageIO.write(image, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            int calculatedBrightness =  brightnessManager.calculateLuminance(image);
            System.out.println("Calculated brightness " + i + " :  "+calculatedBrightness);
            i++;
            try {
                brightnessManager.setBrightness(calculatedBrightness, 0);
                Thread.sleep(2000);
            }catch (BrightnessSettingException e){
                System.err.println("Standard Error:");
                System.err.println(e.getMessage());
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }







    }
}
