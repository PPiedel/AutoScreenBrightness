package webcam;

import webcam.BrightnessManager;
import webcam.BrightnessSettingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Pawel on 2016-03-01.
 */
public class LinuxBrightnessManager extends BrightnessManager {
    @Override
    public void setBrightness(int brightness, int delay) throws IOException, BrightnessSettingException {
		String command = createExecCommand(brightness, delay);
		Process bashProcess = Runtime.getRuntime().exec(command);
        bashProcess.getOutputStream().close();
    }

    private enum Card{
        intel,acpi
    }

    @Override
    public String createExecCommand (int brightness, int delay) throws IOException{

        Process checkDir;
        BufferedReader stdInput;
        String cardString;
        checkDir = Runtime.getRuntime().exec("if test -d /sys/class/backlight/intel_backlight; then echo \"intel\";" +
                "else if test -d /sys/class/backlight/acpi_video0; then echo \"acpi\" ;fi ");
        stdInput = new BufferedReader(new
                InputStreamReader(checkDir.getInputStream()));
        Card card = Card.valueOf(stdInput.readLine());
        switch (card){
            case intel:
                cardString = "/sys/class/backlight/intel_backlight/";
                break;
            case acpi:
                cardString = "/sys/class/backlight/acpi_video0/";
                break;
            default:
                throw new IOException();
        }

        Process process = Runtime.getRuntime().exec("cat "+cardString+"max_brightness");
        stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));
        String maxBrightness = stdInput.readLine();
       return new String("echo "+new Integer(maxBrightness)*(brightness/100)+
               " | sudo tee /sys/class/backlight/intel_backlight/brightness");
    }
}
