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
		String command = createExecCommand(brightness, delay);
		Process bashProcess = Runtime.getRuntime().exec(command);
    }

    @Override
    public String createExecCommand(int brightness, int delay) {
        String maxBrightness = Runtime.getRuntime().exec("cat /sys/class/backlight/intel_backlight/max_brightness");
        
        String calculatedBrightness = new String("echo "+new Integer(maxBrightness)*(brightness/100)+" | sudo tee /sys/class/backlight/intel_backlight/brightness");
        return calculatedBrightness;
    }
}
