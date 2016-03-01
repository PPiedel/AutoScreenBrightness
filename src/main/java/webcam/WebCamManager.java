package webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import util.Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Pawel on 2016-02-26.
 */
public class WebCamManager {
    //Outer class
    private int breakTime = 2000;
    private int delayTime = 0;
    private final String IMAGE_FILE_FORMAT = "bmp";

    private Webcam webcam;

    WebCamManager(){
        webcam = Webcam.getDefault();
        setCameraResolution();
    }

    public int getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    public int getDelayTime() {
        return delayTime;
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

        /*Set default web cam resolution*/
       // webcam.setViewSize(webcam.getViewSize());
    }

    public BufferedImage getCameraImage() {
        webcam.open();
        BufferedImage image = webcam.getImage();
        webcam.close();

        return image;
    }

    private Dimension getCameraResolution(Webcam webcam) {
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

    public byte[] getImageInByteArray(BufferedImage image) {
        byte[] imageInByte = null;

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, IMAGE_FILE_FORMAT, outputStream);
            outputStream.flush();
            imageInByte = outputStream.toByteArray();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Image in byte length : "+imageInByte.length);
        return imageInByte;
    }


}
