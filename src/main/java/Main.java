import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            BrightnessManager.setBrightness(50, 5);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
