import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Pawel on 2016-02-26.
 */
public class BrightnessManager {

    public void setBrightness(int brightness, int delay) throws IOException, BrightnessSettingException {
        String command = createExecCommand(brightness, delay);
        Process powerShellProcess = Runtime.getRuntime().exec(command);
        powerShellProcess.getOutputStream().close();

        reportAnyErrors(powerShellProcess);
    }

    private String createExecCommand(int brightness, int delay) {
        String power_shell_exe_command = "powershell.exe  ";
        String brightness_settings_command = String.format("$brightness = %d; $delay = %d;", brightness, delay) +
                "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;" +
                "$myMonitor.wmisetbrightness($delay, $brightness);";

        return power_shell_exe_command + brightness_settings_command;
    }

    private void reportAnyErrors(Process powerShellProcess) throws IOException, BrightnessSettingException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
        String errorStream = bufferedReader.readLine();
        if (errorStream != null) {
            throw new BrightnessSettingException(errorStream);

        }
        bufferedReader.close();
    }

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
        float percentBrightness = (float) ((sumOfBrightness / 2.55) / (image.getHeight() * image.getWidth()));
        System.out.println("Luminance " + percentBrightness);
        return (int) percentBrightness;
    }

}
