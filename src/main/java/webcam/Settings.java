package webcam;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 2016-03-09.
 */
public class Settings {

    private static int breakTime = 2000;
    private static int delay =  0;

    public static int getBreakTime() {
        return breakTime;
    }

    public static void setBreakTime(int time) {
        breakTime = time;
    }

    public static int getDelay() {
        return delay;
    }

    public static void setDelay(int delayTime) {
        delay=delayTime;
    }

    public static boolean setSettingsFromFile(File file){
        boolean areRead = false;
        List<String> settings = new ArrayList<>();
        try {
            settings = FileUtils.readLines(file);
            areRead = true;
        }catch (IOException e){
            e.printStackTrace();
        }

        if (areRead){
            breakTime = Integer.parseInt(settings.get(0));
            delay = Integer.parseInt(settings.get(1));
        }

        return areRead;
    }

    public static boolean saveSettings(int breakTime, int delay){
        boolean saved = false;
        try {
            /*function writeStringToFile overwrites previus values*/
            FileUtils.writeStringToFile(new File("settings.txt"), breakTime +"\n"+delay);
            saved=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saved;
    }


}
