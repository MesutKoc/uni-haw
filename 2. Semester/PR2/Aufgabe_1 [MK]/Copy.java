/**
 *
 * @author Mesut, Anton
 */

//====================================
// Packages and Includes
//====================================
package copy;
import help.*;
import model.*;

public class Copy {
    private static final long timeMS = 10;
    private static Porsche911GT2RS porsche;
    
    public static void driveSpeedTest(double speed) {
        speed = converter.convert(speed,"kmhToMs");
        while (porsche.speed() < speed) porsche.step(timeMS/1000.0, 1.0);
        System.out.println(porsche);
    }
    
    public static void main(String[] args) {
        porsche = Porsche911GT2RS.valueOf();
        driveSpeedTest(100);
    }
}
