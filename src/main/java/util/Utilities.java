package util;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import webcam.Settings;

/**
 * Created by Pawel on 2016-02-26.
 */
public class Utilities {
    public static int getRandomInt(){
       Random generator = new Random();
        return generator.nextInt(100)+1;
    }


}
