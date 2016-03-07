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

        String[] command = {"sh","-c","test -d /sys/class/backlight/intel_backlight && echo \"intel\" || (test -d /sys/class/backlight/acpi_video0 && echo \"acpi\")"};
        
        checkDir = Runtime.getRuntime().exec(command);
        checkDir.waitFor();
        stdInput = new BufferedReader(new
                InputStreamReader(checkDir.getInputStream()));
        System.out.println("####    "+stdInput.readLine());
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
               " | sudo tee "+cardString+"brightness");
    }
}
