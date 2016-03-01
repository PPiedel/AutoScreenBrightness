/**
 * Created by Pawel on 2016-03-01.
 */

/*Based on the http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/*/
    
public class OSValidator {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {

        return (OS.contains("win"));

    }

    public static boolean isUnix() {

        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0 );

    }
}
