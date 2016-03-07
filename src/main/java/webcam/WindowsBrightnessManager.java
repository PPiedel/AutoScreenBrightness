package webcam;

import webcam.BrightnessManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Pawel on 2016-03-01.
 */
public class WindowsBrightnessManager extends BrightnessManager {
    @Override
    public void setBrightness(int brightness, int delay) throws IOException, BrightnessSettingException, InterruptedException {
        String command = createExecCommand(brightness, delay);
        Process powerShellProcess = Runtime.getRuntime().exec(command);
        powerShellProcess.waitFor();
        powerShellProcess.getOutputStream().close();

        reportAnyErrors(powerShellProcess);
    }

    @Override
    public String createExecCommand(int brightness, int delay) {
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
}
