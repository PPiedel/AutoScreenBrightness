package util;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;

/**
 * Created by Pawel on 2016-02-26.
 */
public class Utilities {
    public static int getRandomInt(){
       Random generator = new Random();
        return generator.nextInt(100)+1;
    }

    public static boolean saveSettings(int brightness,  int delay){
        boolean saved = false;
        try {
            FileUtils.writeStringToFile(new File("test.txt"), brightness+"\n"+delay);
            saved=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saved;
    }

    public static boolean readSettings(File file){
        boolean areRead = false;
        List<String> settings = new ArrayList<>();
        try {
            settings = FileUtils.readLines(file);
            areRead = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        //TODO zapisywanie do settingsow z listy
        return areRead;
    }
}
