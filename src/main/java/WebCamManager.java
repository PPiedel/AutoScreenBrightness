import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebCamManager {

    public static void takeAPicture(){

            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(getViewSize(webcam));
            webcam.open();

            BufferedImage image = webcam.getImage();
            saveImage(image);

    }

    public static Dimension getViewSize(Webcam webcam) {
        return webcam.getViewSize();
    }

    public static boolean isWebCamDetected(){
        Webcam webcam = Webcam.getDefault();
        return webcam==null;
    }

    private static void saveImage(BufferedImage image) {
        try {
            ImageIO.write(image, "JPG", new File("test.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
