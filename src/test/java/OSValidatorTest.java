import org.junit.Test;
import webcam.OSValidator;

import static org.junit.Assert.*;

/**
 * Created by Pawel on 2016-03-01.
 */
public class OSValidatorTest {

    @Test
    public void testIsWindows() throws Exception {
        assertTrue(OSValidator.isWindows());
    }

    @Test
    public void testIsUnix() throws Exception {
        assertFalse(OSValidator.isUnix());
    }
}