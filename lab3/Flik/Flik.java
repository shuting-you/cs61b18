import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // Integer from 128 starts to store in different places
        return a == b;
    }

    @Test
    public void testFlik() {
        boolean A = isSameNumber(2, 2);
        assertTrue(A);
    }
}
