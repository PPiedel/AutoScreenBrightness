import java.util.Random;

/**
 * Created by Pawel on 2016-02-26.
 */
public class Utilities {
    public static int getRandomInt(){
       Random generator = new Random();
        return generator.nextInt(100)+1;
    }
}
