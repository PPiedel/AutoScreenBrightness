package webcam;

import webcam.BrightnessManager;
import webcam.BrightnessSettingException;

import java.io.IOException;

/**
 * Created by Pawel on 2016-03-01.
 */
public class LinuxBrightnessManager extends BrightnessManager {
    @Override
    public void setBrightness(int brightness, int delay) throws IOException, BrightnessSettingException {

    }

    @Override
    public String createExecCommand(int brightness, int delay) {
        return null;
    }
}
