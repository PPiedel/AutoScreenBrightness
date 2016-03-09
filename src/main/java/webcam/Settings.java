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
    private final int DEFAULT_BREAK_TIME = 2000;
    private final int DEFAULT_DELAY = 0;

    private int breakTime;
    private int delay;

    public Settings() {
        this.breakTime = DEFAULT_BREAK_TIME;
        this.delay = DEFAULT_DELAY;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean saveSettings(Settings settings){
        boolean saved = false;
        try {
            FileUtils.writeStringToFile(new File("test.txt"), breakTime +"\n"+delay);
            saved=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saved;
    }

    public boolean readSettings(File file){
        boolean areRead = false;
        List<String> settings = new ArrayList<>();
        try {
            settings = FileUtils.readLines(file);
            areRead = true;
        }catch (IOException e){
            e.printStackTrace();
        }

        setBreakTime(Integer.parseInt(settings.get(0)));
        setDelay(Integer.parseInt(settings.get(1)));

        return areRead;
    }
}
