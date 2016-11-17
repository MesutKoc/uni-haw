package units;

import org.junit.Test;
import static org.junit.Assert.*;
import units.enums.TimeDiffUnit;
import units.interfaces.TimeDiff;

public class ValuesTest {
    /**
     * Test of timeDiff method, of class Values.
     */
    @Test
    public void testTimeDiff_double() {
        double valueInS = 1.0;
        TimeDiff expResult = Values.timeDiffInS(valueInS);
        TimeDiff result = Values.timeDiff(valueInS);
        assertEquals(expResult, result);
    }

    /**
     * Test of timeDiffInH method, of class Values.
     */
    @Test
    public void testTimeDiffInH() {
        double valueInS = 1.0;
        double valueInH = valueInS * TimeDiffUnit.HOUR.multiplier();
        TimeDiff expResult = Values.timeDiffInS(valueInH);
        TimeDiff result = Values.timeDiffInH(valueInS);
        assertEquals(expResult, result);
    }

    /**
     * Test of timeDiff method, of class Values.
     */
    @Test
    public void testTimeDiff_double_TimeDiffUnit() {
        double valueInS = 2.0;
        TimeDiffUnit unit = TimeDiffUnit.HOUR;
        double valueInH = valueInS * unit.multiplier();
        TimeDiff expResult = Values.timeDiffInS(valueInH);
        TimeDiff result = Values.timeDiff(valueInS, unit);
        assertEquals(expResult, result);
    }
}