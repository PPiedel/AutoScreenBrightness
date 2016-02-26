import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Pawel on 2016-02-26.
 */
public class BrightnessManager {
    public static void setBrightness(int brightness, int delay) throws IOException{
        String command = createExecCommand(brightness, delay);

        Process powerShellProcess = Runtime.getRuntime().exec(command);

        powerShellProcess.getOutputStream().close();

        //Report any error messages
        String line;

        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));
        line = stderr.readLine();
        if (line != null) {
            System.err.println("Standard Error:");
            do {
                System.err.println(line);
            } while ((line = stderr.readLine()) != null);

        }
        stderr.close();

    }

    private static String createExecCommand(int brightness, int delay) {
        String power_shell_exe_command = "powershell.exe  ";
        String brightness_settings_command = String.format("$brightness = %d; $delay = %d;", brightness, delay) +
                                                            "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;"+
                                                            "$myMonitor.wmisetbrightness($delay, $brightness);" ;

        return power_shell_exe_command + brightness_settings_command;
    }
}
