import com.github.sarxos.webcam.Webcam;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pawel on 2016-02-26.
 */
public class WebCamManagerTest {
    @Test
    public void testIsWebCamDetected() throws Exception {
        Webcam webcam = Webcam.getDefault();
        assert (webcam!=null);
    }

    @Test
    public void webcamConnection() throws Exception{
        Webcam webcam =Webcam.getDefault();
        assertTrue(webcam.open());
    }

}