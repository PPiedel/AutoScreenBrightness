package webcam;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Pawel on 2016-02-26.
 */
public abstract class BrightnessManager {
    private double brightnessFactor = 2.55;

    public abstract void setBrightness(int brightness, int delay) throws IOException, BrightnessSettingException, InterruptedException;

    public abstract String createExecCommand(int brightness, int delay) throws IOException, InterruptedException;

    public int calculateLuminance(BufferedImage image) {
        float sumOfBrightness = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                float red = (pixel >> 16) & 0xff;
                float green = (pixel >> 8) & 0xff;
                float blue = (pixel) & 0xff;

                //sumOfBrightness+= Math.sqrt(red*red*0.299 + green*green*0.587 + blue*blue*0.114);
                sumOfBrightness += (red * 299 + green * 587 + blue * 114) / 1000;    //W3C algo
            }
        }
        //System.out.println("Sum : "+sumOfBrightness);
        float percentBrightness = (float) ((sumOfBrightness / brightnessFactor) / (image.getHeight() * image.getWidth()));
        if(percentBrightness >= 100) return 100;
        else return (int) percentBrightness;
    }

    public void setBrightnessFactor(double brightnessFactor) {
        this.brightnessFactor = brightnessFactor;
    }
}
