import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class WebCamManager {
    private final String IMAGE_FILE_FORMAT = "bmp";

    Webcam webcam;

    WebCamManager(){
        webcam = Webcam.getDefault();
    }

    public byte[] getCameraImageBitmap() {
        setCameraResolution();
        webcam.open();

        BufferedImage image = webcam.getImage();
        webcam.close();

        saveImageOnDisk(image);

        return getBitmapFromImage(image);
    }

    private void setCameraResolution() {
        Dimension[] nonStandardResolutions = new Dimension[] {
                WebcamResolution.PAL.getSize(),
                WebcamResolution.HD720.getSize(),
                new Dimension(2000, 1000),
                new Dimension(1000, 500),
        };
        webcam.setCustomViewSizes(nonStandardResolutions);
        webcam.setViewSize(WebcamResolution.HD720.getSize());
    }

    private Dimension getViewSize(Webcam webcam) {
        return webcam.getViewSize();
    }

    private boolean isWebCamDetected() {
        return webcam == null;
    }

    private void saveImageOnDisk(BufferedImage image) {
        try {
            ImageIO.write(image, IMAGE_FILE_FORMAT, new File("ASBTest" + Utilities.getRandomInt() + "." + IMAGE_FILE_FORMAT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getBitmapFromImage(BufferedImage image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, IMAGE_FILE_FORMAT, outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }


}
