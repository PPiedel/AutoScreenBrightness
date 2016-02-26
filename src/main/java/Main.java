public class Main {
    public static void main(String[] args) {
        /*try {
            BrightnessManager.setBrightness(50, 5);
        }catch (IOException e){
            e.printStackTrace();
        }*/

        WebCamManager webCamManager = new WebCamManager();
        byte[] pixels = webCamManager.getCameraImageBitmap();

        BrightnessManager brightnessManager = new BrightnessManager();
        int brightness =  brightnessManager.calculateBrightness(pixels);

        System.out.println("Calculated brightness : "+brightness);


    }
}
