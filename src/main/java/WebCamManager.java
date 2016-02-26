import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebCamManager {
    Webcam webcam;

    WebCamManager(){
        webcam = Webcam.getDefault();
    }

    public boolean takeAPicture(){
        if(isWebCamDetected()){
            webcam = Webcam.getDefault();
            setViewSize();
            webcam.open();

            BufferedImage image = webcam.getImage();
            saveImage(image);

            return true;

        }
        else {
            return false;
        }


    }

    private void setViewSize() {
        webcam.setViewSize(webcam.getViewSize());
    }

    private boolean isWebCamDetected(){
        return webcam==null;
    }

    private void saveImage(BufferedImage image) {
        try {
            ImageIO.write(image, "PNG", new File("test.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
