/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author Toschka
 */
public class converter {
    public static double wToKw(double value) {
        return value / 1000.0;
    }
     
    public static double kwToW(double value) {
        return value * 1000.0;
    }
    
    public static double kmhToMs(double value) {
        return value / 3.6;
    }
    
    public static double msToKmh(double value) {
        return value * 3.6;
}
}