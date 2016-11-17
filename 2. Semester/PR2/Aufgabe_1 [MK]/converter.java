package help;
/**
 *
 * @author Mesut
 */

public class converter {
    public static double convert(double value, String s){
    double valueNew;
     switch (s){
         case "wToKw":   valueNew = value / 1000.0;
                         break;
         case "kwToW":   valueNew = value * 1000.0;
                         break;
         case "kmhToMs": valueNew = value / 3.6;
                         break;
         case "msToKmh": valueNew = value * 3.6;
                         break;
         default: throw new IllegalArgumentException("Invalid string of: " + s);
     }
     return valueNew;
    }
}