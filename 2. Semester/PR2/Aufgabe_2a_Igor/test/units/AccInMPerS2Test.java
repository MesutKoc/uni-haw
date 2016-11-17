package units;

import org.junit.Test;
import static org.junit.Assert.*;
import units.enums.AccUnit;
import units.interfaces.Acc;
import units.interfaces.Force;
import units.interfaces.Speed;

public class AccInMPerS2Test {

    /**
     * Test of valueOf method, of class AccInMPerS2.
     */
    @Test
    public void testValueOf() {
        double valueInMPerS2 = 1.0;
        AccInMPerS2 expResult = AccInMPerS2.valueOf(1.0);
        AccInMPerS2 result = AccInMPerS2.valueOf(valueInMPerS2);
        assertEquals(expResult, result);
    }

    /**
     * Test of value method, of class AccInMPerS2.
     */
    @Test
    public void testValue() {
        AccUnit unit = AccUnit.KILOMETREPERHOUR2;
        AccInMPerS2 instance = AccInMPerS2.valueOf(1.0);
        double expResult = 12960.0;
        double result = instance.value(unit);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of mPerS2 method, of class AccInMPerS2.
     */
    @Test
    public void testMPerS2() {
        double valueInMPerS2 = 2.0;
        double expResult = 2.0;
        AccInMPerS2 result = AccInMPerS2.valueOf(2.0);
        assertEquals(expResult, result.mPerS2(), 0.001);
    }

    /**
     * Test of add method, of class AccInMPerS2.
     */
    @Test
    public void testAdd() {
        double valueInMPerS2_1 = 1.0;
        double valueInMPerS2_2 = 2.0;
        double valueInMPerS2_3 = 3.0;
        AccInMPerS2 instance_1 = AccInMPerS2.valueOf(valueInMPerS2_1);
        AccInMPerS2 instance_2 = AccInMPerS2.valueOf(valueInMPerS2_2);
        Acc expResult = AccInMPerS2.valueOf(valueInMPerS2_3);
        Acc result = instance_1.add(instance_2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of mul method, of class AccInMPerS2.
     */
    @Test
    public void testMul_Mass() {
        double valueInKg = 2.0;
        double valueInMPerS2 = 3.0;
        double valueInN = 6.0;
        AccInMPerS2 instance = AccInMPerS2.valueOf(valueInMPerS2);
        Force expResult = Values.forceInN(valueInN);
        Force result = instance.mul(Values.massInKg(valueInKg));
        assertEquals(expResult, result);
    }

    /**
     * Test of mul method, of class AccInMPerS2.
     */
    @Test
    public void testMul_TimeDiff() {
        double valueInS = 3.0;
        double valueInMPerS2 = 2.0;
        double valueInMPerS = 6.0;
        AccInMPerS2 instance = AccInMPerS2.valueOf(valueInMPerS2);
        Speed expResult = Values.speedInMPerS(valueInMPerS);
        Speed result = instance.mul(Values.timeDiffInS(valueInS));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of compareTo method, of class AccInMPerS2.
     */
    @Test
    public void testCompareTo() {    
        double valueInMPerS2_1 = 2.0;
        int expResult_1 = 0;
        AccInMPerS2 instance_1 = AccInMPerS2.valueOf(valueInMPerS2_1);
        int result_1 = instance_1.compareTo(instance_1);
        double valueInMPerS2_2 = 1.0;
        int expResult_2 = -1;
        AccInMPerS2 instance_2 = AccInMPerS2.valueOf(valueInMPerS2_2);
        int result_2 = instance_2.compareTo(instance_1);
        assertEquals(expResult_1, result_1);
        assertEquals(expResult_2, result_2);
    }
    
    /**
     * Test of equals method, of class AccInMPerS2.
     */
    @Test
    public void testEquals() {
        double valueInMPerS2_1 = 2.0;
        boolean expResult_1 = true;
        AccInMPerS2 instance_1 = AccInMPerS2.valueOf(valueInMPerS2_1);
        boolean result_1 = instance_1.equals(instance_1);
        double valueInMPerS2_2 = 1.0;
        boolean expResult_2 = false;
        AccInMPerS2 instance_2 = AccInMPerS2.valueOf(valueInMPerS2_2);
        boolean result_2 = instance_1.equals(instance_2);
        assertEquals(expResult_1, result_1);
        assertEquals(expResult_2, result_2);
    }

    /**
     * Test of hashCode method, of class AccInMPerS2.
     */
    @Test
    public void testHashCode() {
        double valueInMPerS2 = 2.0;
        AccInMPerS2 instance = AccInMPerS2.valueOf(valueInMPerS2);
        int expResult = 1073742061;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class AccInMPerS2.
     */
    @Test
    public void testToString() {
        double valueInMPerS2 = 2.0;
        AccInMPerS2 instance = AccInMPerS2.valueOf(valueInMPerS2);
        String expResult = "AccInMPerS2{valueInMPerS2=2.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    } 
}